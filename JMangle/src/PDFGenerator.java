import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

public class PDFGenerator 
{
	ArrayList<Image> images;
	Document pdfDocument;
	String outPutFilename = "PDFOutput\\default.pdf";
	String outputDirectory = ".";
	
	public PDFGenerator()
	{
		images = new ArrayList<Image>();		
	}
	
	public void setOutputDirectory(String outputDirectory)
	{
		this.outputDirectory = outputDirectory;
	}
	
	public void setOutputFilename(String outputFilename)
	{
		this.outPutFilename = outputFilename;
	}
	
	public void addImage(File image)
	{
		
		try
		{
			Image tmpImage = Image.getInstance(image.getAbsolutePath());
			images.add(tmpImage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        
	}
	
	public void generatePDF()
	{
        try 
        {
        	pdfDocument = new Document();
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(outPutFilename));
            pdfDocument.open();
            if(images.size()>1)
            {
            	for(int i=0;i<images.size();i++)
            	{
            		pdfDocument.add(images.get(i));
            		//TODO Add page break
            	}
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
