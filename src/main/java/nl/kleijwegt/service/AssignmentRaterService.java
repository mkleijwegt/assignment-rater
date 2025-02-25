package nl.kleijwegt.service;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import nl.kleijwegt.entity.Assignment;

/**
* AssignmentRaterService is an interface that can be used to rate an assignment.
* Please see the {@link nl.kleijwegt.entity.Assignment} class for the details of the assignment it can rate.
* 
* @author Mark Kleijwegt
* 
*/
public interface AssignmentRaterService {
	
	void rateAssignment(Assignment assignment) throws IOException, DocumentException;

}
