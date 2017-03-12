package jide.util;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import jide.jidest;

@SuppressWarnings("serial")
public class openFileAction extends icaction{
	public openFileAction(String NewSize,boolean isButton){
		super("",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+NewSize+".png")))),"Create a new file (CTRL+O)",(Integer)KeyEvent.VK_O,KeyStroke.getKeyStroke(KeyEvent.VK_O,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public openFileAction(String NewSize){
		super("Open",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+NewSize+".png")))),null,(Integer)KeyEvent.VK_O,KeyStroke.getKeyStroke(KeyEvent.VK_O,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public void actionPerformed(ActionEvent e){
		JFileChooser picky = new JFileChooser(jidest.lastDir);
		int returnValue = picky.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		  File selectedFile = picky.getSelectedFile();
		  jidest.MainWindow.open(selectedFile.getAbsolutePath());
		}
	}
}
