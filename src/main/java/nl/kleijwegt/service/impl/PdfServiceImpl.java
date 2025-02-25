package nl.kleijwegt.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import nl.kleijwegt.service.PdfService;

/**
* PdfServiceImpl contains the implementation functions of the interface {@link nl.kleijwegt.service.PdfService}
* 
* @author Mark Kleijwegt
* 
*/
@Service
public class PdfServiceImpl implements PdfService {
	
	/**
	 * <p>Function that checks if a document or folder exists
	 * </p>
	 * @param path the path to the document or folder
	 * @return true when document or folder exists, false if not
	 */
	@Override
	public boolean documentOrFolderExists(String path) {
		Path filePath = Paths.get(path);
		//if the file is already there return true
		if (Files.exists(filePath)) {
			return true;
		}
		return false;
	}

	/**
	 * <p>Function that writes a PDF document to the provided path with the provided PdfTable 
	 * see {@link com.itextpdf.text.pdf.PdfPTable}
	 * </p>
	 * @param table the PdfTable to write to the PDF file
	 * @param path the path where the PDF should be written to
	 */
	@Override
	public void writeDocument(PdfPTable table, String path) throws FileNotFoundException, DocumentException {
		//if the file is already there we skip generation
		if (documentOrFolderExists(path)) {
			return;
		}
		
		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream(path));
		try {
			document.open();
		
			document.add(table);
		} finally {
			document.close();
		}
		
	}

	/**
	 * <p>Function that creates a PdfTable for the purpose of writing 
	 * the name of the student and give an introduction.
	 * </p>
	 * @param studentName the name of the student
	 * @param introduction an introduction text
	 * @return a PdfTable containing the studentName and introduction text
	 * see {@link com.itextpdf.text.pdf.PdfPTable}
	 */
	@Override
	public PdfPTable createAssignmentTable(String studentName, String introduction) {
		PdfPTable table = new PdfPTable(1);
		//making sure long text won't set a blank page in the PDF
		table.setSplitLate(false);
		addTableSection(table, "Student: " + studentName, introduction);
		return table;
	}

	/**
	 * <p>Function that adds a section to an existing PdfTable
	 * </p>
	 * @param table the table to add the section to
	 * @param headerText an introduction text
	 */
	@Override
	public void addTableSection(PdfPTable table, String headerText, String bodyText) {
		PdfPCell header = new PdfPCell();
		header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		header.setBorderWidth(2);
		header.setPhrase(new Phrase(headerText));
		
		table.addCell(header);
		table.addCell(bodyText);
		
	}
}
