package nl.kleijwegt.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import nl.kleijwegt.entity.Assignment;

public interface JsonAssignmentService {

	
	public Assignment readFile(String assignmentFileName) throws StreamReadException, DatabindException, IOException, URISyntaxException;
	
	public Assignment readFile(String assignmentFileName, String assignmentPath) throws StreamReadException, DatabindException, IOException, URISyntaxException;
	
}
