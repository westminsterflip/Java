package jide.util;

import java.awt.event.ActionEvent;

import jide.jidest;

@SuppressWarnings("serial")
public class SaveAllFileAction extends icaction{
	public SaveAllFileAction() {
		super("Save All",null,null,null,null);
	}
	
	public void actionPerformed(ActionEvent e){
		int s = jidest.MainWindow.files.getSelectedIndex();
		for(int i=0;i<jidest.MainWindow.files.getTabCount();i++){
			jidest.MainWindow.save(jidest.MainWindow.files.getTitleAt(i));
		}
		jidest.MainWindow.files.setSelectedIndex(s);
	}

}
