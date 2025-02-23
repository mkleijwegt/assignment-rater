package nl.kleijwegt.entity.ai;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "promptRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PromptRequest {

	private String model;
	private Boolean stream = false;
	private List<Message> messages;

	public PromptRequest() {

	}

	public PromptRequest(String model, Boolean stream) {
		this.model = model;
		this.stream = stream;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getStream() {
		return stream;
	}

	public void setStream(Boolean stream) {
		this.stream = stream;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "PromptRequest [model=" + model + ", stream=" + stream + "]";
	}
}
