package nl.kleijwegt.json.test;

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
import nl.kleijwegt.service.JsonAssignmentService;

@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class LoadAssignmentFromJsonTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private JsonAssignmentService jsonAssignmentService;

	@Test
	public void testLoadAssignmentFromJson() throws IOException, DocumentException, URISyntaxException{
		Assignment assignment = jsonAssignmentService.readFile("php_tussentoets_p05.json");
		log.debug(assignment);
	}
}
