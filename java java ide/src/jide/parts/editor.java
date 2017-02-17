package jide.parts;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import jide.jidest;

@SuppressWarnings("serial")
public class editor extends JTextArea implements KeyListener{
	node last;
	node toRedo;
	String flname;
	int ops = 0;
	public editor(String content, String fileName){
		super(content);
		this.addKeyListener(this);
		flname=fileName;
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
		File as = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+flname+".as");
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
			saver.print(last.getStep());
			saver.flush();
			saver.close();
			System.out.println("SAVED");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void asCounter(){
		last = new node(getText(),last);
		if(ops==jidest.asFrequency-1){
			JTabbedPane tmp = (JTabbedPane) this.getParent().getParent().getParent();
			for(int i=0;i<tmp.getTabCount();i++){
				if(i!=tmp.getSelectedIndex())
					tmp.setEnabledAt(i, false);
			}
			autosave();
			for(int i=0;i<tmp.getTabCount();i++){
				tmp.setEnabledAt(i, true);
			}
			ops=0;
		}else
			ops++;
	}

	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_V&&e.isControlDown())
			asCounter();
		else if(e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_DELETE||e.getKeyCode()==KeyEvent.VK_BACK_SPACE||e.getKeyCode()==KeyEvent.VK_TAB)
			asCounter();
		else if(e.getKeyCode()==KeyEvent.VK_X&&e.isControlDown()){
			asCounter();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()==' '||e.getKeyChar()=='.'||e.getKeyChar()==';'||e.getKeyChar()==':'||e.getKeyChar()=='+'||e.getKeyChar()=='-'||e.getKeyChar()=='*'){
			asCounter();
		}
	}
}
