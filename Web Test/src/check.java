import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class check {
	ArrayList<String> b = new ArrayList<String>();
	ArrayList<String> e = new ArrayList<String>();
	
	public check(){
		File a = new File("knownwikis.wot");
		try {
			Scanner c = new Scanner(a);
			while(c.hasNextLine()){
				b.add(c.nextLine());
			}
			c.close();
			for(String d:b){
				if(e.indexOf(d)==-1)
					e.add(d);
			}
			Collections.sort(b,String.CASE_INSENSITIVE_ORDER);
			PrintWriter f = new PrintWriter(a);
			String g = "";
			for(String h:e){
				g+=(h+'\n');
			}
			f.print(g);
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(){
		check i = new check();
	}
}