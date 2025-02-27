package nl.kleijwegt.entity.ai;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
* Options is a class representing the options that can be send when prompting ollama.
* Expand this class if you want to utilise more options in ollama.
* 
* @author Mark Kleijwegt
* 
*/
@XmlRootElement(name = "options")
@XmlAccessorType(XmlAccessType.FIELD)
public class Options {

	@XmlElement(name = "temperature")
	private BigDecimal temperature = new BigDecimal("0.8");
	
	@XmlElement(name = "top_k")
	private BigDecimal topK = new BigDecimal("20");
	
	@XmlElement(name = "top_p")
	private BigDecimal topP = new BigDecimal("0.9");
	
	@XmlElement(name = "seed")
	private BigDecimal seed = new BigDecimal("42");
	
	@XmlElement(name = "num_ctx")
	private Long numCtx = 20000L;
	
	@XmlElement(name = "repeat_penalty")
	private BigDecimal repeatPenalty = new BigDecimal("1.2");

	public Options() {

	}
	
	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public BigDecimal getTopK() {
		return topK;
	}

	public void setTopK(BigDecimal topK) {
		this.topK = topK;
	}

	public BigDecimal getTopP() {
		return topP;
	}

	public void setTopP(BigDecimal topP) {
		this.topP = topP;
	}
	
	public BigDecimal getSeed() {
		return seed;
	}
	
	public void setSeed(BigDecimal seed) {
		this.seed = seed;
	}
	
	public Long getNumCtx() {
		return numCtx;
	}
	
	public void setNumCtx(Long numCtx) {
		this.numCtx = numCtx;
	}

	public BigDecimal getRepeatPenalty() {
		return repeatPenalty;
	}

	public void setRepeatPenalty(BigDecimal repeatPenalty) {
		this.repeatPenalty = repeatPenalty;
	}
	
	@Override
	public String toString() {
		return "Options [temperature=" + temperature + ", repeatPenalty=" + repeatPenalty + "]";
	}
}
