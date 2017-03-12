package jide.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import jide.jidest;

@SuppressWarnings("serial")
public class saveAsFileAction extends icaction{
	public saveAsFileAction(boolean isButton) {
		super("", (Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images/saveas.png")).getScaledInstance(22,22,Image.SCALE_SMOOTH))),"Save As (CTRL+SHIFT+S)",null,KeyStroke.getKeyStroke(KeyEvent.VK_S,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()+KeyEvent.SHIFT_MASK));
	}

	public saveAsFileAction() {
		super("Save As",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images/saveas.png")).getScaledInstance(16,16,Image.SCALE_SMOOTH))),null,null,KeyStroke.getKeyStroke(KeyEvent.VK_S,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()+KeyEvent.SHIFT_MASK));
	}
	
	public void actionPerformed(ActionEvent s){
		jidest.MainWindow.saveAs(jidest.MainWindow.files.getTitleAt(jidest.MainWindow.files.getSelectedIndex()));
	}
}