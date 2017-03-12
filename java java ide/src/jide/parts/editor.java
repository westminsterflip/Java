package jide.parts;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jide.jidest;

@SuppressWarnings("serial")
public class editor extends JTextPane implements KeyListener,DocumentListener,ComponentListener{
	node last;
	node toRedo;
	String flname;
	Timer asTimer;
	JPanel test = new JPanel();
	public editor(String content, String fileName){
		super();
		setText(content);
		this.setFont(new Font(jidest.fontName,Font.PLAIN,jidest.fontSize));
		this.addKeyListener(this);
		
		
		//test.setSize(20,this.getHeight());
//		test.add(getLineNums());
//		add(test,BorderLayout.EAST);
		//add(new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		//add(getLineNums(),BorderLayout.EAST);
		flname=fileName;
		asTimer = new Timer(2000,new asclass());
		asTimer.setRepeats(false);
		this.getDocument().addDocumentListener(this);
		this.addComponentListener(this);
	}
	
	public JLabel getLineNums(){
		String out = "<html>";
		for(int i=1;i<=getDocument().getDefaultRootElement().getElementCount();i++){
			out += (i+"<br>");
		}
		out+= "</html>";
		return new JLabel(out);
	}
	
	public boolean redo(){
		
		this.setText(toRedo.step);
		last = new node(toRedo.getStep(),last);
		toRedo = toRedo.lastNode();
		if(toRedo==null)
			return false;
		return true;
	}
	
	public boolean undo(){
		this.setText(last.step);
		toRedo = new node(last.getStep(),toRedo);
		last = last.lastNode();
		if(last==null)
			return false;
		return true;
	}
	
	public class node{
		private String step;
		private node laststep;
		public node(String s,node l){
			step = s;
			laststep=l;
		}
		public node lastNode(){
			return laststep;
		}
		public String getStep(){
			return step;
		}
	}
	
	public void autosave(){
		last = new node(this.getText(),last);
		File as = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+flname+"_as");
		if(new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+flname+"_s").exists())
			new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+flname+"_s").renameTo(as);
		String path="";
		Scanner pathFinder;
		try {
			pathFinder = new Scanner(as);
			if(pathFinder.hasNextLine())
				path = pathFinder.nextLine();
			pathFinder.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			PrintWriter saver = new PrintWriter(as);
			saver.println(path);
			saver.print(last.step);
			saver.flush();
			saver.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void movesaver(){
		last = new node(this.getText(),last);
	}

	public class asclass implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			autosave();
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_V&&e.isControlDown())
			movesaver();
		else if(e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_DELETE||e.getKeyCode()==KeyEvent.VK_BACK_SPACE||e.getKeyCode()==KeyEvent.VK_TAB)
			movesaver();
		else if(e.getKeyCode()==KeyEvent.VK_X&&e.isControlDown()){
			movesaver();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()==' '||e.getKeyChar()=='.'||e.getKeyChar()==';'||e.getKeyChar()==':'||e.getKeyChar()=='+'||e.getKeyChar()=='-'||e.getKeyChar()=='*'){
			movesaver();
		}
	}

	
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		asTimer.restart();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		asTimer.restart();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		asTimer.restart();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		test.setSize(20,this.getHeight());
		test.removeAll();
		test.add(getLineNums());
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}
}
