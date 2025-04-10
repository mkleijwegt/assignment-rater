package nl.kleijwegt.entity.ai;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;


/**
* SimplePromptRequest is a class representing a prompt that can be send to ollama.
* A prompt requires a prompt text and the model.
* Stream is set to false by default as we are only interested in the complete result.
* 
* Various options can be provided like for instance the temperature and repeat penalty.
* Please see the {@link nl.kleijwegt.entity.ai.Options} class to see what is supported.
* 
* @author Mark Kleijwegt
* 
*/
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
