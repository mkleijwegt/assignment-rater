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
	
	public List<GHAssignment> fetchAssignmentsForClassroom(GHClassroom classroom, 
			Integer page, Integer perPage) throws IOException;
	
	List<GHStudentAssignment> fetchStudentAssignments(GHAssignment assignment, Integer page, Integer perPage) throws IOException;

	List<GHStudentAssignment> fetchStudentAssignments(Long assignmentId, Integer page, Integer perPage) throws IOException;
	
	GHRepository fetchRepository(GitHub github, String fullName) throws IOException;
	
	List<GHContent> fetchFilesFromRepository(GHRepository repository, List<String> folders, 
			String branchName) throws IOException;

}
