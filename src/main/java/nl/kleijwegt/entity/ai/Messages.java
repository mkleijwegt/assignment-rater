package nl.kleijwegt.entity.ai;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
* Messages is a class representing multiple chat messages that can be send when prompting ollama.
* Please see the {@link nl.kleijwegt.entity.ai.Message} class for the message.
* 
* @author Mark Kleijwegt
* 
*/
@XmlRootElement(name = "messages")
@XmlAccessorType(XmlAccessType.FIELD)
public class Messages {

	@XmlElement(name = "message")
	private List<Message> messages = new ArrayList<>();

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
