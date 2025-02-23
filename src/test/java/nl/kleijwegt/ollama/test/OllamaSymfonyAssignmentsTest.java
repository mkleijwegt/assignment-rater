package nl.kleijwegt.ollama.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import nl.kleijwegt.entity.ai.AIModel;
import nl.kleijwegt.entity.github.GHStudentAssignment;
import nl.kleijwegt.service.GitHubService;
import nl.kleijwegt.service.OllamaService;
import nl.kleijwegt.service.PdfService;


@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class OllamaSymfonyAssignmentsTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	@Autowired
	private OllamaService ollamaService;
	
	@Autowired
	private PdfService pdfService;

	@Test
	public void testSymfony() throws IOException, DocumentException{
		
		final Long ASSIGNMENT_ID = 729275L;
		final String BRANCH = "exercise-three";
		Long page = 1L;
		final Long PER_PAGE = 50L;
		
		GitHub github = GitHubBuilder.fromPropertyFile().build();
		
		List<GHStudentAssignment> studentAssignments = new ArrayList<>();
		
		while(page < 3) {
			studentAssignments.addAll(gitHubService.fetchStudentAssignments(ASSIGNMENT_ID, page, PER_PAGE));
			page++;
		}
		
		for (GHStudentAssignment studentAssignment : studentAssignments) {
			
			String studentLogin = studentAssignment.getStudents().iterator().next().getLogin();
			String filePath = studentLogin + "-" + studentAssignment.getAssignment().getTitle() + ".pdf";
			
			//only if the file is not present we'll go through all steps generate it
			if(!pdfService.documentExists(filePath)) {
				GHRepository repository = gitHubService.fetchRepository(github, studentAssignment.getRepository().getFullName());
				List<String> folders = new ArrayList<>();
				folders.add("src/Controller");
				folders.add("src/Entity");
				folders.add("templates");
				List<GHContent> contents = gitHubService.fetchFilesFromRepository(repository, folders, BRANCH);
				
				PdfPTable table = pdfService.createAssignmentTable(studentAssignment.getStudents().iterator().next().getLogin(), 
						"Hieronder vind je de feedback op de opdracht: " + BRANCH + ". "
						+ "Let op: deze feedback is AI gegenereerd en kan daardoor onjuistheden bevatten.");
				//making sure long text won't set a blank page in the PDF
				table.setSplitLate(false);
				String fullCode = "";
				for(GHContent content : contents) {
					if(content.getName().endsWith(".php") || content.getName().endsWith(".html.twig")) {
						String fileContent = IOUtils.toString(content.read(), StandardCharsets.UTF_8);
						log.debug(content.getName());
						log.debug(fileContent);
						//skip empty files
						if(fileContent.trim().length() > 0) {
							pdfService.addTableSection(table, "Jouw uitwerking van " + content.getName(), fileContent);
							fullCode += fileContent;
						}
					}
				}
				String feedback = ollamaService.ollamaChat("Geef positieve feedback om de gegeven code te verbeteren. Geef alleen code suggesties voor twig, PHP en Symfony 6.4."
						+ "De code bevat onder andere een route voor het teruggeven van ingredienten (Ingredient entity) opgehaald uit de database die worden weergegeven op "
						+ "een twig in een tabel met behulp van een for loop. "
						+ "Ook bevat de code een route voor het teruggeven van één ingredient weergegeven op een twig."
						+ "Daarnaast zit er ook nog een route bij voor het weergeven van fietsen uit de database (Bike entity) en "
						+ "de mogelijkheid om details op te vragen van een fiets. "
						+ "Als er in onderstaande code niets kan worden gevonden voor deze functionaliteit geef dan terug dat er geen feedback kan worden gegeven. "
						+ "De code:  \"" + fullCode + "\"", AIModel.QWEN_2_5_CODER_14B);
				pdfService.addTableSection(table, "Gegenereerde AI feedback", feedback);
				
				
				pdfService.writeDocument(table, filePath);
			}
			
		}
	}
}
