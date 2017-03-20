package jide.parts;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import jide.util.SaveAllFileAction;
import jide.util.UndoAction;
import jide.util.newFileAction;
import jide.util.openFileAction;
import jide.util.saveAsFileAction;
import jide.util.saveFileAction;

@SuppressWarnings("serial")
public class menubar extends JMenuBar{
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu java = new JMenu("Java");
	public JMenuItem save;
	public JMenuItem saveas;
	public JMenuItem saveall;
	public JMenuItem undo;
	public menubar(){
		super();
		file.add(new JMenuItem(new newFileAction("New16")));
		file.add(new JMenuItem(new openFileAction("Open16")));
		file.addSeparator();
		save = new JMenuItem(new saveFileAction("Save16"));
		saveas = new JMenuItem(new saveAsFileAction());
		saveall = new JMenuItem(new SaveAllFileAction());
		undo = new JMenuItem(new UndoAction());
		file.add(save);
		file.add(saveas);
		file.add(saveall);
		edit.add(undo);
		edit.addSeparator();
		add(file);
		add(edit);
		add(java);
		disableSaves();
	}
	
	public void enableSaves(){
		saveas.setEnabled(true);
		save.setEnabled(true);
		saveall.setEnabled(true);
	}
	
	public void disableSaves(){
		saveas.setEnabled(false);
		save.setEnabled(false);
		saveall.setEnabled(false);
	}
}
