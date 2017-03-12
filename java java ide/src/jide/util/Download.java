package jide.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import jide.jidest;
import jide.parts.aSWarning;

public class Download implements Runnable{
	public Download(){}
	
	public void run(){
		int cnt=0;
		new File(jidest.YATE_FOLDER_PATH+File.separator+"fonts").mkdir();
		byte[] buffer = new byte[1024];
		try{
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new URL("https://github.com/google/fonts/archive/master.zip").openStream()));
	    	ZipEntry ze = zis.getNextEntry();
	    	while(ze!=null){
	    		String fileName = ze.getName();
	    		if(fileName.endsWith(".ttf")){
	    			File newFile = new File(jidest.YATE_FOLDER_PATH + File.separator + "fonts" + File.separator + fileName.substring(fileName.lastIndexOf('/')+1));
	    			jidest.MainWindow.splash.widen();
	    			jidest.MainWindow.splash.output.setText(newFile.getName());
		    		FileOutputStream fos = new FileOutputStream(newFile);
		    		int len;
		    		while ((len = zis.read(buffer)) > 0) {
		    			fos.write(buffer, 0, len);
		    		}
		    		fos.close();
		    		Font tnof = Font.createFont(Font.TRUETYPE_FONT,newFile);
		    		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(tnof);
	    		}
	    		ze = zis.getNextEntry();
	    	}
	        zis.closeEntry();
	    	zis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(cnt>jidest.MainWindow.splash.ttfpro.getMaximum()){
			try{
				List<String> lines = Files.readAllLines(jidest.settingsFile.toPath());
				PrintWriter lineChanger = new PrintWriter(jidest.settingsFile);
				for(String exciting:lines){
					if(exciting.startsWith("font.file.number")){
						lineChanger.println("font.file.number:"+cnt);
					}else{
						lineChanger.println(exciting);
					}
				}
				lineChanger.flush();
				lineChanger.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		try{
			List<String> lines = Files.readAllLines(jidest.settingsFile.toPath());
			PrintWriter lineChanger = new PrintWriter(jidest.settingsFile);
			for(String exciting:lines){
				if(exciting.startsWith("check.font.download.on.start")){
					lineChanger.println("check.font.download.on.start:"+false);
				}else{
					lineChanger.println(exciting);
				}
			}
			lineChanger.flush();
			lineChanger.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		jidest.MainWindow.splash.dispose();
		jidest.MainWindow.splash.test.setVisible(false);
		jidest.MainWindow.splash.testpro.setVisible(false);
		jidest.MainWindow.splash.testpro.dispose();
		jidest.MainWindow.splash.test.dispose();
		jidest.MainWindow.splash.outputback.dispose();
		if(jidest.MainWindow.nme.size()!=0){
			aSWarning nm = new aSWarning(jidest.MainWindow.nme,jidest.MainWindow);
			jidest.MainWindow.setVisible(true);
			jidest.MainWindow.splash.dispose();
			nm.setVisible(true);
		}else{
			jidest.MainWindow.setVisible(true);
			jidest.MainWindow.splash.dispose();
		}
	}
}
