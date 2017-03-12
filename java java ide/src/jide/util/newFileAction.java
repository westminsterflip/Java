package jide.util;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import jide.jidest;

@SuppressWarnings("serial")
public class newFileAction extends icaction{
	public newFileAction(String NewSize, boolean isButton){
		super("",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images/"+NewSize+".png")))),"Create a new file (CTRL+N)",(Integer)KeyEvent.VK_N,KeyStroke.getKeyStroke(KeyEvent.VK_N,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public newFileAction(String NewSize){
		super("New",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images/"+NewSize+".png")))),null,(Integer)KeyEvent.VK_N,KeyStroke.getKeyStroke(KeyEvent.VK_N,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public void actionPerformed(ActionEvent e){
		jidest.MainWindow.open(null);
	}
}
