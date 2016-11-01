import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class guirn extends JFrame implements ActionListener{
	JPanel but = new JPanel();
	JPanel buf = new JPanel();
	JRadioButton lst = new JRadioButton("Compile or update article list");
	JRadioButton dl = new JRadioButton("Download from article list");
	JRadioButton gnli = new JRadioButton("Generate list of downloaded articles");
	JRadioButton cln = new JRadioButton("Remove duplicates (Normally no need to run this)");
	JRadioButton sch = new JRadioButton("Search downloaded library");
	JButton go = new JButton("Go");
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	static String filepath = null;
	int todo=0;

	public guirn(String flpth){
		super("Wikipedia-Downloader");
		filepath = flpth;
		if(filepath.length()!=0)
			if(filepath.charAt(filepath.length()-1)!='\\')
				filepath+='\\';
		File m = new File(filepath);
		if(!m.exists())
			m.mkdirs();
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
		ButtonGroup n = new ButtonGroup();
		lst.setMnemonic(KeyEvent.VK_C);
		dl.setMnemonic(KeyEvent.VK_D);
		gnli.setMnemonic(KeyEvent.VK_G);
		cln.setMnemonic(KeyEvent.VK_R);
		sch.setMnemonic(KeyEvent.VK_S);
		lst.addActionListener(this);
		dl.addActionListener(this);
		gnli.addActionListener(this);
		cln.addActionListener(this);
		sch.addActionListener(this);
		go.addActionListener(this);
		lst.setSelected(true);
		n.add(lst);
		n.add(dl);
		n.add(gnli);
		n.add(cln);
		n.add(sch);
		Dimension j = new Dimension(100,26);
		go.setPreferredSize(j);
		buf.add(go);
		but.setLayout(new BoxLayout(but,BoxLayout.Y_AXIS));
		but.add(lst);
		but.add(dl);
		but.add(gnli);
		but.add(cln);
		but.add(sch);
		but.add(buf);
		add(but);
		pack();
		setVisible(true);
		setResizable(false);
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(lst)){
			todo = 0;
		}else if(e.getSource().equals(dl)){
			todo = 1;
		}else if(e.getSource().equals(gnli)){
			todo = 2;
		}else if(e.getSource().equals(cln)){
			todo = 3;
		}else if(e.getSource().equals(sch)){
			todo = 4;
		}else if(e.getSource().equals(go)){
			switch(todo){
				case 0: dispose();listop n = new listop();break;
				case 1: dispose();dlop m = new dlop();break;
				case 2: go.setEnabled(false);Runtime rt = Runtime.getRuntime();
						try {
							PrintWriter kk = new PrintWriter(new File("ml.bat"));
							kk.print("java mklist "+filepath);
							kk.close();
						} catch (FileNotFoundException e2) {
							e2.printStackTrace();
						}
						try {
							Process pr = rt.exec("cmd /c start ml.bat");
						}catch (IOException e1){
							e1.printStackTrace();
						}go.setEnabled(true);break;
				case 3: go.setEnabled(false);Runtime rt1 = Runtime.getRuntime();
						try {
							PrintWriter kk = new PrintWriter(new File("c.bat"));
							kk.print("java clean "+filepath);
							kk.close();
						} catch (FileNotFoundException e2) {
							e2.printStackTrace();
						}
						try {
							Process pr = rt1.exec("cmd /c start c.bat");
						}catch (IOException e1){
							e1.printStackTrace();
						}go.setEnabled(true);break;
				case 4: dispose();srchgui s = new srchgui();break;
			}
		}
	}
	
}
