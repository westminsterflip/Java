import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class fork extends JFrame{
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	
	public fork(){
		super("FOrkS and FOKRKDS AND FORKS  ADSND F0rk5");
		JLabel squares = new JLabel("You wanna not?");
		squares.setFont (squares.getFont ().deriveFont (150.0f));
		setLayout(new BorderLayout());
		add(squares, BorderLayout.CENTER);
		setSize(scr);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] saltysaltysalty){
		for(int y = 0;y<10;y++){
			fork f = new fork();
		}
	}

}
