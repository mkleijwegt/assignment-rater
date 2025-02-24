package nl.kleijwegt.entity;

/**
* AssignmentQuestion is a class that represents a question. 
* It is used to assess coding questions.
* 
* @author Mark Kleijwegt
* 
*/
public class AssignmentQuestion {

	private String title;
	
	private String text;
	
	private String repositoryDirectory;
	
	private String repositoryFile;

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRepositoryDirectory() {
		return repositoryDirectory;
	}

	public void setRepositoryDirectory(String repositoryDirectory) {
		this.repositoryDirectory = repositoryDirectory;
	}

	public String getRepositoryFile() {
		return repositoryFile;
	}

	public void setRepositoryFile(String repositoryFile) {
		this.repositoryFile = repositoryFile;
	}
}
