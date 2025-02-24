package nl.kleijwegt.service.impl;

import java.util.ArrayList;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.kleijwegt.entity.ai.AIModel;
import nl.kleijwegt.entity.ai.Message;
import nl.kleijwegt.entity.ai.Options;
import nl.kleijwegt.entity.ai.PromptRequest;
import nl.kleijwegt.entity.ai.PromptResponse;
import nl.kleijwegt.entity.ai.SimplePromptRequest;
import nl.kleijwegt.entity.ai.SimplePromptResponse;
import nl.kleijwegt.service.OllamaService;

@Service
public class OllamaServiceImpl implements OllamaService {
	
	public static final String OLLAMA_BASE_URL = "http://localhost:11434/api/";

	@Override
	public String ollamaChat(String prompt, AIModel model) {
		Client client = ClientBuilder.newClient(new ClientConfig());

		WebTarget webTarget = client.target(OLLAMA_BASE_URL).path("chat");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		PromptRequest promptMessage = new PromptRequest(model.name, false);
		Message message = new Message("user", prompt);
		promptMessage.setMessages(new ArrayList<>());
		promptMessage.getMessages().add(message);

		Response response = invocationBuilder.post(Entity.entity(promptMessage, MediaType.APPLICATION_JSON));

		PromptResponse promptResponse = response.readEntity(PromptResponse.class);

		return promptResponse.getMessage().getContent();
	}
	
	@Override
	public String ollamaGenerate(String prompt, AIModel model) {
		return ollamaGenerate(prompt, model, new Options());
	}

	@Override
	public String ollamaGenerate(String prompt, AIModel model, Options options) {
		Client client = ClientBuilder.newClient(new ClientConfig());

		WebTarget webTarget = client.target(OLLAMA_BASE_URL).path("generate");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		SimplePromptRequest promptMessage = new SimplePromptRequest(model.name, prompt, false);
		promptMessage.setOptions(options);
	
		Response response = invocationBuilder.post(Entity.entity(promptMessage, MediaType.APPLICATION_JSON));

		SimplePromptResponse promptResponse = response.readEntity(SimplePromptResponse.class);

		return promptResponse.getResponse();
	}





	

}
