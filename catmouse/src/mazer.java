import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class mazer extends JFrame implements ActionListener{
	File maze;
	JPanel maz = new JPanel();
	JPanel[][] blocs;
	JPanel cat = new JPanel();
	JLabel ca=new JLabel("C");
	
	public mazer(){
		super("Cats, and Mouses, and Mazes! Oh my!");
		maz.setPreferredSize(new Dimension(30,30));
		maz.setLayout(new FlowLayout());
		blocs = new JPanel[3][3];
		blocs[0][0]=cat;
		
		add(maz);
		setPreferredSize(new Dimension(50,50));
		pack();
		setVisible(true);
		System.out.println(maz.getHeight());
	}

	public void actionPerformed(ActionEvent g) {
	}
	
	public static void main(String[] yay){
		mazer y = new mazer();
	}
	
}
