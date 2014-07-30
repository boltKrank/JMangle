/**
 * TODO: 
 * 
 * Remove Run button ? or maybe create an ArrayList and a flag for multiple directories or not
 * 
 * Need to add sort algorithm, that notices the difference between 01 and 1, 
 * i.e sorts 2 before 10.
 * 
 * Check for first numeral ?
 * 
 * 
 */


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

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
  JButton group_of_directories;
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
    browse_button = new JButton("Single Directory");
    browse_button.setMnemonic('b');
    browse_button.addActionListener(this);
    pane.add(browse_button);
            
    //add directory of directories (1 level) button
    group_of_directories = new JButton("Multiple Directories");
    group_of_directories.setMnemonic('b');
    group_of_directories.addActionListener(this);
    pane.add(group_of_directories);
    
    
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
  
  private void processDirectory(File processDirectory)
  {
	  
	File[] files = processDirectory.listFiles();	
	
	Arrays.sort(files);
  	
  	//Add all files of image type to pdfGenerator
  	for(int i=0;i < files.length; i++)
  	{
  		System.out.println(files[i].getName());
  		//TODO: Add different graphic types
  		if(files[i].getName().endsWith("jpeg")||
  				files[i].getName().endsWith("jpg") ||
  				files[i].getName().endsWith("JPEG")||
  				files[i].getName().endsWith("JPG"))
  		{
  			System.out.println("Adding " + files[i].getName());
  			//pdfGenerator.addImage(processDirectory[i]);
  		}
  	}
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
        	processDirectory(jfc.getSelectedFile());
        	parsing_directory = jfc.getSelectedFile();
        }
    }
    
    else if(event.getSource() == group_of_directories)
    {
        JFileChooser jfc = new JFileChooser(new File("."));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Demonstrate "Open" dialog:
              
        if (jfc.showOpenDialog(JMangle.this) == JFileChooser.APPROVE_OPTION) 
        {
        	
        	File[] listOfFiles = jfc.getSelectedFile().listFiles();
        	
        	Arrays.sort(listOfFiles);
        	
        	//Add all files of image type to pdfGenerator
        	for(int i=0;i < listOfFiles.length; i++)
        	{
        		System.out.println(listOfFiles[i].getName());
        		processDirectory(listOfFiles[i]);
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