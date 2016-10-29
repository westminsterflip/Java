import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
	String filepath = null;

	public guirn(String flpth){
		super("Wikipedia-Downloader");
		filepath = flpth;
		if(filepath.charAt(filepath.length()-1)!=-1)
			filepath+='\\';
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
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
