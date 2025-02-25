package nl.kleijwegt.entity;

/**
* AssignmentFolder is a class that represents a folder. 
* It is used to decide on which folders to include from the repository.
* 
* @author Mark Kleijwegt
* 
*/
public class AssignmentFolder {
	
	private String folder;
	
	public AssignmentFolder() {
		
	}
	
	public AssignmentFolder(String folder) {
		this.folder = folder;
	}
	
	public String getFolder() {
		return folder;
	}
	
	public void setFolder(String folder) {
		this.folder = folder;
	}
}
