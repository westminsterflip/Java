import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class mklist {
	ArrayList<String> list = new ArrayList<String>();
	
	public mklist(String pth){
		makesey(pth);
	}
	
	public void makesey(String path){
		File readli = new File(path + "list\\readablelist.27");
		File tbtested;
		if(readli.exists()){
			try {
				PrintWriter j = new PrintWriter(readli);
				j.close();
			} catch (FileNotFoundException e) {
				System.out.println("Well this shouldn't be possible");
			}
		}else{
			try {
				readli.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(String urlpt:list){
			String flname = urlpt;
	        while(flname.indexOf('%')!=-1){
	        	int tmp = flname.indexOf('%');
	        	String cha = flname.substring(tmp+1,tmp+3);
	        	String t = (char)(int)Integer.valueOf(cha, 16) + "";
	        	if(t.equals("%"))
	        		t="~|~";
	        	flname = flname.substring(0, tmp) + t + flname.substring(tmp+3);
	        }
	        while(flname.indexOf("~|~")!=-1){
	        	int tmp = flname.indexOf("~|~");
	        	flname = flname.substring(0, tmp) + '%' + flname.substring(tmp+3);
	        }
	        tbtested = new File(flname + ".html");
	        if(tbtested.exists()){
	        	try {
					Files.write(Paths.get(path + "list\\readablelist.27"), (flname + '\n').getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
	}
}
