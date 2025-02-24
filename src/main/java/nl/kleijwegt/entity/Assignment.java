package nl.kleijwegt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Assignment {

	@JsonProperty("assignment_id")
	private Long assignmentId;
	
	private String title;
	
	@JsonProperty("ai_model")
	private String aiModel;
	
	private List<AssignmentFolder> folders;
	
	@JsonProperty("file_types")
	private List<AssignmentFileType> fileTypes;
	
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
	
	public String getAiModel() {
		return aiModel;
	}
	
	public void setAiModel(String aiModel) {
		this.aiModel = aiModel;
	}
	
	public List<AssignmentFolder> getFolders() {
		return folders;
	}
	
	public void setFolders(List<AssignmentFolder> folders) {
		this.folders = folders;
	}
	
	public List<AssignmentFileType> getFileTypes() {
		return fileTypes;
	}
	
	public void setFileTypes(List<AssignmentFileType> fileTypes) {
		this.fileTypes = fileTypes;
	}

	public List<AssignmentQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AssignmentQuestion> questions) {
		this.questions = questions;
	}



	

	
}
