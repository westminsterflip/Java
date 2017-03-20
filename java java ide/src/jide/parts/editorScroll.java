package jide.parts;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class editorScroll extends JScrollPane implements ComponentListener{
	public static editor editorinst;
	public String content;
	static String name="";
	//JPanel panel = new JPanel();
	public editorScroll(String content,String fileName, Dimension availableSize, String encoding){
		super();
		editorinst=new editor(content,fileName,encoding);
		TextLineNumber tln = new TextLineNumber(editorinst,1);
		this.setViewportView(editorinst);
		this.setRowHeaderView(tln);
		this.content=content;
		setSize(availableSize);
		name=fileName;
	}
	
	public String getEncoding(){
		return editorinst.encoding;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String s){
		name = s;
		editorinst.flname=name;
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}
}
