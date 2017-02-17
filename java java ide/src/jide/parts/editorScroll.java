package jide.parts;

import java.awt.Dimension;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class editorScroll extends JScrollPane{
	static editor editorinst;
	public editorScroll(String content,String fileName, Dimension availableSize){
		super(editorinst=new editor(content,fileName));
		setSize(availableSize);
	}
}
