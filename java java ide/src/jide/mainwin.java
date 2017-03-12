package jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import jide.parts.aSWarning;
import jide.parts.editorScroll;
import jide.parts.menubar;
import jide.parts.saveAsDialog;
import jide.parts.toolbar;
import jide.util.Download;


@SuppressWarnings("serial")
public class mainwin extends JFrame implements ComponentListener{
	ArrayList<String> autosavePile = new ArrayList<String>();
	ArrayList<String> savePaths = new ArrayList<String>();
	public JTabbedPane files = new JTabbedPane();
	int menubarHeight;
	boolean good2go = false;
	public JPanel menubuf = new JPanel();
	public JPanel lnpanel = new JPanel();
	public JLabel lineNums = new JLabel();
	public yatespash splash;
	public menubar menubar0 = new menubar();
	public ArrayList<String> nme = new ArrayList<String>();
	public mainwin(){
		super("YATE: Yet Another Text Editor");
		splash = new yatespash();
		menubuf.setLayout(new BorderLayout());
		menubuf.add(new toolbar(),BorderLayout.SOUTH);
		menubuf.add(menubar0,BorderLayout.NORTH);
		//splash.setAlwaysOnTop(true);
		String path = System.getProperty("user.home");
		switch(osc()){
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
		jidest.jdkPath+=osc();
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
				ne.println("font.name:Monotype.plain");
				ne.println("font.size:12");
				ne.println("check.font.download.on.start:true");
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
		Scanner winsizescan=null;
		try {
			winsizescan = new Scanner(jidest.settingsFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		boolean found = false,found1=false,found2=false,found3=false,found4=false,found5=false,found6=false;
		while(winsizescan.hasNextLine()&&(!found||!found1||!found2||!found3||!found4||!found5||!found6)){
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
				found2=true;
			}else if(line.startsWith("font.name")){
				jidest.fontName=line.substring(line.indexOf(':')+1);
				found3=true;
			}else if(line.startsWith("font.size")){
				jidest.fontSize = Integer.parseInt(line.substring(line.indexOf(':')+1));
				found4=true;
			}else if(line.startsWith("font.file.number")){
				jidest.fontFileNumber = Integer.parseInt(line.substring(line.indexOf(':')));
				found5=true;
			}else if(line.startsWith("check.font.download.on.start")){
				jidest.checkOnOpen = Boolean.parseBoolean(line.substring(line.indexOf(':')+1));
				found6=true;
			}
		}
		winsizescan.close();
		if(jidest.x_size==0||jidest.y_size==0){
			setSize((int)jidest.scrSizeUs.getWidth()+16,(int)jidest.scrSizeUs.getHeight()+8);
			jidest.x_size=getWidth();
			jidest.y_size=getHeight();
		}else{
			setSize((int)jidest.x_size,(int)jidest.y_size);
		}
		if(jidest.y_loc<0){
			jidest.y_loc=0;
			setSize((int)jidest.x_size,(int)jidest.y_size-8);
		}
		if(jidest.fontName==null){
			jidest.fontName = "Monospaced.plain";
		}
		if(jidest.fontSize==0){
			jidest.fontSize = 12;
		}
		for(String as:asaves.list()){
			if(as.endsWith("_as")){
				nme.add(as.substring(0, as.indexOf("_as")));
			}
		}
		JDialog out = new JDialog();
		JPanel buttons1 = new JPanel();
		out.add(new JLabel("<html><br>Do you want to download fonts? (~350MB)<br></html>",JLabel.CENTER),BorderLayout.NORTH);
		out.add(buttons1,BorderLayout.SOUTH);
		JButton yea = new JButton("Yes");
		yea.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				out.setVisible(false);
				splash.setVisible(true);
				splash.testpro.setVisible(true);
				splash.test.setVisible(true);
				splash.setAlwaysOnTop(true);
				splash.testpro.setAlwaysOnTop(true);
				splash.outputback.setAlwaysOnTop(true);
				splash.test.setAlwaysOnTop(true);
				Download doodle = new Download();
				Thread thread = new Thread(doodle);
				thread.start();
			}
		});
		JButton naw = new JButton("No");
		naw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				out.setVisible(false);
				splash.setVisible(false);
				jidest.MainWindow.splash.testpro.dispose();
				jidest.MainWindow.splash.test.dispose();
				jidest.MainWindow.splash.outputback.dispose();
				if(nme.size()!=0){
					aSWarning nm = new aSWarning(nme,jidest.MainWindow);
					setVisible(true);
					splash.dispose();
					nm.setVisible(true);
				}else{
					setVisible(true);
					splash.dispose();
				}
			}
		});
		out.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				splash.setVisible(false);
				jidest.MainWindow.splash.testpro.dispose();
				jidest.MainWindow.splash.test.dispose();
				jidest.MainWindow.splash.outputback.dispose();
				if(nme.size()!=0){
					aSWarning nm = new aSWarning(nme,(mainwin) files.getParent());
					setVisible(true);
					splash.dispose();
					nm.setVisible(true);
				}else{
					setVisible(true);
					splash.dispose();
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
			
		});
		buttons1.add(yea);
		buttons1.add(naw);
		yea.setPreferredSize(new Dimension(81,23));
		naw.setPreferredSize(new Dimension(81,23));
		setLocation((int)jidest.x_loc,(int)jidest.y_loc);
		menubarHeight=menubar0.getHeight();
		add(menubuf,BorderLayout.NORTH);
		add(files,BorderLayout.CENTER);
		lnpanel.setBackground(Color.red);
		validate();
		this.addComponentListener(this);
		try {
			setWinSize();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		if(jidest.checkOnOpen){
			out.setVisible(true);
			out.pack();
			out.setLocation((int)Math.round((jidest.scrSize.getWidth()-out.getWidth())/2.0),(int)Math.round((jidest.scrSize.getHeight()-out.getHeight())/2.0));
			out.setResizable(false);
			out.setAlwaysOnTop(true);
		}else{
			if(nme.size()!=0){
				aSWarning nm = new aSWarning(nme,this);
				setVisible(true);
				splash.dispose();
				splash.testpro.dispose();
				splash.test.dispose();
				splash.outputback.dispose();
				nm.setVisible(true);
			}else{
				setVisible(true);
				splash.dispose();
				splash.testpro.dispose();
				splash.test.dispose();
				splash.outputback.dispose();
			}
		}
	}
	
	public char osc(){
		char ou = 'o';
		String tob = System.getProperty("os.name").toLowerCase();
		if(tob.indexOf("win")!=-1)
			ou = 'w';
		else if(tob.indexOf("mac")!=-1)
			ou = 'm';
		else if(tob.indexOf("nix")!=-1||tob.indexOf("nux")!=-1||tob.indexOf("aix")!=-1)
			ou = 'l';
		jidest.OS=ou;
		return ou;
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
	
	public void open(String fileName){
		menubar0.enableSaves();
		toolbar.enableSaves();
		String autosaveString = "";
		String tfileName="";
		if(fileName==null){
			int no = 1;
			fileName="new"+no;
			while(new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+"_as").exists()){
				fileName = "new"+(++no);
			}
			try {
				new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+"_as").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			tfileName=fileName;
		}else{
			try{
				tfileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
				File fileopen;
				if(fileName.contains("_as")){
					tfileName = tfileName.substring(0,tfileName.lastIndexOf("_as"));
					Scanner autosaveRead = new Scanner(new File(fileName));
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
				}else{
					Scanner autosaveRead = new Scanner(new File(fileName));
					savePaths.add(fileName);
					while(autosaveRead.hasNextLine()){
						autosaveString+=(autosaveRead.nextLine()+System.lineSeparator());
					}
					autosaveString=autosaveString.substring(0,autosaveString.lastIndexOf(System.lineSeparator()));
					autosaveRead.close();
				}
				fileopen = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+tfileName+"_s");
				if(!fileopen.exists()){
					if(!new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+tfileName+"_as").exists())
						try {
							fileopen.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					else
						fileopen = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+tfileName+"_as");
					autosavePile.add(autosaveString);
					PrintWriter ok = new PrintWriter(fileopen);
					ok.println(fileName);
					ok.print(autosaveString);
					ok.flush();
					ok.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		editorScroll newedit = new editorScroll(autosaveString,tfileName,new Dimension((int)jidest.x_size,(int)jidest.y_size-menubuf.getHeight()));
		files.addTab(tfileName, newedit);
		files.setSelectedComponent(files.getComponentAt(files.getTabCount()-1));
	}
	
	@SuppressWarnings("static-access")
	public void save(String flname){
		String text = ((editorScroll)files.getSelectedComponent()).editorinst.getText();
		File as = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+flname+"_as");
		String path="";
		Scanner pathFinder;
		try {
			pathFinder = new Scanner(as);
			if(pathFinder.hasNextLine())
				path = pathFinder.nextLine();
			pathFinder.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if(path.equals("")){
			saveAs(flname);
		}else{
			try{
				pathFinder = new Scanner(text);
				PrintWriter saveOut = new PrintWriter(new File(path));
				while(pathFinder.hasNextLine()){
					saveOut.println(pathFinder.nextLine());
				}
				pathFinder.close();
				saveOut.flush();
				saveOut.close();
				as.renameTo(new File(as.getAbsolutePath().substring(0,as.getAbsolutePath().lastIndexOf("_as"))+"_s"));
			}catch(FileNotFoundException e){}
		}
	}
	
	@SuppressWarnings("static-access")
	public void saveAs(String flname){
		String text = ((editorScroll)files.getSelectedComponent()).editorinst.getText();
		File as = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+flname+"_as");
		String path="";
		Scanner pathFinder;
		try {
			pathFinder = new Scanner(as);
			if(pathFinder.hasNextLine())
				path = pathFinder.nextLine();
			pathFinder.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		saveAsDialog sad = new saveAsDialog(path);
		path=sad.pth;
		if(!sad.canceled)
			try{
				pathFinder = new Scanner(text);
				PrintWriter saveOut = new PrintWriter(new File(path));
				PrintWriter rerw = new PrintWriter(as);
				rerw.println(new File(path).getAbsolutePath());
				while(pathFinder.hasNextLine()){
					String ssss=pathFinder.nextLine();
					saveOut.println(ssss);
					rerw.println(ssss);
				}
				rerw.flush();
				rerw.close();
				pathFinder.close();
				saveOut.flush();
				saveOut.close();
				files.getSelectedComponent().setName(path.substring(path.lastIndexOf(File.separator)+1));
				files.setTitleAt(files.getSelectedIndex(),files.getSelectedComponent().getName());
				as.renameTo(new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+path.substring(path.lastIndexOf(File.separator)+1)+"_s"));
			}catch(FileNotFoundException e){}
	}
}