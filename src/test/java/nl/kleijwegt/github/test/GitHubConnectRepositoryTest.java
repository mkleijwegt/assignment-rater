package nl.kleijwegt.github.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import nl.kleijwegt.service.GitHubService;

/**
* GitHubConnectRepositoryTest can be run to test the connection to a repository. It will list the files in there.
* 
* @author Mark Kleijwegt
* 
*/
@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class GitHubConnectRepositoryTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private GitHubService gitHubService;
	
	@Test
	public void testConnection() throws IOException{
		GHRepository repository = gitHubService.fetchRepository("ROCMondriaanTIN/sd23-p07-symfony-oefen-mkleijwegt");
		List<String> folders = new ArrayList<>();
		folders.add("/src/Controller");
		folders.add("/src/Entity");
		folders.add("/templates");
		List<GHContent> contents = gitHubService.fetchFilesFromRepository(repository, folders, "exercise-one");
		for(GHContent content : contents) {
			if(content.getName().endsWith(".php") || content.getName().endsWith(".html.twig")) {
				String fileContent = IOUtils.toString(content.read(), StandardCharsets.UTF_8);
				log.debug(content.getName());
				log.debug(fileContent);
			}
		}
	}
}
