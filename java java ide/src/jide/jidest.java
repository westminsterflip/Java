package jide;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.UIManager;

//YATE
public class jidest {
	public static String YATE_FOLDER_PATH=null;
	public static char OS = 'o';
	public static boolean is64 = false;
	public final static Dimension scrSize= Toolkit.getDefaultToolkit().getScreenSize();
	public static File settingsFile = null;
	public static double x_size = 0,y_size=0,x_loc=-8,y_loc=0;
	public final static Dimension scrSizeUs = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
	public final Object classex = getClass();
	public static int asFrequency = 15;
	public static ArrayList<KeyEvent> keysToWatch = new ArrayList<KeyEvent>();
	public static String jdkPath = "jdk";
	public static String lastDir = "";
	public static String fontName = null;
	public static int fontSize = 0;
	public static int fontFileNumber=2089;
	public static boolean checkOnOpen = true;
	public static mainwin MainWindow;
	public static boolean wasDownloaded = false;
	public static boolean isMaximized = false;
	public jidest(){
		try {
   			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   	    }
   	    catch (Exception e) {
   	    	e.printStackTrace();
   	    }
		MainWindow=new mainwin();
	}
	
	public static void main(String[] js){
		new jidest();
	}
}
