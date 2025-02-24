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

@Service
public class PdfServiceImpl implements PdfService {
	
	@Override
	public boolean documentOrFolderExists(String path) {
		Path filePath = Paths.get(path);
		//if the file is already there return true
		if (Files.exists(filePath)) {
			return true;
		}
		return false;
	}

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

	@Override
	public PdfPTable createAssignmentTable(String studentName, String introduction) {
		PdfPTable table = new PdfPTable(1);
		//making sure long text won't set a blank page in the PDF
		table.setSplitLate(false);
		// gray header is generated where the student login name is written
		PdfPCell header = new PdfPCell();
		header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		header.setBorderWidth(2);
		header.setPhrase(new Phrase("Student: " + studentName));
		table.addCell(header);
		table.addCell(introduction);
		return table;
	}

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
