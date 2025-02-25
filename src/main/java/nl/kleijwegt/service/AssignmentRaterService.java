package nl.kleijwegt.service;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import nl.kleijwegt.entity.Assignment;
import nl.kleijwegt.entity.github.classroom.GHStudentAssignment;

/**
* AssignmentRaterService is an interface that can be used to rate an assignment.
* Please see the {@link nl.kleijwegt.entity.Assignment} class for the details of the assignment it can rate.
* 
* @author Mark Kleijwegt
* 
*/
public interface AssignmentRaterService {
	
	/**
	 * <p>Function that rates all student assignments for a given classroom assignment according to the provided instructions in the 
	 * Assignment object. See {@link nl.kleijwegt.entity.Assignment}.
	 * </p>
	 * @param assignment the assignment object containing the instructions
	 */
	void rateAssignment(Assignment assignment) throws IOException, DocumentException;
	
	/**
	 * <p>Function that rates a student assignment according to the provided instructions in the 
	 * Assignment object. See {@link nl.kleijwegt.entity.Assignment}.
	 * </p>
	 * @param assignment the assignment object containing the instructions
	 */
	void rateStudentAssignment(Assignment assignment, GHStudentAssignment studentAssignment) throws IOException, DocumentException;
	
	/**
	 * <p>Function that rates a student assignment according to the provided instructions in the 
	 * Assignment object. See {@link nl.kleijwegt.entity.Assignment}.
	 * </p>
	 * @param assignment the assignment object containing the instructions
	 * @param repositoryName the repository to rate
	 * @param assignmentTitle the title to use for the assignment
	 * @param studentLogin the login or name of the student
	 * 
	 */
	void rateStudentAssignment(Assignment assignment, String repositoryName, String assignmentTitle, String studentLogin)
			throws IOException, DocumentException;

}
