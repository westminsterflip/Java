package jide.util;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import jide.jidest;
import jide.mainwin;

@SuppressWarnings("serial")
public class newFileAction extends icaction{
	public newFileAction(String NewSize){
		super("New",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+NewSize+".png")))),"Create a new file",(Integer)KeyEvent.VK_N,KeyStroke.getKeyStroke(KeyEvent.VK_N,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public void actionPerformed(ActionEvent e){
		try {
			mainwin.open(null);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
