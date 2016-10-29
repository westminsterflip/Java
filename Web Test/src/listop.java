import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

@SuppressWarnings("serial")
public class listop extends JFrame implements ActionListener{
	JPanel but = new JPanel();
	JLabel what = new JLabel("What would you like to do?");
	boolean update=false;
	JButton up = new JButton("Update");
	JButton comp = new JButton("Compile");
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel all = new JPanel();
	JPanel whatcon = new JPanel();
	JPanel fill = new JPanel();
	JPanel fill1 = new JPanel();
	JPanel fill2 = new JPanel();
	Dimension j = new Dimension(40,10);

	public listop(){
		super("Wikipedia-Downloader");
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
		all.setLayout(new BoxLayout(all,BoxLayout.Y_AXIS));
		whatcon.setLayout(new BorderLayout());
		fill.setPreferredSize(j);
		fill1.setPreferredSize(j);
		fill2.setPreferredSize(j);
		but.add(up);
		but.add(comp);
		whatcon.add(what,BorderLayout.CENTER);
		whatcon.add(fill,BorderLayout.NORTH);
		whatcon.add(fill1,BorderLayout.EAST);
		whatcon.add(fill2,BorderLayout.WEST);
		up.addActionListener(this);
		comp.addActionListener(this);
		all.add(whatcon);
		all.add(but);
		add(all);
		pack();
		setVisible(true);
		setResizable(false);
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @SuppressWarnings("unused")
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        guirn n = new guirn(guirn.filepath);
		    }
		});
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(up)){
			update = true;
		}
		try {
			PrintWriter kk = new PrintWriter(new File("wls.bat"));
			kk.print("java wikilist "+update);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		Runtime rt = Runtime.getRuntime();
		setVisible(false);
		try {
			Process pr = rt.exec("cmd /c start wls.bat");
		} catch (IOException e1){
			e1.printStackTrace();
		}
		dispose();
		guirn n = new guirn(guirn.filepath);
	}

}
