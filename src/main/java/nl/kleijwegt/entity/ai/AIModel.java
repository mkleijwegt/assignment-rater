package nl.kleijwegt.entity.ai;


/**
* AIModel is an enum that lists AI models that can be used with ollama.
* New models can be added easily by expanding the enum.
* 
* @author Mark Kleijwegt
* 
*/
public enum AIModel {
	DEEPSEEK_R1_14B("deepseek-r1:14b"),
	DEEPSEEK_R1_32B("deepseek-r1:32b"),
	OLLAMA_3_2("llama3.2:latest"),
	QWEN_2_5("qwen2.5:7b"),
	QWEN_2_5_CODER_7B("qwen2.5-coder:latest"),
	QWEN_2_5_CODER_14B("qwen2.5-coder:14b"),
	QWEN_2_5_CODER_32B("qwen2.5-coder:32b");
	
	public final String name;
	
	private AIModel(String name) {
		this.name = name;
	}
	
}
