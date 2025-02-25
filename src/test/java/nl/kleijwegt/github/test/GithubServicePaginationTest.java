package nl.kleijwegt.github.test;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import nl.kleijwegt.entity.github.classroom.GHStudentAssignment;
import nl.kleijwegt.service.GitHubService;

@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class GithubServicePaginationTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	@Test
	public void testGitHubPaginationForAssignment() throws IOException{
		
		List<GHStudentAssignment> studentAssignments = gitHubService.fetchAllStudentAssignmentsForAssignmentId(729275L);

		log.debug(studentAssignments.size());
	}
}
