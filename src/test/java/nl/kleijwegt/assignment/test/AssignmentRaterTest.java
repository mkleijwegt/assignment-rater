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


@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class AssignmentRaterTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private JsonAssignmentService jsonAssignmentService;
	
	@Autowired
	private AssignmentRaterService assignmentRaterService;

	@Test
	public void testAssignment() throws IOException, DocumentException, URISyntaxException{
		
		Assignment assignment = jsonAssignmentService.readFile("symfony_oefen_p07.json");
		
		assignmentRaterService.rateAssignment(assignment);
			
		
	}
}
