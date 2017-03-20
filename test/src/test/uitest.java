package test;

import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class uitest {
	public static void main(String[] args){
		try {
   			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   	    }
   	    catch (Exception e) {
   	    	e.printStackTrace();
   	    }
		UIDefaults d = UIManager.getDefaults();
		System.out.println(d.size());
		Enumeration<Object> e = d.keys();
		while(e.hasMoreElements()){
			Object o = e.nextElement();
			if(o.toString().contains("ree"))
				System.out.println(o + " " + d.get(o));
		}
	}
}
