import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class JMangle extends JFrame
{
  JPanel pane = new JPanel();
  JMangle() // the frame constructor method
  {
    super("Mangle Java");
    setBounds(100,100,300,100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container con = this.getContentPane(); // inherit main frame
    con.add(pane); // add the panel to frame
    //add browse button
    //add run button
    
    
    // customize panel here
    // pane.add(someWidget);
    setVisible(true); // display this frame
  }
  public static void main(String args[])
  {
	  new JMangle();
  }
}