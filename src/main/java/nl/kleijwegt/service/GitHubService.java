package nl.kleijwegt.service;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import nl.kleijwegt.entity.github.GHAssignment;
import nl.kleijwegt.entity.github.GHClassroom;
import nl.kleijwegt.entity.github.GHStudentAssignment;

public interface GitHubService {
	
	List<GHClassroom> fetchClassrooms() throws IOException;
	
	GHClassroom fetchClassroom(Long classroomId) throws IOException;
	
	GHAssignment fetchAssignment(Long assignmentId) throws IOException;
	
	List<GHAssignment> fetchAssignmentsForClassroom(GHClassroom classroom, 
			Long page, Long perPage) throws IOException;
	
	List<GHStudentAssignment> fetchAllStudentAssignmentsForAssignmentId(Long assignmentId) throws IOException;
	
	List<GHStudentAssignment> fetchStudentAssignmentsForAssignment(GHAssignment assignment, Long page, Long perPage) throws IOException;

	List<GHStudentAssignment> fetchStudentAssignmentsForAssignmentId(Long assignmentId, Long page, Long perPage) throws IOException;
	
	GHRepository fetchRepository(GitHub github, String fullName) throws IOException;
	
	List<GHContent> fetchFilesFromRepository(GHRepository repository, List<String> folders, 
			String branchName) throws IOException;

}
