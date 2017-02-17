package jide;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public jidest() throws FileNotFoundException{
		try {
   			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   	    }
   	    catch (Exception e) {
   	    	e.printStackTrace();
   	    }
		new yatespash();
	}
	
	public static char osc(){
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
