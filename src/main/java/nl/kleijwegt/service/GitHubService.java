package nl.kleijwegt.service;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;

import nl.kleijwegt.entity.github.classroom.GHAssignment;
import nl.kleijwegt.entity.github.classroom.GHClassroom;
import nl.kleijwegt.entity.github.classroom.GHStudentAssignment;

/**
* GitHubService is an interface that can be used to call various GitHub API endpoints as well as some other convenience functions.
* 
* @author Mark Kleijwegt
* 
*/
public interface GitHubService {
	
	/**
	 * <p>Function that fetches all classrooms available in your organisation
	 * </p>
	 * @return List of GHClassroom objects. See {@link nl.kleijwegt.entity.github.classroom.GHClassroom}
	 */
	List<GHClassroom> fetchClassrooms() throws IOException;
	
	/**
	 * <p>Function that fetches a classroom by classRoomId
	 * </p>
	 * @param classRoomId the id of the classroom
	 * @return A GHClassroom object. See {@link nl.kleijwegt.entity.github.classroom.GHClassroom}
	 */
	GHClassroom fetchClassroom(Long classroomId) throws IOException;
	
	/**
	 * <p>Function that fetches a classroom assignment by assignmentId
	 * </p>
	 * @param assignmentId the id of the assignment
	 * @return A GHAssignment object. See {@link nl.kleijwegt.entity.github.classroom.GHAssignment}
	 */
	GHAssignment fetchAssignment(Long assignmentId) throws IOException;
	
	/**
	 * <p>Function that fetches all classroom assignments available in a classroom
	 * </p>
	 * @param classroom the classroom to fetch the assignments for
	 * @param page the page number to fetch the assignments from (start at 1)
	 * @param perPage the amount of assignments per page (100 is the GitHub API limit but 50 seems a better number in terms of reliance and speed)
	 * @return List of GHAssignment objects. See {@link nl.kleijwegt.entity.github.classroom.GHAssignment}
	 */
	List<GHAssignment> fetchAssignmentsForClassroom(GHClassroom classroom, 
			Long page, Long perPage) throws IOException;
	
	/**
	 * <p>Function that fetches all student assignments available in a classroom assignment
	 * </p>
	 * @param assignmentId the classroom assignment to fetch the student assignments for
	 * @return List of GHStudentAssignment objects. See {@link nl.kleijwegt.entity.github.classroom.GHStudentAssignment}
	 */
	List<GHStudentAssignment> fetchAllStudentAssignmentsForAssignmentId(Long assignmentId) throws IOException;
	
	/**
	 * <p>Function that fetches all student assignments available in a classroom assignment
	 * </p>
	 * @param assignment the classroom assignment to fetch the student assignments for
	 * @param page the page number to fetch the student assignments from (start at 1)
	 * @param perPage the amount of student assignments per page (100 is the GitHub API limit but 50 seems a better number in terms of reliance and speed)
	 * @return List of GHStudentAssignment objects. See {@link nl.kleijwegt.entity.github.classroom.GHStudentAssignment}
	 */
	List<GHStudentAssignment> fetchStudentAssignmentsForAssignment(GHAssignment assignment, Long page, Long perPage) throws IOException;

	/**
	 * <p>Function that fetches all student assignments available in a classroom assignment
	 * </p>
	 * @param assignmentId the classroom assignment id to fetch the student assignments for
	 * @param page the page number to fetch the student assignments from (start at 1)
	 * @param perPage the amount of student assignments per page (100 is the GitHub API limit but 50 seems a better number in terms of reliance and speed)
	 * @return List of GHStudentAssignment objects. See {@link nl.kleijwegt.entity.github.classroom.GHStudentAssignment}
	 */
	List<GHStudentAssignment> fetchStudentAssignmentsForAssignmentId(Long assignmentId, Long page, Long perPage) throws IOException;
	
	/**
	 * <p>Function that fetches a GitHub repository
	 * </p>
	 * @param fullName the full name of the repository
	 * @return GHRepository the GitHub repository. See {@link org.kohsuke.github.GHRepository}
	 */
	GHRepository fetchRepository(String fullName) throws IOException;
	
	/**
	 * <p>Function that fetches files from a GitHub repository recursively
	 * </p>
	 * @param repository the GitHub repository
	 * @param folders a List of folders to read from in the repository
	 * @param branchName the branch to read code from
	 * @return List of GHContent objects from the GitHub repository. See {@link org.kohsuke.github.GHContent}
	 */
	List<GHContent> fetchFilesFromRepository(GHRepository repository, List<String> folders, 
			String branchName) throws IOException;

}
