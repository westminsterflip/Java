package jide.util;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import jide.jidest;

@SuppressWarnings("serial")
public class saveFileAction extends icaction{
	public saveFileAction(String SaveSize,boolean s){
		super("",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+SaveSize+".png")))),"Save file in current tab (CTRL+S)",(Integer)KeyEvent.VK_S,KeyStroke.getKeyStroke(KeyEvent.VK_S,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public saveFileAction(String SaveSize){
		super("Save",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+SaveSize+".png")))),null,(Integer)KeyEvent.VK_S,KeyStroke.getKeyStroke(KeyEvent.VK_S,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public void actionPerformed(ActionEvent e){
		jidest.MainWindow.save(jidest.MainWindow.files.getTitleAt(jidest.MainWindow.files.getSelectedIndex()));
	}
}
