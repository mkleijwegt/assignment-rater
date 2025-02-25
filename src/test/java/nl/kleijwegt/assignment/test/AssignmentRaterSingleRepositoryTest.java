package nl.kleijwegt.assignment.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.itextpdf.text.DocumentException;

import nl.kleijwegt.entity.Assignment;
import nl.kleijwegt.entity.AssignmentFileType;
import nl.kleijwegt.entity.AssignmentFolder;
import nl.kleijwegt.entity.ai.AIModel;
import nl.kleijwegt.service.AssignmentRaterService;


@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class AssignmentRaterSingleRepositoryTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private AssignmentRaterService assignmentRaterService;

	@Test
	public void testSingleRepository() throws IOException, DocumentException{
		final String REPOSITORY_NAME = "ROCMondriaanTIN/sd23-p07-symfony-oefen-mkleijwegt";
		final String BRANCH = "exercise-one";
		final String STUDENT_LOGIN = "mkleijwegt";
		final String ASSIGNMENT_TITLE = "check-single-repository";
		
		Assignment assignment = new Assignment();
		
		assignment.setBranch(BRANCH);
		assignment.setPdfHeading("Hieronder vind je de feedback op de opdracht: " + BRANCH + ". "
				+ "Let op: deze feedback is AI gegenereerd en kan daardoor onjuistheden bevatten.");
		assignment.setPdfFileHeading("Jouw uitwerking van");
		assignment.setPdfPromptHeading("Gebruikte prompt:");
		assignment.setPdfFeedbackHeading("Gegenereerde AI feedback:");
		assignment.setAiModel(AIModel.QWEN_2_5_CODER_7B.name);
		assignment.setPrompt("Geef feedback om de code te verbeteren. "
				+ "De code bevat onder andere een route voor een calculator (optellen, aftrekken, vermenigvuldigen en delen) en een route voor het "
				+ "teruggeven van de maand op basis van numerieke input. "
				+ "De code: ");
		
		assignment.setPdfResultFolder("test");
		
		List<AssignmentFileType> fileTypes = new ArrayList<>();
		fileTypes.add(new AssignmentFileType(".php"));
		fileTypes.add(new AssignmentFileType(".html.twig"));
		assignment.setFileTypes(fileTypes);
		
		List<AssignmentFolder> folders = new ArrayList<>();
		folders.add(new AssignmentFolder("/src/Controller"));
		folders.add(new AssignmentFolder("/templates"));
		assignment.setFolders(folders);
		
		
		assignmentRaterService.rateStudentAssignment(assignment, REPOSITORY_NAME, ASSIGNMENT_TITLE, STUDENT_LOGIN);

		
		
	}
}
