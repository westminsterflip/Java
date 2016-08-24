import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class help extends JFrame{
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	
	public help(){
		super("Help");
		JLabel squares = new JLabel("!!SQUARES!!");
		squares.setFont (squares.getFont ().deriveFont (200.0f));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		add(squares);
		pack();
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
	}
	
}
