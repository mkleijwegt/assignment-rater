package nl.kleijwegt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Assignment {

	@JsonProperty("assignment_id")
	private Long assignmentId;
	
	private String title;
	
	private List<AssignmentQuestion> questions;

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AssignmentQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AssignmentQuestion> questions) {
		this.questions = questions;
	}



	

	
}
