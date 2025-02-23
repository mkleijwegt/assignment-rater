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

@Service
public class GitHubServiceImpl implements GitHubService {

	public static final String BASE_GITHUB_URL = "https://api.github.com/";

	@Override
	public List<GHClassroom> fetchClassrooms() throws IOException {
		Response response = callGitHubApi("classrooms");

		GHClassroom[] classrooms = response.readEntity(GHClassroom[].class);
		return Arrays.asList(classrooms);
	}
	
	@Override
	public GHClassroom fetchClassroom(Long classroomId) throws IOException {
		Response response = callGitHubApi("classrooms/" + classroomId);

		GHClassroom classroom = response.readEntity(GHClassroom.class);
		return classroom;
	}
	
	@Override
	public GHAssignment fetchAssignment(Long assignmentId) throws IOException {
		Response response = callGitHubApi("assignments/" + 	assignmentId);

		GHAssignment assignment = response.readEntity(GHAssignment.class);
		return assignment;
	}

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

	@Override
	public List<GHStudentAssignment> fetchStudentAssignments(GHAssignment assignment, Long page, Long perPage) throws IOException {
		return fetchStudentAssignments(assignment.getId(), page, perPage);

	}

	/*
	 * fetches accepted assignments for a specific assignment id.
	 */
	@Override
	public List<GHStudentAssignment> fetchStudentAssignments(Long assignmentId, Long page, Long perPage) throws IOException {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", page);
		queryParams.put("per_page", perPage);

		Response response = callGitHubApi("assignments/" + assignmentId + "/accepted_assignments", queryParams);

		GHStudentAssignment[] studentAssignments = response.readEntity(GHStudentAssignment[].class);

		return Arrays.asList(studentAssignments);
	}

	// this function fetches the secret key from the .github file in the home
	// directory of the user
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

	@Override
	public GHRepository fetchRepository(GitHub github, String fullName) throws IOException {
		return github.getRepository(fullName);
	}

	@Override
	public List<GHContent> fetchFilesFromRepository(GHRepository repository, List<String> folders, String branchName)
			throws IOException {
		List<GHContent> contentList = new ArrayList<>();
		for (String folder : folders) {
			contentList.addAll(getFilesFromFolder(folder, branchName, repository));
		}
		return contentList;
	}
	
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
	
	private Response callGitHubApi(String path) throws IOException {
		return callGitHubApi(path, new HashMap<>());
	}
	
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

}
