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

import nl.kleijwegt.entity.github.classroom.GHAssignment;
import nl.kleijwegt.entity.github.classroom.GHClassroom;
import nl.kleijwegt.service.GitHubService;

@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class GithubServiceTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	//run this test to see your classrooms and classroom assignment id's
	@Test
	public void testGitHubConnection() throws IOException{
		
		GHClassroom classroomToUse = null;
		
		List<GHClassroom> classRooms = gitHubService.fetchClassrooms();
		log.debug(classRooms);
		for(GHClassroom classroom : classRooms) {
			log.debug(classroom.getId() + " - " + classroom.getName());
			//change this id to the classroom id you want to use
			if(classroom.getId().equals(182819L)) {
				classroomToUse = classroom;
			}
			
		}
		Long MAX_PAGES = 5L;
		Long page = 1L;
		Long perPage = 50L;
		List<GHAssignment> assignments = new ArrayList<>();
		while(page <= MAX_PAGES) {
			assignments.addAll(gitHubService.fetchAssignmentsForClassroom(classroomToUse, page, perPage));
			page++;
		}
		for(GHAssignment assignment : assignments) {
			log.debug(assignment.getId() + " - " + assignment.getTitle());
		}
	}
}
