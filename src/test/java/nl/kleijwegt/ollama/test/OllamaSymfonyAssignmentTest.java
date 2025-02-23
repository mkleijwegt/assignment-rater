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
import nl.kleijwegt.entity.ai.Options;
import nl.kleijwegt.service.GitHubService;
import nl.kleijwegt.service.OllamaService;
import nl.kleijwegt.service.PdfService;


@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class OllamaSymfonyAssignmentTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	@Autowired
	private OllamaService ollamaService;
	
	@Autowired
	private PdfService pdfService;

	@Test
	public void testSymfony() throws IOException, DocumentException{
		
		final String REPOSITORY_NAME = "ROCMondriaanTIN/sd23-p07-symfony-oefen-Areberohirwa";
		final String BRANCH = "exercise-one";
		
		GitHub gitHub = GitHubBuilder.fromPropertyFile().build();
		
		GHRepository repository = gitHubService.fetchRepository(gitHub, REPOSITORY_NAME);
		List<String> folders = new ArrayList<>();
		folders.add("/src/Controller");
		folders.add("/templates");
		List<GHContent> contents = gitHubService.fetchFilesFromRepository(repository, folders, BRANCH);
		
		PdfPTable table = pdfService.createAssignmentTable("Areberohirwa", "Hieronder vind je de feedback op de opdracht: " + BRANCH + ". "
				+ "Let op: deze feedback is AI gegenereerd en kan daardoor onjuistheden bevatten.");
		//making sure long text won't set a blank page in the PDF
		table.setSplitLate(false);
		String fullCode = "";
		for(GHContent content : contents) {
			if(content.getName().endsWith(".php") || content.getName().endsWith(".html.twig")) {
				String fileContent = IOUtils.toString(content.read(), StandardCharsets.UTF_8);
				log.debug(content.getName());
				log.debug(fileContent);
				if(fileContent.trim().length() > 0) {
					pdfService.addTableSection(table, content.getName(), fileContent);
					fullCode += fileContent;
				}
			}
		}
		Options options = new Options();
		String feedback = ollamaService.ollamaGenerate("Geef feedback om de code te verbeteren. "
				+ "De code bevat onder andere een route voor een calculator (optellen, aftrekken, vermenigvuldigen en delen) en een route voor het "
				+ "teruggeven van de maand op basis van numerieke input. "
				+ "De code:  \"" + fullCode + "\"", AIModel.QWEN_2_5_CODER_7B, options);
		pdfService.addTableSection(table, "Gegenereerde AI feedback", feedback);
		
		
		pdfService.writeDocument(table, "Areberohirwa.pdf");
		
		
	}
}
