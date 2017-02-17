package jide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream; 
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import jide.parts.aSWarning;
import jide.parts.editorScroll;
import jide.parts.menubar;


@SuppressWarnings("serial")
public class mainwin extends JFrame implements ComponentListener, ActionListener{
	static ArrayList<String> autosavePile = new ArrayList<String>();
	static ArrayList<File> autosaveFilePile = new ArrayList<File>();
	static ArrayList<String> savePaths = new ArrayList<String>();
	static JTabbedPane files = new JTabbedPane();
	static int menubarHeight;
	public static yatespash splash;
	public mainwin() throws FileNotFoundException{
		splash = new yatespash();
		//splash.setAlwaysOnTop(true);
		String path = System.getProperty("user.home");
		switch(jidest.osc()){
		case 'w': path+="\\AppData\\Roaming\\YATE\\";
			if(System.getProperty("os.arch").equals("amd64"))
				jidest.is64=true;break;
		case 'm': path+="/Library/Application Support/YATE/";
			if(System.getProperty("os.arch").equals("ppc"))
				jidest.is64=true;break;
		case 'l': path+="/.YATE/";
			if(System.getProperty("os.arch").equals("amd64"))
				jidest.is64=true;break;
		default: path="";
		}
		jidest.jdkPath+=jidest.osc();
		if(jidest.is64)
			jidest.jdkPath+="64";
		else 
			jidest.jdkPath+="32";
		jidest.YATE_FOLDER_PATH=path;
		jidest.settingsFile = new File(path+"Settings");
		if(!jidest.settingsFile.exists())
			try {
				jidest.settingsFile.createNewFile();
				PrintWriter ne = new PrintWriter(jidest.settingsFile);
				ne.println("window.size:0,0");
				ne.println("window.location:-8,0");
				ne.println("autosave.frequency:15");
				ne.flush();
				ne.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		new File(path).mkdir();
		if(!new File(jidest.YATE_FOLDER_PATH+"/jdk").exists()){
			InputStream inp = mainwin.class.getResourceAsStream("/"+jidest.jdkPath+".zip");
			try {
				Files.copy(inp, new File(jidest.YATE_FOLDER_PATH+"/"+jidest.jdkPath+".zip").toPath(),REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] buffer = new byte[1024];
			try{
			ZipInputStream zis = new ZipInputStream(new FileInputStream(new File(jidest.YATE_FOLDER_PATH+"/"+jidest.jdkPath+".zip")));
		    	ZipEntry ze = zis.getNextEntry();
		    	while(ze!=null){
		    		String fileName = ze.getName();
		    		File newFile = new File(jidest.YATE_FOLDER_PATH + File.separator + fileName);
		    		new File(newFile.getParent()).mkdirs();
		    		if(ze.isDirectory()){
		    			newFile.mkdir();
		    		}else{
		    			FileOutputStream fos = new FileOutputStream(newFile);
		    			int len;
		    			while ((len = zis.read(buffer)) > 0) {
		    				fos.write(buffer, 0, len);
		    			}
		    			fos.close();
		    		}
		    		ze = zis.getNextEntry();
		    	}
		        zis.closeEntry();
		    	zis.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			new File(jidest.YATE_FOLDER_PATH+"/"+jidest.jdkPath).renameTo(new File(jidest.YATE_FOLDER_PATH+"/jdk"));
			new File(jidest.YATE_FOLDER_PATH+"/"+jidest.jdkPath+".zip").delete();
		}
		new File(jidest.YATE_FOLDER_PATH+File.separator+"class files").mkdir();
		File asaves = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves");
		asaves.mkdir();
		Image m = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images"+File.separator+"icon.png"));
		setIconImage(m);
		Scanner winsizescan = new Scanner(jidest.settingsFile);
		boolean found = false,found1=false,found2=false;
		while(winsizescan.hasNextLine()&&(!found||!found1||!found2)){
			String line = winsizescan.nextLine();
			if(line.startsWith("window.size")){
				jidest.x_size=Double.parseDouble(line.substring(line.indexOf(':')+1, line.indexOf(',')));
				jidest.y_size=Double.parseDouble(line.substring(line.indexOf(',')+1));
				found=true;
			}else if(line.startsWith("window.location")){
				jidest.x_loc=Double.parseDouble(line.substring(line.indexOf(':')+1, line.indexOf(',')));
				jidest.y_loc=Double.parseDouble(line.substring(line.indexOf(',')+1));
				found1=true;
			}else if(line.startsWith("autosave.frequency")){
				jidest.asFrequency=Integer.parseInt(line.substring(line.indexOf(':')+1));
				
			}
		}
		winsizescan.close();
		if(jidest.x_size==0||jidest.y_size==0){
			//setVisible(true);
			setSize((int)jidest.scrSizeUs.getWidth()+16,(int)jidest.scrSizeUs.getHeight()+8);
			jidest.x_size=getWidth();
			jidest.y_size=getHeight();
			//setVisible(false);
		}else{
			setSize((int)jidest.x_size,(int)jidest.y_size);
		}
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
		menubar menu = new menubar();
		menubarHeight=menu.getHeight();
		add(menu,BorderLayout.NORTH);
		add(files,BorderLayout.CENTER);
		validate();
		this.addComponentListener(this);
		setWinSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		if(nme.size()!=0){
			aSWarning nm = new aSWarning(nme);
			setVisible(true);
			splash.dispose();
			nm.setVisible(true);
		}else{
			setVisible(true);
			splash.dispose();
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
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		if(arg0.getSource().equals(this)){
			jidest.x_loc=this.getLocation().getX();
			jidest.y_loc=this.getLocation().getY();
			try {
				setWinLoc();
			} catch (FileNotFoundException e) {
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
		
	}
	
	public static void open(String fileName) throws FileNotFoundException{
		if(fileName==null){
			int no = 1;
			fileName="new"+no;
			while(new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+".as").exists()){
				fileName = "new"+(++no);
			}
		}
		File fileopen = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+".as");
		if(!fileopen.exists())
			if(!new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+".s").exists())
				try {
					fileopen.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				fileopen = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+".s");
		autosaveFilePile.add(fileopen);
		Scanner autosaveRead = new Scanner(fileopen);
		String autosaveString = "";
		if(autosaveRead.hasNextLine())
			savePaths.add(autosaveRead.nextLine());
		else
			savePaths.add("");
		if(autosaveRead.hasNextLine())
			autosaveString+=autosaveRead.nextLine();
		while(autosaveRead.hasNextLine()){
			autosaveString+=(System.lineSeparator()+autosaveRead.nextLine());
		}
		autosaveRead.close();
		autosavePile.add(autosaveString);
		editorScroll newedit = new editorScroll(autosaveString,fileName,new Dimension((int)jidest.x_size,(int)jidest.y_size-menubarHeight));
		files.addTab(fileName, newedit);
		files.setSelectedComponent(files.getComponentAt(files.getTabCount()-1));
	}
	
	public void autoSave(String name,String savepath,String content){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}