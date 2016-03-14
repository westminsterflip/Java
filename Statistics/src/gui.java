import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class gui extends JFrame implements ActionListener{
	
	public JPanel file = new JPanel();
	public JPanel filw = new JPanel();
	public JLabel fil = new JLabel("Filename:");
	public JLabel f = new JLabel("Invalid Filename");
	public JTextField fi = new JTextField(30);
	public JTable out = new JTable(31,4);
	public JTable data = new JTable(2,4);
	
	public gui(){
		super("Student Statistics");
		filw.setLayout(new BorderLayout());
		filw.add(fil,BorderLayout.WEST);
		filw.add(fi,BorderLayout.EAST);
		file.setLayout(new BorderLayout());
		file.add(filw, BorderLayout.CENTER);
		file.add(f, BorderLayout.SOUTH);
		f.setVisible(false);
		setLayout(new BorderLayout());
		add(file,BorderLayout.NORTH);
		add(out,BorderLayout.CENTER);
		add(data,BorderLayout.SOUTH);
		setVisible(true);
		out.setPreferredScrollableViewportSize(new Dimension(500,200));
		data.setPreferredScrollableViewportSize(new Dimension(500,200));
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		gui g = new gui();
	}
	
}
