package jide.parts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import jide.jidest;
import jide.mainwin;

@SuppressWarnings("serial")
public class aSWarning extends JDialog implements ActionListener{
	JTextArea files = new JTextArea();
	JLabel warning;
	JButton discardAll = new JButton("Discard All");
	JButton saveAll = new JButton("Save All");
	JButton viewAll = new JButton("View All");
	JScrollPane list = new JScrollPane(files);
	JPanel stuff = new JPanel();
	JPanel buttons = new JPanel();
	JPanel notB = new JPanel();
	JPanel warningLine = new JPanel();
	JPanel idprotect = new JPanel();
	ArrayList<String> fllist;
	
	public aSWarning(ArrayList<String> filelist){
		fllist=filelist;
		setTitle("YATE File Recovery");
		Image m1 = Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+"icon.png"));
		Image m = Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+"warning.png"));
		setIconImage(m1);
		m=m.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		Icon dos = new ImageIcon(m);
		warning = new JLabel("<html><br>YATE has recovered files:<br><br></html>",dos,JLabel.CENTER);
		buttons.add(discardAll);
		buttons.add(saveAll);
		buttons.add(viewAll);
		warning.setFont(new Font("Arial",Font.PLAIN,14));
		files.setFont(new Font("Arial",Font.PLAIN,12));
		list.setPreferredSize(new Dimension(270,80));
		notB.setLayout(new BoxLayout(notB,BoxLayout.Y_AXIS));
		notB.add(new JPanel().add(list));
		stuff.add(notB,BorderLayout.CENTER);
		warningLine.add(warning,BorderLayout.CENTER);
		add(warning,BorderLayout.NORTH);
		add(stuff,BorderLayout.CENTER);
		add(buttons,BorderLayout.SOUTH);
		for(String file:filelist){
			files.append(file + System.lineSeparator());
		}
		discardAll.addActionListener(this);
		saveAll.addActionListener(this);
		viewAll.addActionListener(this);
		setModal(true);
		files.setEditable(false);
		pack();
		setLocation((int)Math.round((jidest.scrSize.getWidth()-getWidth())/2.0),(int)Math.round((jidest.scrSize.getHeight()-getHeight())/2.0));
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(discardAll)){
			discardAll.setEnabled(false);
			saveAll.setEnabled(false);
			viewAll.setEnabled(false);
			for(String fileName:fllist){
				new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+".as").delete();
			}
			dispose();
		}else if(e.getSource().equals(saveAll)){
			discardAll.setEnabled(false);
			saveAll.setEnabled(false);
			viewAll.setEnabled(false);
			for(String filename:fllist){
				File file = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+filename+".as");
				String savePath="";
				try {
					Scanner pathFinder = new Scanner(file);
					savePath = pathFinder.nextLine();
					pathFinder.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Files.copy(file.toPath(),new File(savePath).toPath(),REPLACE_EXISTING);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			dispose();
		}else if(e.getSource().equals(viewAll)){
			discardAll.setEnabled(false);
			saveAll.setEnabled(false);
			viewAll.setEnabled(false);
			for(String fileName:fllist){
				try {
					mainwin.open(fileName);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			dispose();
		}
	}
}
