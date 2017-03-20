package jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URISyntaxException;
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
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import jide.parts.DirectoryPanel;
import jide.parts.aSWarning;
import jide.parts.editorScroll;
import jide.parts.menubar;
import jide.parts.saveAsDialog;
import jide.parts.toolbar;
import jide.util.Download;
import jide.util.Opener;

//TODO settings window
//TODO workspace
@SuppressWarnings("serial")
public class mainwin extends JFrame implements ComponentListener,WindowListener{
	public JTabbedPane files = new JTabbedPane();
	int menubarHeight;
	int prevx;
	int prevy;
	boolean good2go = false;
	public JPanel menubuf = new JPanel();
	public JPanel lnpanel = new JPanel();
	public JLabel lineNums = new JLabel();
	public yatespash splash;
	public menubar menubar0 = new menubar();
	public toolbar tb = new toolbar();
	public JSplitPane center;
	//public BottomBar bottombar;
	public DirectoryPanel dbs;
	public ArrayList<String> nme = new ArrayList<String>();
	boolean wasMaximized = false;
	public ArrayList<File> fileList = new ArrayList<File>();
	public mainwin(){
		super("YATE: Yet Another Text Editor");
		splash = new yatespash();
		
		splash.build();
		dbs = new DirectoryPanel(); 
		menubuf.setLayout(new BorderLayout());
		menubuf.add(tb,BorderLayout.SOUTH);
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
				ne.println("window.maximized:true");
				ne.println("window.size:600,480");
				ne.println("window.location:-8,0");
				ne.println("autosave.frequency:15");
				ne.println("font.name:Inconsolata");
				ne.println("font.size:12");
				ne.println("check.font.download.on.start:true");
				ne.println("fonts.were.downloaded");
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
		Image m = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/icon.png"));
		setIconImage(m);
		Scanner winsizescan=null;
		try {
			winsizescan = new Scanner(jidest.settingsFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		boolean found = false,found1=false,found2=false,found3=false,found4=false,found5=false,found6=false,found7=false,found8=false;
		while(winsizescan.hasNextLine()&&(!found||!found1||!found2||!found3||!found4||!found5||!found6||!found7||!found8)){
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
			}else if(line.startsWith("fonts.were.downloaded")){
				jidest.wasDownloaded = Boolean.parseBoolean(line.substring(line.indexOf(':')+1));
				found7=true;
			}else if(line.startsWith("window.maximized")){
				jidest.isMaximized = Boolean.parseBoolean(line.substring(line.indexOf(':')+1));
				found8=true;
			}
		}
		winsizescan.close();
		if(jidest.isMaximized||jidest.x_size==0||jidest.y_size==0){
			jidest.x_size=600;
			jidest.y_size=480;
			jidest.isMaximized=true;
			try {
				setWinSize();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			wasMaximized=true;
		}else{
			setSize((int)jidest.x_size,(int)jidest.y_size);
		}
		if(jidest.y_loc<0){
			jidest.y_loc=0;
			setSize((int)jidest.x_size,(int)jidest.y_size-8);
		}
		if(jidest.fontName==null){
			jidest.fontName = "Inconsolata";
		}
		if(jidest.fontSize==0){
			jidest.fontSize = 12;
		}
		if(jidest.wasDownloaded){
			for(File n:new File(jidest.YATE_FOLDER_PATH+File.separator+"fonts").listFiles()){
				try{
	    			Font tnof = Font.createFont(Font.TRUETYPE_FONT,n);
	    			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(tnof);
	    		}catch(FontFormatException | IOException e){}
			}
		}
		try{
			Font tnof;
			try {
				tnof = Font.createFont(Font.TRUETYPE_FONT,new File(jidest.class.getResource("/defaultfonts/monaco.ttf").toURI()));
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(tnof);
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		}catch(FontFormatException e){e.printStackTrace();}
//		for(String sssss:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
//			System.out.println(sssss);
//		}
		for(String as:asaves.list()){
			if(as.endsWith("_as")){
				nme.add(as.substring(0, as.indexOf("_as")));
			}
		}
		//bottombar = new BottomBar();
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
					files.setMinimumSize(new Dimension(jidest.MainWindow.getWidth()/3*2,200));
					splash.dispose();
					nm.setVisible(true);
				}else{
					setVisible(true);
					files.setMinimumSize(new Dimension(jidest.MainWindow.getWidth()/3*2,200));
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
					aSWarning nm = new aSWarning(nme,jidest.MainWindow);
					setVisible(true);
					files.setMinimumSize(new Dimension(jidest.MainWindow.getWidth()/3*2,200));
					splash.dispose();
					nm.setVisible(true);
				}else{
					setVisible(true);
					files.setMinimumSize(new Dimension(jidest.MainWindow.getWidth()/3*2,200));
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
		this.setJMenuBar(menubar0);
		add(menubuf,BorderLayout.NORTH);
		center = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,dbs,files);
		center.setOneTouchExpandable(true);
		center.setDividerLocation(150);
		System.out.println(dbs.getContent().dirTree.getWidth());
		files.setMinimumSize(new Dimension(getWidth()-dbs.getContent().dirTree.getWidth(),200));
		add(center,BorderLayout.CENTER);
		lnpanel.setBackground(Color.red);
		validate();
		this.addComponentListener(this);
		try{
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
				files.setMinimumSize(new Dimension(this.getWidth()/3*2,200));
				splash.dispose();
				splash.testpro.dispose();
				splash.test.dispose();
				splash.outputback.dispose();
				nm.setVisible(true);
			}else{
				setVisible(true);
				files.setMinimumSize(new Dimension(this.getWidth()/3*2,200));
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
		files.setMinimumSize(new Dimension(this.getWidth()/3*2,this.getHeight()));
		try{center.setDividerLocation(this.getWidth()/3);}catch(NullPointerException e){}
		Scanner job = new Scanner(jidest.settingsFile);
		boolean there = false;
		String buffer = "";
		while(job.hasNextLine()){
			String line = job.nextLine();
			if(line.startsWith("window.size")){
				buffer+=("window.size:"+jidest.x_size+","+jidest.y_size+System.lineSeparator());
				there = true;
			}else if(line.startsWith("window.maximized")&&!jidest.isMaximized){
				buffer+=("window.maximized:false");
			}else{
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
			}else {
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
	
	public void setWinMax() throws FileNotFoundException{
		files.setMinimumSize(new Dimension(this.getWidth()/3*2,this.getHeight()));
		center.setDividerLocation(this.getWidth()/5);
		wasMaximized=true;
		Scanner job = new Scanner(jidest.settingsFile);
		boolean there = false;
		String buffer = "";
		while(job.hasNextLine()){
			String line = job.nextLine();
			if(line.startsWith("window.maximized")){
				there=true;
				buffer+=("window.maximixed:true"+System.lineSeparator());
			}else{
				buffer+=(line+System.lineSeparator());
			}
		}
		job.close();
		PrintWriter jobo = new PrintWriter(jidest.settingsFile);
		if(!there)
			buffer+=("window.maximized:true");
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
			prevx=(int) jidest.x_loc;
			prevy=(int) jidest.y_loc;
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
		if(arg0.getSource().equals(this)){
			if(this.getExtendedState()!=JFrame.MAXIMIZED_BOTH){
				if(wasMaximized){
					jidest.isMaximized=false;
					setSize((int)jidest.x_size,(int)jidest.y_size);
					//bottombar.morespace.setMaximumSize(bottombar.morespace.getMinimumSize());
					wasMaximized=false;
				}else{
					try {
						jidest.x_size=getWidth();
						jidest.y_size=getHeight();
					//	bottombar.morespace.setMaximumSize(bottombar.morespace.getMinimumSize());
						setWinSize();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}else{
				jidest.isMaximized=true;
				try {
					setWinMax();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				jidest.x_loc = prevx;
				jidest.y_loc=prevy;
				try {
					setWinLoc();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}
	
	public void open(String fileName){
//		Opener o = ;
		System.out.println("started to open");
		new Thread(new Opener(fileName,this)).start();
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
				//pathFinder = new Scanner(text);
				//PrintWriter saveOut = new PrintWriter(new File(path));
//				while(pathFinder.hasNextLine()){
//					saveOut.println(pathFinder.nextLine());
//				}
				//pathFinder.close();
				//saveOut.print(new String(text.getBytes(),((editorScroll)files.getSelectedComponent()).getEncoding()));
				//saveOut.flush();
				//saveOut.close();
				//InputStreamReader in = new InputStreamReader(new InputStream(text)),((editorScroll)files.getSelectedComponent()).getEncoding());
				
				FileOutputStream fos = new FileOutputStream(new File(path));
				Writer out = new OutputStreamWriter(fos,((editorScroll)files.getSelectedComponent()).getEncoding());
				out.write(text);
				out.flush();
				out.close();
				as.renameTo(new File(as.getAbsolutePath().substring(0,as.getAbsolutePath().lastIndexOf("_as"))+"_s"));
			}catch(IOException e) {
				e.printStackTrace();
			}
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
				//pathFinder = new Scanner(text);
//				PrintWriter saveOut = new PrintWriter(new File(path));
//				PrintWriter rerw = new PrintWriter(as);
//				rerw.println(new File(path).getAbsolutePath());
////				while(pathFinder.hasNextLine()){
////					String ssss=pathFinder.nextLine();
////					saveOut.println(ssss);
////					rerw.println(ssss);
////				}
//				rerw.print(new String(text.getBytes(),((editorScroll)files.getSelectedComponent()).getEncoding()));
//				rerw.flush();
//				rerw.close();
//				//pathFinder.close();
//				saveOut.print(new String(text.getBytes(),((editorScroll)files.getSelectedComponent()).getEncoding()));
//				saveOut.flush();
//				saveOut.close();
				FileOutputStream sos = new FileOutputStream(as);
				Writer sout = new OutputStreamWriter(sos,((editorScroll)files.getSelectedComponent()).getEncoding());
				sout.write(path + System.lineSeparator()+text);
				FileOutputStream fos = new FileOutputStream(new File(path));
				Writer out = new OutputStreamWriter(fos,((editorScroll)files.getSelectedComponent()).getEncoding());
				out.write(text);
				out.flush();
				out.close();
				sout.flush();
				sout.close();
				files.getSelectedComponent().setName(path.substring(path.lastIndexOf(File.separator)+1));
				files.setTitleAt(files.getSelectedIndex(),files.getSelectedComponent().getName());
				as.renameTo(new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+path.substring(path.lastIndexOf(File.separator)+1)+"_s"));
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void windowClosing(WindowEvent arg0) {
		for(int i=0;i<files.getComponentCount();i++){
			((editorScroll)files.getComponentAt(i)).editorinst.autosave();
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
}