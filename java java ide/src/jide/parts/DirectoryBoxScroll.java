package jide.parts;

import java.awt.Color;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class DirectoryBoxScroll extends JScrollPane{
	public static DirectoryBox direcbox = new DirectoryBox();
	public DirectoryBoxScroll(){
		super(direcbox);
		setSize(150,150);
		this.getContent().setBackground(Color.white);
	}
	
	public DirectoryBox getContent(){
		return direcbox;
	}
}
