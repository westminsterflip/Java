package jide.parts;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jide.util.newFileAction;

@SuppressWarnings("serial")
public class menubar extends JMenuBar{
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu java = new JMenu("Java");
	public menubar(){
		super();
		file.add(new JMenuItem(new newFileAction("New16")));
		add(file);
		add(edit);
		add(java);
	}
}
