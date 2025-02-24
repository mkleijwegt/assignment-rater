package nl.kleijwegt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentFileType {
	
	@JsonProperty("file_type")
	private String fileType;
	
	public String getFileType() {
		return fileType;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
