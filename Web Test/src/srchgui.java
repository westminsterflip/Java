import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class srchgui extends JFrame implements ActionListener{
	JTextField srch4 = new JTextField(50);
	JButton srch = new JButton("Search");
	JPanel search = new JPanel();
	JTextArea rslts = new JTextArea(10,50);
	JScrollPane rsltbx = new JScrollPane(rslts);
	JPanel open = new JPanel();
	JTextField opnths = new JTextField(50);
	JButton opn = new JButton("Open");
	JLabel no = new JLabel("You must first generate the list.");
	JButton oh = new JButton("Okay");
	JPanel ohcont = new JPanel();
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel ohco = new JPanel();
	JPanel fill = new JPanel();
	JPanel allcont = new JPanel();
	JButton n = new JButton("Yes");
	JButton m = new JButton("No");
	JPanel o = new JPanel();
	
	public srchgui(){
		super("Wikipedia-Downloader Library Search");
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
		File li = new File(guirn.filepath + "list\\readablelist.27");
		if(!li.exists()){
			fill.add(no);
			ohco.add(oh);
			ohcont.setLayout(new BoxLayout(ohcont,BoxLayout.Y_AXIS));
			ohcont.add(fill);
			ohcont.add(ohco);
			oh.addActionListener(this);
			add(ohcont);
		}else{
			search.add(srch4);
			search.add(srch);
			allcont.setLayout(new BoxLayout(allcont,BoxLayout.Y_AXIS));
			allcont.add(search);
			allcont.add(rsltbx);
			open.add(opnths);
			open.add(opn);
			allcont.add(open);
			add(allcont);
			srch.addActionListener(this);
			opn.addActionListener(this);
			rslts.setEditable(false);
			rslts.setLineWrap(true);
		}
		pack();
		rslts.setSize(getWidth(), 50);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(oh)){
			dispose();
			guirn p = new guirn(guirn.filepath);
		}else if(e.getSource().equals(srch)){
			JFrame iffy = new JFrame("Wikipedia-Downloader Library Search");
			iffy.setLayout(new BoxLayout(iffy,BoxLayout.Y_AXIS));
			iffy.add(new JPanel().add(new JLabel("Search in files? (Will take longer/produce irrelevant results)")));
			n.addActionListener(this);
			o.add(n);
			o.add(m);
			m.addActionListener(this);
			iffy.add(o);
		}else if(e.getSource().equals(n)){
			try {
				libsrch ls1 = new libsrch(guirn.filepath,srch4.getText(),true);
				rslts.setText(ls1.gtli());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource().equals(m)){
			try {
				libsrch ls1 = new libsrch(guirn.filepath,srch4.getText(),false);
				rslts.setText(ls1.gtli());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource().equals(opn)){
			File tbo = new File(guirn.filepath + opnths.getText() + ".html");
			try {
				opener op = new opener(tbo);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
