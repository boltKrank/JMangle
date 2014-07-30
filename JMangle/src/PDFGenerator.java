import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator 
{
	ArrayList<File> images;
	Document pdfDocument;
	String outPutFilename = "PDFOutput\\default.pdf";
	
	public PDFGenerator()
	{
		images = new ArrayList<File>();		
	}
	
	public void addImage(File image)
	{
		images.add(image);
	}
	
	public void generatePDF()
	{
        try 
        {
        	pdfDocument = new Document();
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(outPutFilename));
            pdfDocument.open();
            if(images.size()>2)
            {
            	//add image to PDF
            }
            else
            {
            	pdfDocument.add(new Paragraph("No images to add."));
            }            
            pdfDocument.close(); // no need to close PDFwriter?

        } 
        catch (DocumentException e) 
        {
            e.printStackTrace();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
	}
}
