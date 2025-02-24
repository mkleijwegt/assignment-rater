package nl.kleijwegt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Assignment {

	@JsonProperty("assignment_id")
	private Long assignmentId;
	
	private String title;
	
	private String branch;
	
	@JsonProperty("pdf_heading")
	private String pdfHeading;
	
	@JsonProperty("pdf_file_heading")
	private String pdfFileHeading;
	
	@JsonProperty("pdf_prompt_heading")
	private String pdfPromptHeading;
	
	@JsonProperty("pdf_feedback_heading")
	private String pdfFeedbackHeading;
	
	@JsonProperty("pdf_result_folder")
	private String pdfResultFolder;
	
	@JsonProperty("ai_model")
	private String aiModel;
	
	private String prompt;
	
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
	
	public String getBranch() {
		return branch;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getPdfHeading() {
		return pdfHeading;
	}

	public void setPdfHeading(String pdfHeading) {
		this.pdfHeading = pdfHeading;
	}

	public String getPdfFileHeading() {
		return pdfFileHeading;
	}

	public void setPdfFileHeading(String pdfFileHeading) {
		this.pdfFileHeading = pdfFileHeading;
	}
	
	public String getPdfPromptHeading() {
		return pdfPromptHeading;
	}
	
	public void setPdfPromptHeading(String pdfPromptHeading) {
		this.pdfPromptHeading = pdfPromptHeading;
	}
	
	public String getPdfFeedbackHeading() {
		return pdfFeedbackHeading;
	}
	
	public void setPdfFeedbackHeading(String pdfFeedbackHeading) {
		this.pdfFeedbackHeading = pdfFeedbackHeading;
	}
	
	public String getPdfResultFolder() {
		return pdfResultFolder;
	}
	
	public void setPdfResultFolder(String pdfResultFolder) {
		this.pdfResultFolder = pdfResultFolder;
	}

	public String getAiModel() {
		return aiModel;
	}
	
	public void setAiModel(String aiModel) {
		this.aiModel = aiModel;
	}
	
	public String getPrompt() {
		return prompt;
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
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
