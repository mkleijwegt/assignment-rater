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
	
	/**
	 * <p>Function that checks if a document or folder exists
	 * </p>
	 * @param path the path to the document or folder
	 * @return true when document or folder exists, false if not
	 */
	boolean documentOrFolderExists(String path);

	/**
	 * <p>Function that writes a PDF document to the provided path with the provided PdfTable 
	 * see {@link com.itextpdf.text.pdf.PdfPTable}
	 * </p>
	 * @param table the PdfTable to write to the PDF file
	 * @param path the path where the PDF should be written to
	 */
	void writeDocument(PdfPTable table, String path) throws FileNotFoundException, DocumentException;
	
	/**
	 * <p>Function that creates a PdfTable for the purpose of writing 
	 * the name of the student and give an introduction.
	 * </p>
	 * @param studentName the name of the student
	 * @param introduction an introduction text
	 * @return a PdfTable containing the studentName and introduction text
	 * see {@link com.itextpdf.text.pdf.PdfPTable}
	 */
	PdfPTable createAssignmentTable(String studentName, String introduction);
	
	/**
	 * <p>Function that adds a section to an existing PdfTable
	 * </p>
	 * @param table the table to add the section to
	 * @param headerText an introduction text
	 */
	void addTableSection(PdfPTable table, String headerText, String bodyText);

}
