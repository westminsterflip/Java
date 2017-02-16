package jide;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class mainwin extends JFrame{
	public mainwin() throws FileNotFoundException{
		new File(jidest.YATE_FOLDER_PATH+"\\class files").mkdir();
		new File(jidest.YATE_FOLDER_PATH+"\\autosaves").mkdir();
		Image m = Toolkit.getDefaultToolkit().getImage("src\\images\\icon.png");
		setIconImage(m);
		Scanner winsizescan = new Scanner(jidest.settingsFile);
		boolean found = false;
		while(winsizescan.hasNextLine()&&!found){
			String line = winsizescan.nextLine();
			if(line.startsWith("window.size")){
				jidest.x_size=Double.parseDouble(line.substring(line.indexOf(':')+1, line.indexOf(',')));
				jidest.y_size=Double.parseDouble(line.substring(line.indexOf(',')+1));
				found=true;
			}
		}
		winsizescan.close();
		if(jidest.x_size==0||jidest.y_size==0){
			setVisible(true);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			jidest.x_size=getWidth();
			jidest.y_size=getHeight();
			System.out.println(getWidth()+" " + getHeight());
			setVisible(false);
		}else
			setSize((int)jidest.x_size,(int)jidest.y_size);
		setVisible(true);
		setWinSize();
	}
	
	void setWinSize() throws FileNotFoundException{
		Scanner job = new Scanner(jidest.settingsFile);
		boolean there = false;
		String buffer = "";
		while(job.hasNextLine()){
			String line = job.nextLine();
			if(line.startsWith("window.size")){
				buffer+=("window.size:"+jidest.x_size+","+jidest.y_size+System.lineSeparator());
				there = true;
			}
			else {
				buffer+=(line+System.lineSeparator());
			}
		}
		job.close();
		PrintWriter jobo = new PrintWriter(jidest.settingsFile);
		if(!there)
			buffer+=("window.size:"+jidest.x_size+","+jidest.y_size);
		jobo.print(buffer);
		jobo.flush();
		jobo.close();
	}
}
