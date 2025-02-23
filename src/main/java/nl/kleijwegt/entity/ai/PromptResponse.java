package nl.kleijwegt.entity.ai;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;

@XmlRootElement(name = "promptResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PromptResponse {

	private String model;
	private Boolean stream = false;
	private Boolean done =  false;
	
	@XmlElement(name = "done_reason")
	private String doneReason;
	
	@XmlSchemaType(name="date")
	@XmlElement(name = "created_at")
	private Date createdAt;
	
	@XmlElement(name = "total_duration")
	private Long totalDuration = 0L;
	
	@XmlElement(name = "load_duration")
	private Long loadDuration = 0L;
	
	@XmlElement(name = "prompt_eval_count")
	private Long promptEvalCount = 0L;
	
	@XmlElement(name = "prompt_eval_duration")
	private Long promptEvalDuration = 0L;
	
	@XmlElement(name = "eval_count")
	private Long evalCount = 0L;
	
	@XmlElement(name = "eval_duration")
	private Long evalDuration = 0L;

	private Message message;

	public PromptResponse() {

	}

	public PromptResponse(String model, Boolean stream) {
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
	
	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	
	public String getDoneReason() {
		return doneReason;
	}
	
	public void setDoneReason(String doneReason) {
		this.doneReason = doneReason;
	}

	public Long getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Long totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Long getLoadDuration() {
		return loadDuration;
	}

	public void setLoadDuration(Long loadDuration) {
		this.loadDuration = loadDuration;
	}

	public Long getPromptEvalCount() {
		return promptEvalCount;
	}

	public void setPromptEvalCount(Long promptEvalCount) {
		this.promptEvalCount = promptEvalCount;
	}

	public Long getPromptEvalDuration() {
		return promptEvalDuration;
	}

	public void setPromptEvalDuration(Long promptEvalDuration) {
		this.promptEvalDuration = promptEvalDuration;
	}

	public Long getEvalCount() {
		return evalCount;
	}

	public void setEvalCount(Long evalCount) {
		this.evalCount = evalCount;
	}

	public Long getEvalDuration() {
		return evalDuration;
	}

	public void setEvalDuration(Long evalDuration) {
		this.evalDuration = evalDuration;
	}

	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "PromptResponse [model=" + model + ", stream=" + stream + "]";
	}
}
