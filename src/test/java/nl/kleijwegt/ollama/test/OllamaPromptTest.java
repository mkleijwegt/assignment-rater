package nl.kleijwegt.ollama.test;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.itextpdf.text.DocumentException;

import nl.kleijwegt.entity.ai.AIModel;
import nl.kleijwegt.entity.ai.Options;
import nl.kleijwegt.service.OllamaService;

/**
* OllamaPromptTest can be run to test a prompt against the ollama server with the specified AI model.
* 
* @author Mark Kleijwegt
* 
*/
@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class OllamaPromptTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private OllamaService ollamaService;

	@Test
	public void testPrompt() throws IOException, DocumentException{
		Options options = new Options();
		options.setTemperature(new BigDecimal("0.1"));
		
		
		String response = ollamaService.ollamaGenerate("Schrijf een programma in PHP die voor een viercijferig getal het aantal stappen tot 6174 berekend.", 
				AIModel.QWEN_2_5_CODER_7B,
				options);
		log.debug(response);
	}
}
