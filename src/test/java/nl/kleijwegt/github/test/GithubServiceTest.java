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

/**
* GithubServiceTest can be run to see your classroom id's and classroom assignment id's
* Figuring out the id for an assignment can be a bit tricky. 
* It is also possible to figure out the assignment id from the classroom web interface by inspecting the source code in your browser.
* Go to the assignment page in the web interface and inspect the source code.
* Search for the string 'gh classroom clone student-repos -a' and you'll find the id of the assignment.
* 
* @author Mark Kleijwegt
* 
*/
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
