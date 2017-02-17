package jide.parts;

import javax.swing.JButton;
import javax.swing.JToolBar;
import jide.util.newFileAction;

@SuppressWarnings("serial")
public class toolbar extends JToolBar{
	public toolbar(){
		super();
		add(new JButton(new newFileAction("New22")));
	}

}
