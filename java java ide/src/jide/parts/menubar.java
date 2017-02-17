package jide.parts;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class menubar extends JMenuBar{
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu java = new JMenu("Java");
	public menubar(){
		super();
		add(file);
		add(edit);
		add(java);
	}
}
