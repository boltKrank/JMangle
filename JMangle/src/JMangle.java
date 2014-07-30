import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

public class JMangle extends JFrame implements ActionListener
{

  private static final long serialVersionUID = 1L;
  
  JPanel pane = new JPanel();
  JButton browse_button;
  JButton run_button;
  File parsing_directory;
  PDFGenerator pdfGenerator;
    
  JMangle() // the frame constructor method
  {
    super("Mangle Java");
    setBounds(100,100,300,100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container con = this.getContentPane(); // inherit main frame
    con.add(pane); // add the panel to frame

    //add browse button
    browse_button = new JButton("Browse");
    browse_button.setMnemonic('b');
    browse_button.addActionListener(this);
    pane.add(browse_button);
            
    //add run button
    run_button = new JButton("Run");
    run_button.setMnemonic('r');
    run_button.addActionListener(this);
    pane.add(run_button);
    
    // display this frame
    setVisible(true);    
    
    // initialise generator
    pdfGenerator = new PDFGenerator();
    
  }
  
  public void actionPerformed(ActionEvent event)
  {    
    
    if (event.getSource() == browse_button) 
    {
        JFileChooser jfc = new JFileChooser(new File("."));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Demonstrate "Open" dialog:
              
        if (jfc.showOpenDialog(JMangle.this) == JFileChooser.APPROVE_OPTION) 
        {
        	
        	File[] listOfFiles = jfc.getSelectedFile().listFiles();
        	
        	//Add all files of image type to pdfGenerator
        	for(int i=0;i < listOfFiles.length; i++)
        	{
        		if(listOfFiles[i].getName().endsWith("jpeg")||listOfFiles[i].getName().endsWith("jpg"))
        		{
        			System.out.println("Adding " + listOfFiles[i].getName());
        			pdfGenerator.addImage(listOfFiles[i]);
        		}
        	}

        	
        	parsing_directory = jfc.getSelectedFile();
        }
    }
    
    else if(event.getSource() == run_button)
    {
    	//Run action
    	//TODO: Change this to run application
    	if(parsing_directory!=null)
    	{
    		System.out.println("Running " + parsing_directory.getName());
    		pdfGenerator.generatePDF();
    	}
    	else
    	{
    		System.out.println("Parsing directory is null");
    	}
    }
  }
    
  public static void main(String args[])
  {
	  new JMangle();
  }
}