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
import nl.kleijwegt.entity.github.GHClassroom;
import nl.kleijwegt.service.GitHubService;

@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class GithubServiceTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	@Test
	public void testGitHubConnection() throws IOException{
		
		//GHClassroom classroom = gitHubService.fetchClassroom(182819L);
		
		//log.debug(classroom.getId() + " - " + classroom.getName());
		
		GHClassroom classroomToUse = null;
		
		List<GHClassroom> classRooms = gitHubService.fetchClassrooms();
		log.debug(classRooms);
		for(GHClassroom classroom : classRooms) {
			log.debug(classroom.getId() + " - " + classroom.getName());
			if(classroom.getId().equals(182819L)) {
				classroomToUse = classroom;
			}
			
		}
		Integer MAX_PAGES = 6;
		Integer page = 0;
		Integer perPage = 100;
		List<GHAssignment> assignments = new ArrayList<>();
		while(page < MAX_PAGES) {
			assignments.addAll(gitHubService.fetchAssignmentsForClassroom(classroomToUse, page, perPage));
			page++;
		}
		for(GHAssignment assignment : assignments) {
			log.debug(assignment.getId() + " - " + assignment.getTitle());
		}
	}
}
