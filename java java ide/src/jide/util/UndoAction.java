package jide.util;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import jide.jidest;
import jide.parts.editorScroll;

@SuppressWarnings("serial")
public class UndoAction extends icaction{
	public UndoAction(boolean isButton) {
		super("",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images/Undo22.png")))),"Undo last change",null,KeyStroke.getKeyStroke(KeyEvent.VK_Z,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	public UndoAction() {
		super("Undo                ",(Icon)(new ImageIcon(Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images/Undo16.png")))),null,KeyEvent.VK_U,KeyStroke.getKeyStroke(KeyEvent.VK_Z,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
	
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e){
		((editorScroll)jidest.MainWindow.files.getSelectedComponent()).editorinst.undoredo.undo();
	}
}
