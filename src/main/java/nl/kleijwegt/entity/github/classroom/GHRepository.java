package nl.kleijwegt.entity.github.classroom;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "repository")
@XmlAccessorType(XmlAccessType.FIELD)
public class GHRepository {

	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "full_name")
	private String fullName;
	
	@XmlElement(name = "html_url")
	private String htmlUrl;
	
	@XmlElement(name = "node_id")
	private String nodeId;
	
	@XmlElement(name = "private")
	private Boolean repoPrivate = true;
	
	@XmlElement(name = "default_branch")
	private String defaultBranch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Boolean getRepoPrivate() {
		return repoPrivate;
	}

	public void setRepoPrivate(Boolean repoPrivate) {
		this.repoPrivate = repoPrivate;
	}

	public String getDefaultBranch() {
		return defaultBranch;
	}

	public void setDefaultBranch(String defaultBranch) {
		this.defaultBranch = defaultBranch;
	}
}
