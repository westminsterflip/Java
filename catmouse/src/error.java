import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class error extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5128708273727492302L;
	JLabel err = new JLabel();
	JButton ok = new JButton("OK");
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();

	public error(String g){
		super("Error: Too Many " + g);
		setLayout(new BorderLayout());
		err.setText("Although the program will run with multiple " + g + ", it is likely that only one will be on the given path.");
		add(err,BorderLayout.CENTER);
		ok.addActionListener(this);
		add(ok,BorderLayout.SOUTH);
		setVisible(true);
		pack();
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		mazer.cango = true;
		dispose();
	}

}
