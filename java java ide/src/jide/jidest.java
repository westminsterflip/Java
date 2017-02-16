package jide;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

//YATE
public class jidest {
	public static String YATE_FOLDER_PATH=null;
	public static char OS = 'o';
	public static boolean is64 = false;
	public static Dimension scrSize= null;
	
	public jidest() throws FileNotFoundException{
		new yatespash();
		String path = System.getProperty("user.home");
		switch(osc()){
		case 'w': path+="\\AppData\\Roaming\\YATE\\";
			if(System.getProperty("os.arch").equals("amd64"))
				is64=true;break;
		case 'm': path+="/Library/Application Support/YATE/";
			if(System.getProperty("os.arch").equals("ppc"))
				is64=true;break;
		case 'l': path+="/.YATE/";
			if(System.getProperty("os.arch").equals("amd64"))
				is64=true;break;
		default: path="";
		}
		YATE_FOLDER_PATH=path;
		new File(path).mkdir();
		new mainwin();
		
	}
	
	private char osc(){
		char ou = 'o';
		String tob = System.getProperty("os.name").toLowerCase();
		if(tob.indexOf("win")!=-1)
			ou = 'w';
		else if(tob.indexOf("mac")!=-1)
			ou = 'm';
		else if(tob.indexOf("nix")!=-1||tob.indexOf("nux")!=-1||tob.indexOf("aix")!=-1)
			ou = 'l';
		OS=ou;
		return ou;
	}
	
	public static void main(String[] js) throws FileNotFoundException{
		new jidest();
	}
}
