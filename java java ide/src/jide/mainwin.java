package jide;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

import jide.parts.aSWarning;


@SuppressWarnings("serial")
public class mainwin extends JFrame implements ComponentListener{
	@SuppressWarnings("unused")
	public mainwin() throws FileNotFoundException{
		new File(jidest.YATE_FOLDER_PATH+"\\class files").mkdir();
		File asaves = new File(jidest.YATE_FOLDER_PATH+"\\autosaves");
		asaves.mkdir();
		Image m = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images\\icon.png"));
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
		Scanner winlocscan = new Scanner(jidest.settingsFile);
		found = false;
		while(winlocscan.hasNextLine()&&!found){
			String line = winlocscan.nextLine();
			if(line.startsWith("window.location")){
				jidest.x_loc=Double.parseDouble(line.substring(line.indexOf(':')+1, line.indexOf(',')));
				jidest.y_loc=Double.parseDouble(line.substring(line.indexOf(',')+1));
				found=true;
			}
		}
		winlocscan.close();
		if(jidest.x_size==0||jidest.y_size==0){
			setVisible(true);
			setSize((int)jidest.scrSizeUs.getWidth()+16,(int)jidest.scrSizeUs.getHeight()+8);
			jidest.x_size=getWidth();
			jidest.y_size=getHeight();
			System.out.println(getWidth()+" " + getHeight());
			setVisible(false);
		}else{
			setSize((int)jidest.x_size,(int)jidest.y_size);
		}
		setVisible(true);
		if(jidest.y_loc<0){
			jidest.y_loc=0;
			setSize((int)jidest.x_size,(int)jidest.y_size-8);
		}
		ArrayList<String> nme = new ArrayList<String>();
		for(String as:asaves.list()){
			if(as.endsWith(".as")){
				nme.add(as.substring(0, as.indexOf(".as")));
			}
		}
		setLocation((int)jidest.x_loc,(int)jidest.y_loc);
		this.addComponentListener(this);
		setWinSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		if(nme.size()!=0){
			yatespash.getWindows()[0].setVisible(false);
			aSWarning nm = new aSWarning(nme);
		}
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
	
	void setWinLoc() throws FileNotFoundException{
		Scanner job = new Scanner(jidest.settingsFile);
		boolean there = false;
		String buffer = "";
		while(job.hasNextLine()){
			String line = job.nextLine();
			if(line.startsWith("window.location")){
				buffer+=("window.location:"+jidest.x_loc+","+jidest.y_loc+System.lineSeparator());
				there = true;
			}
			else {
				buffer+=(line+System.lineSeparator());
			}
		}
		job.close();
		PrintWriter jobo = new PrintWriter(jidest.settingsFile);
		if(!there)
			buffer+=("window.location:"+jidest.x_loc+","+jidest.y_loc);
		jobo.print(buffer);
		jobo.flush();
		jobo.close();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		if(arg0.getSource().equals(this)){
			jidest.x_loc=this.getLocation().getX();
			jidest.y_loc=this.getLocation().getY();
			try {
				setWinLoc();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void componentResized(ComponentEvent arg0) {
		if(arg0.getSource().equals(this))
			try {
				jidest.x_size=getWidth();
				jidest.y_size=getHeight();
				setWinSize();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
