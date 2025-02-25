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

/**
* JsonAssignmentServiceImpl contains the implementation functions of the interface {@link nl.kleijwegt.service.JsonAssignmentService}
* It contains functions that can be used to read local json files containing assignment information.
* 
* @author Mark Kleijwegt
* 
*/
@Service
public class JsonAssignmentServiceImpl implements JsonAssignmentService {
	
	private final static String ASSIGNMENT_PATH = "assignment-files";

	/**
	 * <p>Function that reads a local json file from a given path
	 * </p>
	 * @param assignmentFileName the name of the json file
	 * @param assignmentPath the path where the file is located
	 * @return Assignment object representing the json file. See {@link nl.kleijwegt.entity.Assignment}
	 */
	@Override
	public Assignment readFile(String assignmentFileName, String assignmentPath) throws StreamReadException, DatabindException, IOException, URISyntaxException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource(assignmentPath + System.getProperty("file.separator") + assignmentFileName);
		Path path = Paths.get(url.toURI());
		Assignment assignment = mapper.readValue(path.toFile(), Assignment.class);
		return assignment;
	}

	/**
	 * <p>Function that reads a local json file from the standard path
	 * </p>
	 * @param assignmentFileName the name of the json file
	 * @return Assignment object representing the json file. See {@link nl.kleijwegt.entity.Assignment}
	 */
	@Override
	public Assignment readFile(String assignmentFileName)
			throws StreamReadException, DatabindException, IOException, URISyntaxException {
		return readFile(assignmentFileName, ASSIGNMENT_PATH);
	}
}
