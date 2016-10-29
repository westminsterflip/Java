import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class flnameset extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1220934862822261410L;
	JPanel win = new JPanel();
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	JTextField pthbx = new JTextField(30);
	JButton ok = new JButton("OK");
	JButton brows = new JButton("Browse");
	JLabel lbl = new JLabel("Filepath:  ");
	JPanel fill = new JPanel();
	JPanel fill1 = new JPanel();
	JPanel fill2 = new JPanel();
	JPanel ctr = new JPanel();
	
	public flnameset(){
		super("Wikipedia-Downloader Filepath Set");
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
		fill.setSize(393, 10);
		fill1.setSize(10,10);
		fill2.setSize(10,10);
		setLayout(new BorderLayout());
		ctr.setLayout(new BorderLayout());
		ctr.add(lbl, BorderLayout.WEST);
		ctr.add(pthbx, BorderLayout.EAST);
		Dimension j = new Dimension(100,26);
		ok.setPreferredSize(j);
		pthbx.setText(System.getProperty("user.dir"));
		brows.setPreferredSize(j);
		ok.addActionListener(this);
		brows.addActionListener(this);
		win.add(ok);
		win.add(brows);
		add(fill2,BorderLayout.WEST);
		add(ctr,BorderLayout.CENTER);
		add(fill,BorderLayout.NORTH);
		add(win,BorderLayout.SOUTH);
		add(fill1,BorderLayout.EAST);
		setVisible(true);
		pack();
		setResizable(false);
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		//System.out.println(ok.getSize().getHeight() + " width:  " + ok.getSize().getWidth());
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ok)){
			setVisible(false);
			guirn guiurn = new guirn(pthbx.getText());
			dispose();
		}else{
			setVisible(false);
			JFileChooser neat = new JFileChooser();
			neat.setCurrentDirectory(new File(System.getProperty("user.dir")));
			neat.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			neat.setAcceptAllFileFilterUsed(false);
			if(neat.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
				if(neat.getSelectedFile().getAbsolutePath().length()==0){
					pthbx.setText(neat.getCurrentDirectory().getAbsolutePath());
				}else{
					pthbx.setText(neat.getSelectedFile().getAbsolutePath());
				}
			}
			setVisible(true);
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] thiscanbemostanything){
		flnameset dontset = new flnameset();
	}
}
