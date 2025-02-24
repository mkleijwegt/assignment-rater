package nl.kleijwegt.service;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import nl.kleijwegt.entity.Assignment;

public interface AssignmentRaterService {
	
	
	void rateAssignment(Assignment assignment) throws IOException, DocumentException;

}
