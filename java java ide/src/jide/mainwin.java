package jide;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;



@SuppressWarnings("serial")
public class mainwin extends JFrame{
	public mainwin(){
		new File(jidest.YATE_FOLDER_PATH+"\\class files").mkdir();
		new File(jidest.YATE_FOLDER_PATH+"\\autosaves").mkdir();
		System.out.println(new File("src\\images\\icon.ico").exists());
		Image m = Toolkit.getDefaultToolkit().getImage("src\\images\\icon.png");
		setIconImage(m);
		setVisible(true);
	}
}
