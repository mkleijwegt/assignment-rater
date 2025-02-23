package nl.kleijwegt.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.kleijwegt.entity.Assignment;
import nl.kleijwegt.service.JsonAssignmentService;

@Service
public class JsonAssignmentServiceImpl implements JsonAssignmentService {
	
	private final static String ASSIGNMENT_PATH = "assignment-files";

	@Override
	public Assignment readFile(String assignmentFileName, String assignmentPath) throws StreamReadException, DatabindException, IOException, URISyntaxException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource(assignmentPath + System.getProperty("file.separator") + assignmentFileName);
		Path path = Paths.get(url.toURI());
		Assignment assignment = mapper.readValue(
		         path.toFile(), 
		         Assignment.class);
		return assignment;
	}

	@Override
	public Assignment readFile(String assignmentFileName)
			throws StreamReadException, DatabindException, IOException, URISyntaxException {
		return readFile(assignmentFileName, ASSIGNMENT_PATH);
	}
}
