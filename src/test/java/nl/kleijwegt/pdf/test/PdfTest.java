package nl.kleijwegt.pdf.test;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import nl.kleijwegt.service.PdfService;


@ContextConfiguration({"classpath:/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class PdfTest {
	
	final static Logger log = LogManager.getLogger();
	
	@Autowired
	private PdfService pdfService;
	
	@Test
	public void writePdf() throws IOException, DocumentException{
		
		PdfPTable table = pdfService.createAssignmentTable("Test Student", "Please find the feedback on assignment x below.");
		
		pdfService.addTableSection(table, "Header 1", "Lorem Ipsum");
		pdfService.addTableSection(table, "Header 2", "Lorem Ipsum");
		pdfService.addTableSection(table, "Header 3", "Lorem Ipsum Dolor");
		pdfService.addTableSection(table, "Header 4", "Lorem Ipsum");
		pdfService.addTableSection(table, "Header 5", "Lorem Ipsum");
		
		
		pdfService.writeDocument(table, "test.pdf");
	}
}
