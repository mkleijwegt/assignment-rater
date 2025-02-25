package nl.kleijwegt.service;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

/**
* PdfService is an interface that can be used to generate PDF files and add sections to a PDF.
* 
* @author Mark Kleijwegt
* 
*/
public interface PdfService {
	
	boolean documentOrFolderExists(String path);

	void writeDocument(PdfPTable table, String path) throws FileNotFoundException, DocumentException;
	
	PdfPTable createAssignmentTable(String studentName, String introduction);
	
	void addTableSection(PdfPTable table, String headerText, String bodyText);

}
