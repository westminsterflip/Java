package jide.parts;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.undo.UndoManager;

import jide.jidest;

@SuppressWarnings("serial")
public class editor extends JTextPane implements DocumentListener{
	String flname;
	Timer asTimer;
	JPanel test = new JPanel();
	public UndoManager undoredo = new UndoManager();
	public String encoding;
	public editor(String content, String fileName, String encoding){
		super();
		setText(content);
		this.setFont(new Font(jidest.fontName,Font.PLAIN,jidest.fontSize));
		flname=fileName;
		asTimer = new Timer(2000,new asclass());
		asTimer.setRepeats(false);
		this.encoding=encoding;
		this.getDocument().addDocumentListener(this);
		this.getDocument().addUndoableEditListener(undoredo);
	}
	
	public void autosave(){
		this.setEditable(false);
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
//			PrintWriter saver = new PrintWriter(as);
//			saver.println(path);
//			saver.print(new String(this.getText().getBytes(),"Cp1252"));
//			saver.flush();
//			saver.close();
			FileOutputStream fos = new FileOutputStream(new File(path));
			Writer out = new OutputStreamWriter(fos,encoding);
			out.write(this.getText());
			out.flush();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setEditable(true);
	}

	public class asclass implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			autosave();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		if(!undoredo.canUndo()){
			jidest.MainWindow.menubar0.undo.setEnabled(false);
			jidest.MainWindow.tb.undo.setEnabled(false);
		}else{
			jidest.MainWindow.menubar0.undo.setEnabled(true);
			jidest.MainWindow.tb.undo.setEnabled(true);
		}
		asTimer.restart();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		if(!undoredo.canUndo()){
			jidest.MainWindow.menubar0.undo.setEnabled(false);
			jidest.MainWindow.tb.undo.setEnabled(false);
		}else{
			jidest.MainWindow.menubar0.undo.setEnabled(true);
			jidest.MainWindow.tb.undo.setEnabled(true);
		}
		asTimer.restart();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		if(!undoredo.canUndo()){
			jidest.MainWindow.menubar0.undo.setEnabled(false);
			jidest.MainWindow.tb.undo.setEnabled(false);
		}else{
			jidest.MainWindow.menubar0.undo.setEnabled(true);
			jidest.MainWindow.tb.undo.setEnabled(true);
		}
		asTimer.restart();
	}
}
