package nl.kleijwegt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.http.HttpHeaders;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.kleijwegt.entity.github.GHAssignment;
import nl.kleijwegt.entity.github.GHClassroom;
import nl.kleijwegt.entity.github.GHStudentAssignment;
import nl.kleijwegt.service.GitHubService;

/**
* GitHubServiceImpl contains the implementation functions of the interface {@link nl.kleijwegt.service.GitHubService}
* 
* @author Mark Kleijwegt
* 
*/
@Service
public class GitHubServiceImpl implements GitHubService {

	public static final String BASE_GITHUB_URL = "https://api.github.com/";

	/**
	 * <p>Function that fetches all classrooms available in your organisation
	 * </p>
	 * @return List of GHClassroom objects. See {@link nl.kleijwegt.entity.github.GHClassroom}
	 */
	@Override
	public List<GHClassroom> fetchClassrooms() throws IOException {
		Response response = callGitHubApi("classrooms");

		GHClassroom[] classrooms = response.readEntity(GHClassroom[].class);
		return Arrays.asList(classrooms);
	}
	
	/**
	 * <p>Function that fetches a classroom by classRoomId
	 * </p>
	 * @param classRoomId the id of the classroom
	 * @return A GHClassroom object. See {@link nl.kleijwegt.entity.github.GHClassroom}
	 */
	@Override
	public GHClassroom fetchClassroom(Long classroomId) throws IOException {
		Response response = callGitHubApi("classrooms/" + classroomId);

		GHClassroom classroom = response.readEntity(GHClassroom.class);
		return classroom;
	}
	
	/**
	 * <p>Function that fetches a classroom assignment by assignmentId
	 * </p>
	 * @param assignmentId the id of the assignment
	 * @return A GHAssignment object. See {@link nl.kleijwegt.entity.github.GHAssignment}
	 */
	@Override
	public GHAssignment fetchAssignment(Long assignmentId) throws IOException {
		Response response = callGitHubApi("assignments/" + 	assignmentId);

		GHAssignment assignment = response.readEntity(GHAssignment.class);
		return assignment;
	}

	/**
	 * <p>Function that fetches all classroom assignments available in a classroom
	 * </p>
	 * @param classroom the classroom to fetch the assignments for
	 * @param page the page number to fetch the assignments from (start at 1)
	 * @param perPage the amount of assignments per page (100 is the GitHub API limit but 50 seems a better number in terms of reliance and speed)
	 * @return List of GHAssignment objects. See {@link nl.kleijwegt.entity.github.GHAssignment}
	 */
	@Override
	public List<GHAssignment> fetchAssignmentsForClassroom(GHClassroom classroom, 
			Long page, Long perPage) throws IOException {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", page);
		queryParams.put("per_page", perPage);
		
		Response response = callGitHubApi("classrooms/" + classroom.getId() + "/assignments", queryParams);

		GHAssignment[] assignments = response.readEntity(GHAssignment[].class);

		return Arrays.asList(assignments);
	}
	
	/**
	 * <p>Function that fetches all student assignments available in a classroom assignment
	 * </p>
	 * @param assignmentId the classroom assignment to fetch the student assignments for
	 * @return List of GHStudentAssignment objects. See {@link nl.kleijwegt.entity.github.GHStudentAssignment}
	 */
	@Override
	public List<GHStudentAssignment> fetchAllStudentAssignmentsForAssignmentId(Long assignmentId) throws IOException {
		final Long PER_PAGE = 50L;
		
		GHAssignment assignment = fetchAssignment(assignmentId);
		Long accepted = assignment.getAccepted();
		
		//maxPages is number of accepted assignments divided by per page. (i.e. 2 = 100 / 50).
		Long maxPages = (accepted / PER_PAGE);
		//we add 1 to maxPages in case we need another page. (i.e. 1 = 90 / 50).
		if(assignment.getAccepted() % PER_PAGE > 0) {
			maxPages++;
		}
		
		List<GHStudentAssignment> studentAssignments = new ArrayList<GHStudentAssignment>();
		for(Long page = 1L; page <= maxPages; page++) {
			studentAssignments.addAll(fetchStudentAssignmentsForAssignment(assignment, page, PER_PAGE));
		}
		return studentAssignments;
	}

	/**
	 * <p>Function that fetches all student assignments available in a classroom assignment
	 * </p>
	 * @param assignment the classroom assignment to fetch the student assignments for
	 * @param page the page number to fetch the student assignments from (start at 1)
	 * @param perPage the amount of student assignments per page (100 is the GitHub API limit but 50 seems a better number in terms of reliance and speed)
	 * @return List of GHStudentAssignment objects. See {@link nl.kleijwegt.entity.github.GHStudentAssignment}
	 */
	@Override
	public List<GHStudentAssignment> fetchStudentAssignmentsForAssignment(GHAssignment assignment, Long page, Long perPage) throws IOException {
		return fetchStudentAssignmentsForAssignmentId(assignment.getId(), page, perPage);

	}

	/**
	 * <p>Function that fetches all student assignments available in a classroom assignment
	 * </p>
	 * @param assignmentId the classroom assignment id to fetch the student assignments for
	 * @param page the page number to fetch the student assignments from (start at 1)
	 * @param perPage the amount of student assignments per page (100 is the GitHub API limit but 50 seems a better number in terms of reliance and speed)
	 * @return List of GHStudentAssignment objects. See {@link nl.kleijwegt.entity.github.GHStudentAssignment}
	 */
	@Override
	public List<GHStudentAssignment> fetchStudentAssignmentsForAssignmentId(Long assignmentId, Long page, Long perPage) throws IOException {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", page);
		queryParams.put("per_page", perPage);

		Response response = callGitHubApi("assignments/" + assignmentId + "/accepted_assignments", queryParams);

		GHStudentAssignment[] studentAssignments = response.readEntity(GHStudentAssignment[].class);

		return Arrays.asList(studentAssignments);
	}

	/**
	 * <p>Function that fetches a GitHub repository
	 * </p>
	 * @param fullName the full name of the repository
	 * @return GHRepository the GitHub repository. See {@link org.kohsuke.github.GHRepository}
	 */
	@Override
	public GHRepository fetchRepository(String fullName) throws IOException {
		GitHub github = GitHubBuilder.fromPropertyFile().build();
		return github.getRepository(fullName);
	}

	/**
	 * <p>Function that fetches files from a GitHub repository recursively
	 * </p>
	 * @param repository the GitHub repository
	 * @param folders a List of folders to read from in the repository
	 * @param branchName the branch to read code from
	 * @return List of GHContent objects from the GitHub repository. See {@link org.kohsuke.github.GHContent}
	 */
	@Override
	public List<GHContent> fetchFilesFromRepository(GHRepository repository, List<String> folders, String branchName)
			throws IOException {
		List<GHContent> contentList = new ArrayList<>();
		for (String folder : folders) {
			contentList.addAll(getFilesFromFolder(folder, branchName, repository));
		}
		return contentList;
	}
	
	/**
	 * <p>Function that fetches files from a folder in a GitHub repository recursively
	 * </p>
	 * @param repository the GitHub repository
	 * @param folder a folder to read from in the repository
	 * @param branchName the branch to read code from
	 * @return List of GHContent objects from the GitHub repository. See {@link org.kohsuke.github.GHContent}
	 */
	private List<GHContent> getFilesFromFolder(String folder, String branchName,
			GHRepository repository) throws IOException {
		List<GHContent> folderFiles = repository.getDirectoryContent(folder, branchName);
		List<GHContent> files = new ArrayList<>();
		for(GHContent content : folderFiles) {
			if(content.isFile()) {
				files.add(content);
			} else {
				files.addAll(getFilesFromFolder(content.getPath(), branchName, repository));
			}
		}
		return files;
	}
	
	/**
	 * <p> Convenience function that calls the GitHub API without queryParams
	 * </p>
	 * @param path the API endpoint to call
	 * @return Response containing the response message from the GitHub API. See {@link jakarta.ws.rs.core.Response}
	 */
	private Response callGitHubApi(String path) throws IOException {
		return callGitHubApi(path, new HashMap<>());
	}
	
	/**
	 * <p> Function that calls the GitHub API with queryParams
	 * </p>
	 * @param path the API endpoint to call
	 * @param queryParams the map containing query parameters
	 * @return Response containing the response message from the GitHub API. See {@link jakarta.ws.rs.core.Response}
	 */
	private Response callGitHubApi(String path, Map<String, Object> queryParams) throws IOException {
		Client client = ClientBuilder.newClient(new ClientConfig());

		WebTarget webTarget = client.target(BASE_GITHUB_URL).path(path);

		for(String key : queryParams.keySet()) {
			webTarget = webTarget.queryParam(key, queryParams.get(key));
		}
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + getGitHubKeyFromPropertyFile());

		Response response = invocationBuilder.get();
		
		return response;
	}
	
	/**
	 * <p>Function that fetches the secret key from the .github file in the home directory of the user
	 * </p>
	 * @return String containing the secret key used to authenticate with the GitHub API
	 */
	private String getGitHubKeyFromPropertyFile() throws IOException {
		File homeDir = new File(System.getProperty("user.home"));
		File propertyFile = new File(homeDir, ".github");

		Properties props = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(propertyFile);
			props.load(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
		String oauth = props.getProperty("oauth");

		return oauth;
	}
}
