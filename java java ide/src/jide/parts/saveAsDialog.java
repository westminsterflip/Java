package jide.parts;

import java.io.File;
import javax.swing.JFileChooser;

public class saveAsDialog{
	public String pth="";
	public boolean canceled = false;
	public saveAsDialog(String path){
		boolean chosen = false;
		while(!chosen&&!canceled){
			JFileChooser picky = new JFileChooser(path);
			int returnValue = picky.showSaveDialog(null);
			if(returnValue==JFileChooser.APPROVE_OPTION&&!picky.getSelectedFile().exists()){
				File selectedFile = picky.getSelectedFile();
				pth=selectedFile.getAbsolutePath();
				chosen=true;
			}else if(returnValue==JFileChooser.APPROVE_OPTION){
				overwriteWarning oww = new overwriteWarning();
				if(oww.overwrite){
					File selectedFile = picky.getSelectedFile();
					pth=selectedFile.getAbsolutePath();
					chosen=true;
				}
			}else if(returnValue==JFileChooser.CANCEL_OPTION||returnValue==JFileChooser.ERROR_OPTION){
				pth="";
				canceled=true;
			}
		}
	}
}
