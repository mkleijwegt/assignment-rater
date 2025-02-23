package nl.kleijwegt.entity.github;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "studentAssignment")
@XmlAccessorType(XmlAccessType.FIELD)
public class GHStudent {

	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "login")
	private String login;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "avatar_url")
	private String avatarUrl;
	
	@XmlElement(name = "html_url")
	private String htmlUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	
}
