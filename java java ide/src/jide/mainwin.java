package jide;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class mainwin extends JFrame{
	public mainwin() throws FileNotFoundException{
		new File(jidest.YATE_FOLDER_PATH+"\\class files").mkdir();
		new File(jidest.YATE_FOLDER_PATH+"\\autosaves").mkdir();
		Image m = Toolkit.getDefaultToolkit().getImage("src\\images\\icon.png");
		setIconImage(m);
		setVisible(true);
		Scanner winratioscan = new Scanner(jidest.settingsFile);
		boolean found = false;
		while(winratioscan.hasNextLine()&&!found){
			String line = winratioscan.nextLine();
			if(line.startsWith("window.ratio")){
				jidest.x_rat=Double.parseDouble(line.substring(12, line.indexOf(',')));
				jidest.y_rat=Double.parseDouble(line.substring(line.indexOf(',')));
				found=true;
			}
		}
	}
}
