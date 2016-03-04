import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class button {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public button(){
      prepareGUI();
   }

   public static void main(String[] args){
      button  swingListenerDemo = new button();  
      swingListenerDemo.showActionListenerDemo();
   }

   private void prepareGUI(){
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	   mainFrame = new JFrame("Java SWING Examples");
      mainFrame.setSize(400,400);
	   mainFrame.setLocation((int)(dimension.getWidth()-400)/2,(int)(dimension.getHeight()-400)/2);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void showActionListenerDemo(){
      headerLabel.setText("Listener in action: ActionListener");      

      JPanel panel = new JPanel();      
      panel.setBackground(Color.magenta);            
		
      JButton okButton = new JButton("OK");

      okButton.addActionListener(new CustomActionListener());        
      panel.add(okButton);
      controlPanel.add(panel);
      mainFrame.setVisible(true); 
   }
   
   class CustomActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
    	      button  swingListenerDemo = new button();  
    	      swingListenerDemo.showActionListenerDemo();
      }
   }	
}