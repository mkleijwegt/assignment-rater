package nl.kleijwegt.service;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

public interface PdfService {
	
	Boolean documentExists(String path);

	void writeDocument(PdfPTable table, String path) throws FileNotFoundException, DocumentException;
	
	PdfPTable createAssignmentTable(String studentName, String introduction);
	
	void addTableSection(PdfPTable table, String headerText, String bodyText);

}
