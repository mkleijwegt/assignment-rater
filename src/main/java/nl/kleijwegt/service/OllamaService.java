package nl.kleijwegt.service;

import nl.kleijwegt.entity.ai.AIModel;
import nl.kleijwegt.entity.ai.Options;

public interface OllamaService {

	/*
	 * This function calls the Ollama API with the provided prompt. This function
	 * returns the feedback as provided by the AI model.
	 */
	String ollamaChat(String prompt, AIModel model);
	
	String ollamaGenerate(String prompt, AIModel model, Options options);

}
