package nl.kleijwegt.service;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;

import nl.kleijwegt.entity.github.GHAssignment;
import nl.kleijwegt.entity.github.GHClassroom;
import nl.kleijwegt.entity.github.GHStudentAssignment;

/**
* GitHubService is an interface that can be used to call various GitHub API endpoints as well as some other convenience functions.
* 
* @author Mark Kleijwegt
* 
*/
public interface GitHubService {
	
	List<GHClassroom> fetchClassrooms() throws IOException;
	
	GHClassroom fetchClassroom(Long classroomId) throws IOException;
	
	GHAssignment fetchAssignment(Long assignmentId) throws IOException;
	
	List<GHAssignment> fetchAssignmentsForClassroom(GHClassroom classroom, 
			Long page, Long perPage) throws IOException;
	
	List<GHStudentAssignment> fetchAllStudentAssignmentsForAssignmentId(Long assignmentId) throws IOException;
	
	List<GHStudentAssignment> fetchStudentAssignmentsForAssignment(GHAssignment assignment, Long page, Long perPage) throws IOException;

	List<GHStudentAssignment> fetchStudentAssignmentsForAssignmentId(Long assignmentId, Long page, Long perPage) throws IOException;
	
	GHRepository fetchRepository(String fullName) throws IOException;
	
	List<GHContent> fetchFilesFromRepository(GHRepository repository, List<String> folders, 
			String branchName) throws IOException;

}
