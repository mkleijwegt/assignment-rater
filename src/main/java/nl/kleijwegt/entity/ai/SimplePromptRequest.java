package nl.kleijwegt.entity.ai;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "simplePromptRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimplePromptRequest {

	private String model;
	private Boolean stream = false;
	private String prompt;
	
	private Options options;

	public SimplePromptRequest() {

	}

	public SimplePromptRequest(String model, String prompt, Boolean stream) {
		this(model, prompt, stream, null);
	}
	
	public SimplePromptRequest(String model, String prompt, Boolean stream, Options options) {
		this.model = model;
		this.stream = stream;
		this.prompt = prompt;
		this.options = options;
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
	
	public String getPrompt() {
		return prompt;
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "SimplePromptRequest [model=" + model + ", stream=" + stream + "]";
	}
}
