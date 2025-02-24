package nl.kleijwegt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* AssignmentFileType is a class that represents a file type. 
* It is used to decide on which fileTypes to include.
* 
* @author Mark Kleijwegt
* 
*/
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
