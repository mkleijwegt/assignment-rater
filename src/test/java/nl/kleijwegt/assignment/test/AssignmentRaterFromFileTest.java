package nl.kleijwegt.assignment.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.itextpdf.text.DocumentException;

import nl.kleijwegt.entity.Assignment;
import nl.kleijwegt.service.AssignmentRaterService;
import nl.kleijwegt.service.JsonAssignmentService;

/**
* AssignmentRaterFromFileTest can be run to rate all student repositories for a classroom assignment
* Make sure to setup your own json file in assignment-files and run it in this test.
* 
* @author Mark Kleijwegt
* 
*/
@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class AssignmentRaterFromFileTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private JsonAssignmentService jsonAssignmentService;
	
	@Autowired
	private AssignmentRaterService assignmentRaterService;

	@Test
	public void testAssignment() throws IOException, DocumentException, URISyntaxException{
		
		Assignment assignment = jsonAssignmentService.readFile("php_oefen_p03.json");
		assignment.setIncludeFeedback(false);
		
		assignmentRaterService.rateAssignment(assignment);
			
		
	}
}
