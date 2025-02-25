package nl.kleijwegt.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import nl.kleijwegt.entity.Assignment;

/**
* JsonAssignmentService is an interface that can be used to load in an assignment json file.
* 
* @author Mark Kleijwegt
* 
*/
public interface JsonAssignmentService {

	Assignment readFile(String assignmentFileName) throws StreamReadException, DatabindException, IOException, URISyntaxException;
	
	Assignment readFile(String assignmentFileName, String assignmentPath) throws StreamReadException, DatabindException, IOException, URISyntaxException;
	
}
