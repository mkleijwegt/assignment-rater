package nl.kleijwegt.github.test;

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

import nl.kleijwegt.entity.github.GHAssignment;
import nl.kleijwegt.entity.github.GHStudentAssignment;
import nl.kleijwegt.service.GitHubService;

@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class GithubServicePaginationTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	@Test
	public void testGitHubPaginationForAssignment() throws IOException{
		Long PER_PAGE = 50L;
		
		GHAssignment assignment = gitHubService.fetchAssignment(729275L);
		Long accepted = assignment.getAccepted();
		log.debug(accepted);
		
		Long maxPages = (accepted / PER_PAGE);
		if(assignment.getAccepted() % PER_PAGE > 0) {
			maxPages++;
		}
		log.debug(maxPages);
		
		Long page = 1L;
		List<GHStudentAssignment> studentAssignments = new ArrayList<GHStudentAssignment>();
		while(page <= maxPages) {
			studentAssignments.addAll(gitHubService.fetchStudentAssignments(assignment, page, PER_PAGE));
			page++;
		}
		
		log.debug(studentAssignments.size());
	}
}
