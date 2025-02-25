package nl.kleijwegt.entity.ai;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;


/**
* Message is a class representing a chat message that can be send when prompting ollama.
* In a message a certain role can be assumed like for instance 'user', 'teacher' or any other role you can think of.
* 
* @author Mark Kleijwegt
* 
*/
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

	private String role;
	private String content;

	public Message() {

	}

	public Message(String role, String content) {
		this.role = role;
		this.content = content;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [role=" + role + ", content=" + content + "]";
	}
}
