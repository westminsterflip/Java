import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class wlistgui extends JFrame{
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	public static JTextArea output = new JTextArea(20,75);
	public String j;
	public ArrayList<String> list = new ArrayList<String>();
	JPanel m = new JPanel();
	
	@SuppressWarnings("unused")
	public wlistgui(String lnl){
		super("Wikipedia-Downloader");
		JLabel lbl = new JLabel(lnl);
		System.out.println(lbl.getText());
		m.add(lbl);
		//output.setText("maybe here");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	    	e.printStackTrace();
	    }
	    catch (ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	    catch (InstantiationException e) {
	    	e.printStackTrace();
	    }
	    catch (IllegalAccessException e) {
	    	e.printStackTrace();
	    }
		add(lbl);
		lbl.paintImmediately(lbl.getVisibleRect());
		System.out.println(lbl.getText());
		pack();
		setVisible(true);
		setResizable(false);
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        guirn n = new guirn(guirn.filepath);
		    }
		});
	}
}
