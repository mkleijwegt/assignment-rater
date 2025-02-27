package nl.kleijwegt.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* Assignment is a class that represents an assignment json file. 
* It is used to load in a json file providing all the parameters needed to rate a classroom assignment.
* 
* For instance: the assignmentId property is the id for the classroom assignment. 
* The branch property is used to load a specific branch in the assignment.
* The pdf properties are used for pdf generation.
* The aiModel specifies which AI model to use.
* The prompt is the overall prompt used to rate the assignment.
* The folders are used to decide on which folders to read from the GitHub repository.
* The fileTypes are used to decide on which fileTypes to include.
* 
* @author Mark Kleijwegt
* 
*/
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
	
	private BigDecimal temperature;
	
	@JsonProperty("top_k")
	private BigDecimal topK;
	
	@JsonProperty("top_p")
	private BigDecimal topP;
	
	private BigDecimal seed;
	
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
	
	public BigDecimal getTemperature() {
		return temperature;
	}
	
	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}
	
	public BigDecimal getTopK() {
		return topK;
	}
	
	public void setTopK(BigDecimal topK) {
		this.topK = topK;
	}
	
	public BigDecimal getTopP() {
		return topP;
	}
	
	public void setTopP(BigDecimal topP) {
		this.topP = topP;
	}
	
	public BigDecimal getSeed() {
		return seed;
	}
	
	public void setSeed(BigDecimal seed) {
		this.seed = seed;
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
