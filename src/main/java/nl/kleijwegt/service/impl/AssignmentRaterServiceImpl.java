package nl.kleijwegt.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import nl.kleijwegt.entity.Assignment;
import nl.kleijwegt.entity.AssignmentFileType;
import nl.kleijwegt.entity.AssignmentFolder;
import nl.kleijwegt.entity.ai.AIModel;
import nl.kleijwegt.entity.ai.Options;
import nl.kleijwegt.entity.github.classroom.GHStudentAssignment;
import nl.kleijwegt.service.AssignmentRaterService;
import nl.kleijwegt.service.GitHubService;
import nl.kleijwegt.service.OllamaService;
import nl.kleijwegt.service.PdfService;

/**
* AssignmentRaterServiceImpl contains the implementation functions of the interface {@link nl.kleijwegt.service.AssignmentRaterService}
* It contains functions that can be used to rate the work of all student assignments in a GitHub classroom assignment
* 
* @author Mark Kleijwegt
* 
*/
@Service
public class AssignmentRaterServiceImpl implements AssignmentRaterService{
	
	final static Logger log = LogManager.getLogger();
	
	private static final String STANDARD_OUTPUT_FOLDER = "result";
	
	@Autowired
	private GitHubService gitHubService;
	
	@Autowired
	private OllamaService ollamaService;
	
	@Autowired
	private PdfService pdfService;

	/**
	 * <p>Function that rates all student assignments for a given classroom assignment according to the provided instructions in the 
	 * Assignment object. See {@link nl.kleijwegt.entity.Assignment}.
	 * </p>
	 * @param assignment the assignment object containing the instructions
	 */
	@Override
	public void rateAssignment(Assignment assignment) throws IOException, DocumentException {
		List<GHStudentAssignment> studentAssignments = gitHubService.fetchAllStudentAssignmentsForAssignmentId(assignment.getAssignmentId());
		
		for (GHStudentAssignment studentAssignment : studentAssignments) {
			rateStudentAssignment(assignment, studentAssignment);
		}
	}
	
	/**
	 * <p>Function that rates a student assignment according to the provided instructions in the 
	 * Assignment object. See {@link nl.kleijwegt.entity.Assignment}.
	 * </p>
	 * @param assignment the assignment object containing the instructions
	 */
	@Override
	public void rateStudentAssignment(Assignment assignment, GHStudentAssignment studentAssignment) throws IOException, DocumentException {
		String studentLogin = studentAssignment.getStudents().iterator().next().getLogin();
		String assignmentTitle = studentAssignment.getAssignment().getTitle();
		String repositoryName = studentAssignment.getRepository().getFullName();
		rateStudentAssignment(assignment, repositoryName, assignmentTitle, studentLogin);
	}
	
	/**
	 * <p>Function that rates a student assignment according to the provided instructions in the 
	 * Assignment object. See {@link nl.kleijwegt.entity.Assignment}.
	 * </p>
	 * @param assignment the assignment object containing the instructions
	 * @param repositoryName the repository to rate
	 * @param assignmentTitle the title to use for the assignment
	 * @param studentLogin the login or name of the student
	 * 
	 */
	@Override
	public void rateStudentAssignment(Assignment assignment, String repositoryName, 
			String assignmentTitle, String studentLogin) throws IOException, DocumentException {
		log.debug("Generating for student: " + studentLogin);
		
		String resultFolder = STANDARD_OUTPUT_FOLDER + System.getProperty("file.separator") + assignment.getPdfResultFolder();
		
		if(!pdfService.documentOrFolderExists(resultFolder)) {
			//if the ouput folder is not yet present let's generate it
			Path path = Paths.get(resultFolder);
			Files.createDirectories(path);
		}
		
		String filePath = resultFolder + System.getProperty("file.separator") + studentLogin + "-" + assignmentTitle + ".pdf";
		
		if(pdfService.documentOrFolderExists(filePath)) {
			//if the file is already present we'll skip it
			return;
		}
		
		GHRepository repository = gitHubService.fetchRepository(repositoryName);
		List<GHContent> contents = fetchCodeFromRepository(repository, assignment);
		
		generateDocument(assignment, contents, studentLogin, filePath);
		
	}
	
	private void generateDocument(Assignment assignment, List<GHContent> contents, 
			String studentLogin, String filePath) throws IOException, DocumentException {
		//create a PDF table with the student name and introduction text
		PdfPTable table = pdfService.createAssignmentTable(studentLogin, assignment.getPdfHeading());
		//gather all code in a single String for prompting purposes
		String fullCode = "";
		for(GHContent content : contents) {
			String fileContent = IOUtils.toString(content.read(), StandardCharsets.UTF_8);
			//add a table section to the PDF document for each source file
			pdfService.addTableSection(table, assignment.getPdfFileHeading() + " " + content.getName(), fileContent);
			fullCode += fileContent;
		}
		//only add feedback if needed
		if(assignment.getIncludeFeedback()) {
			//add a table section containing the prompt used
			pdfService.addTableSection(table, assignment.getPdfPromptHeading(), assignment.getPrompt());
			//create options object with information from assignment
			Options options = createOptionsBasedOnAssignment(assignment);
			//call ollama with the prompt, full code and AI model
			String feedback = ollamaService.ollamaGenerate(assignment.getPrompt() + "\"" + fullCode + "\"", 
					getAiModelFromEnum(assignment.getAiModel()), options);
			//add a table section containing the feedback from ollama
			pdfService.addTableSection(table, assignment.getPdfFeedbackHeading(), feedback);
		}
		//write the document with the complete table
		pdfService.writeDocument(table, filePath);
	}
	
	private List<GHContent> fetchCodeFromRepository(GHRepository repository, Assignment assignment) throws IOException {
		List<String> folders = getFoldersFromAssignment(assignment);
		List<String> fileTypes = getFileTypesFromAssignment(assignment);
		List<GHContent> contents = gitHubService.fetchFilesFromRepository(repository, folders, assignment.getBranch());
		
		List<GHContent> contentToKeep = new ArrayList<>();
		
		for(GHContent content : contents) {
			if(shouldContentBeKept(content, fileTypes)) {
				contentToKeep.add(content);
			}
		}
		return contentToKeep;
	}
	
	private boolean shouldContentBeKept(GHContent content, List<String> fileTypes) throws IOException {
		for(String fileType : fileTypes) {
			//content must be of desired filetype and not empty to be kept
			if(content.getName().endsWith(fileType) && !isContentEmpty(content)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isContentEmpty(GHContent content) throws IOException {
		String fileContent = IOUtils.toString(content.read(), StandardCharsets.UTF_8);
		if(fileContent.trim().length() > 0) {
			//if more than 0 characters the file is not empty
			return false;
		}
		return true;
	}
	
	private List<String> getFoldersFromAssignment(Assignment assignment){
		List<String> folders = new ArrayList<>();
		for(AssignmentFolder assignmentFolder : assignment.getFolders()) {
			folders.add(assignmentFolder.getFolder());
		}
		return folders;
	}
	
	private List<String> getFileTypesFromAssignment(Assignment assignment){
		List<String> fileTypes = new ArrayList<>();
		for(AssignmentFileType assignmentFileType : assignment.getFileTypes()) {
			fileTypes.add(assignmentFileType.getFileType());
		}
		return fileTypes;
	}
	
	private AIModel getAiModelFromEnum(String aiModelName) {
		for(AIModel aiModel : AIModel.values()){
			if(aiModel.name.equals(aiModelName)) {
				//return AI Model that matches the given name
				return aiModel;
			}
		}
		//not found default to qwen coder 7b
		return AIModel.QWEN_2_5_CODER_7B;
	}
	
	private Options createOptionsBasedOnAssignment(Assignment assignment) {
		Options options = new Options();
		options.setTemperature(assignment.getTemperature());
		options.setTopK(assignment.getTopK());
		options.setTopP(assignment.getTopP());
		options.setSeed(assignment.getSeed());
		return options;
	}

	

}
