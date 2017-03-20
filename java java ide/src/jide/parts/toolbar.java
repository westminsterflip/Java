package jide.parts;

import javax.swing.JButton;
import javax.swing.JToolBar;
import jide.util.UndoAction;
import jide.util.newFileAction;
import jide.util.openFileAction;
import jide.util.saveAsFileAction;
import jide.util.saveFileAction;

@SuppressWarnings("serial")
public class toolbar extends JToolBar{
	public JButton save = new JButton(new saveFileAction("Save22",true));
	public JButton saveas = new JButton(new saveAsFileAction(true));
	public JButton undo = new JButton(new UndoAction(true));
	public toolbar(){
		super();
		add(new JButton(new newFileAction("New22",true)));
		add(new JButton(new openFileAction("Open22",true)));
		add(save);
		add(saveas);
		add(new Separator());
		add(undo);
		disableSaves();
		setFloatable(false);
		undo.setEnabled(false);
	}
	
	public void enableSaves(){
		saveas.setEnabled(true);
		save.setEnabled(true);
	}
	
	public void disableSaves(){
		saveas.setEnabled(false);
		save.setEnabled(false);
	}
}
