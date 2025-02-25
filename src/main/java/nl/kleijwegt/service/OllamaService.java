package nl.kleijwegt.service;

import nl.kleijwegt.entity.ai.AIModel;
import nl.kleijwegt.entity.ai.Options;

/**
* OllamaService is an interface that can be used to call the ollama server with a prompt to run on a specific AI model.
* 
* @author Mark Kleijwegt
* 
*/
public interface OllamaService {

	/**
	 * <p>Function that sends a prompt to the chat endpoint of the ollama server
	 * </p>
	 * @param prompt the prompt used to chat with the ollama server
	 * @param model the AI model to use by the ollama server
	 * @return A String containing the response of the ollama server
	 */
	String ollamaChat(String prompt, AIModel model);
	
	/**
	 * <p>Function that sends a prompt to the generate endpoint of the ollama server with default options
	 * </p>
	 * @param prompt the prompt used by the ollama server to generate a response
	 * @param model the AI model to use by the ollama server
	 * @return A String containing the response of the ollama server
	 */
	String ollamaGenerate(String prompt, AIModel model);
	
	/**
	 * <p>Function that sends a prompt to the generate endpoint of the ollama server
	 * </p>
	 * @param prompt the prompt used by the ollama server to generate a response
	 * @param model the AI model to use by the ollama server
	 * @param options instructing ollama server what options to use
	 * @return A String containing the response of the ollama server
	 */
	String ollamaGenerate(String prompt, AIModel model, Options options);

}
