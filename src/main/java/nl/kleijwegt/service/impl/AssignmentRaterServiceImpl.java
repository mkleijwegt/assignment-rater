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
import nl.kleijwegt.entity.github.GHStudentAssignment;
import nl.kleijwegt.service.AssignmentRaterService;
import nl.kleijwegt.service.GitHubService;
import nl.kleijwegt.service.OllamaService;
import nl.kleijwegt.service.PdfService;

@Service
public class AssignmentRaterServiceImpl implements AssignmentRaterService{
	
final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	@Autowired
	private OllamaService ollamaService;
	
	@Autowired
	private PdfService pdfService;

	@Override
	public void rateAssignment(Assignment assignment) throws IOException, DocumentException {
		List<GHStudentAssignment> studentAssignments = gitHubService.fetchAllStudentAssignmentsForAssignmentId(assignment.getAssignmentId());
		
		for (GHStudentAssignment studentAssignment : studentAssignments) {
			rateStudentAssignment(assignment, studentAssignment);
		}
	}
	
	private void rateStudentAssignment(Assignment assignment, GHStudentAssignment studentAssignment) throws IOException, DocumentException {
		String studentLogin = studentAssignment.getStudents().iterator().next().getLogin();
		String filePath = studentLogin + "-" + studentAssignment.getAssignment().getTitle() + ".pdf";
		String resultFolder = assignment.getPdfResultFolder();
		
		if(!pdfService.documentOrFolderExists(resultFolder)) {
			Path path = Paths.get(resultFolder);
			Files.createDirectories(path);
		}
		
		if(pdfService.documentOrFolderExists(resultFolder + System.getProperty("file.separator") + filePath)) {
			//if the file is already present we'll skip it
			return;
		}
		
		GHRepository repository = gitHubService.fetchRepository(studentAssignment.getRepository().getFullName());
		List<GHContent> contents = fetchCodeFromRepository(repository, assignment);
		
		PdfPTable table = pdfService.createAssignmentTable(studentLogin, assignment.getPdfHeading());
		
		String fullCode = "";
		for(GHContent content : contents) {
			String fileContent = IOUtils.toString(content.read(), StandardCharsets.UTF_8);
			pdfService.addTableSection(table, assignment.getPdfFileHeading() + " " + content.getName(), fileContent);
			fullCode += fileContent;
		}
		pdfService.addTableSection(table, assignment.getPdfPromptHeading(), assignment.getPrompt());
		String feedback = ollamaService.ollamaGenerate(assignment.getPrompt() + "\"" + fullCode + "\"", getAIModelFromEnum(assignment.getAiModel()));
		pdfService.addTableSection(table, assignment.getPdfFeedbackHeading(), feedback);
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
	
	private AIModel getAIModelFromEnum(String aiModelName) {
		for(AIModel aiModel : AIModel.values()){
			if(aiModel.name.equals(aiModelName)) {
				//return AI Model that matches the given name
				return aiModel;
			}
		}
		//not found default to qwen coder 7b
		return AIModel.QWEN_2_5_CODER_7B;
	}

}
