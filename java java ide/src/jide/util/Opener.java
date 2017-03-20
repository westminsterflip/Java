package jide.util;

import java.awt.Cursor;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.ibm.icu.text.CharsetDetector;

import jide.jidest;
import jide.mainwin;
import jide.parts.editorScroll;

public class Opener implements Runnable{
	public String fileName;
	public mainwin s;
	public Opener(String file,mainwin w){
		fileName=file;
		s=w;
	}
	
	public void run(){
		try{
			s.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			s.menubar0.enableSaves();
			s.tb.enableSaves();
			String encoding="UTF-8";
			String autosaveString = "";
			String tfileName="";
			if(fileName!=null&&s.fileList.contains(new File(fileName))){
				s.files.setSelectedIndex(s.fileList.indexOf(new File(fileName)));
				return;
			}
			if(fileName==null){
				int no = 1;
				fileName="new"+no;
				while(new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+"_as").exists()){
					fileName = "new"+(++no);
				}
				s.fileList.add(new File(fileName));
				try {
					new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+fileName+"_as").createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				tfileName=fileName;
			}else{
				s.fileList.add(new File(fileName));
				try{
					tfileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
					File fileopen;
					if(fileName.contains("_as"))
						tfileName = tfileName.substring(0,tfileName.lastIndexOf("_as"));
					BufferedInputStream s = new BufferedInputStream(new FileInputStream(new File(fileName)));
					//byte[] bytes = Files.readAllBytes(new File(fileName).toPath());
					CharsetDetector det = new CharsetDetector();
					det.setText(s);
					det.enableInputFilter(false);
					encoding = det.detect().getName();
					InputStreamReader in = new InputStreamReader(new FileInputStream(new File(fileName)),encoding);
					int b;
					while((b=in.read())>-1){
						autosaveString+=(char)b;
					}
					in.close();
					//autosaveString = new String(bytes,encoding);
					fileopen = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+tfileName+"_s");
					if(!fileopen.exists()){
						if(!new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+tfileName+"_as").exists())
							try {
								fileopen.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						else
							fileopen = new File(jidest.YATE_FOLDER_PATH+File.separator+"autosaves"+File.separator+tfileName+"_as");
						fileopen.createNewFile();
						FileOutputStream fos = new FileOutputStream(fileopen);
						Writer out = new OutputStreamWriter(fos,encoding);
						out.write(fileName+System.lineSeparator()+autosaveString);
						System.out.println(autosaveString.length());
						out.flush();
						out.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			editorScroll newedit = new editorScroll(autosaveString,tfileName,new Dimension((int)jidest.x_size,(int)jidest.y_size-s.menubuf.getHeight()),encoding);
			s.files.addTab(tfileName, newedit);
			s.files.setSelectedComponent(s.files.getComponentAt(s.files.getTabCount()-1));
		} finally {
			s.setCursor(Cursor.getDefaultCursor());
		}
	}
}
