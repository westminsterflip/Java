package jide.parts;

import javax.swing.JButton;
import javax.swing.JToolBar;
import jide.util.newFileAction;
import jide.util.openFileAction;
import jide.util.saveAsFileAction;
import jide.util.saveFileAction;

@SuppressWarnings("serial")
public class toolbar extends JToolBar{
	public static JButton save = new JButton(new saveFileAction("Save22",true));
	public static JButton saveas = new JButton(new saveAsFileAction(true));
	public toolbar(){
		super();
		add(new JButton(new newFileAction("New22",true)));
		add(new JButton(new openFileAction("Open22",true)));
		add(save);
		add(saveas);
		disableSaves();
		setFloatable(false);
	}
	
	public static void enableSaves(){
		saveas.setEnabled(true);
		save.setEnabled(true);
	}
	
	public static void disableSaves(){
		saveas.setEnabled(false);
		save.setEnabled(false);
	}
}
