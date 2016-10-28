import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class libsrch {
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> lstin = new ArrayList<String>();
	public boolean al;
	
	public libsrch(String path, String tbf) throws IOException{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader n = new BufferedReader(k);
		System.out.println("Include results in files? (Will probably return many more more, but not relavent results.");
		System.out.println("This method will take much longer.  true or false)");
		boolean in = Boolean.parseBoolean(n.readLine());
		al=in;
		File li = new File(path + "list\\readablelist.27");
		try{
			Scanner lsscn = new Scanner(li);
			String john = null;
			boolean found = false;
			while((john=lsscn.nextLine())!=null){
				if(john.indexOf(tbf)!=-1){
					list.add(john);
					found = true;
				}
				File tmp = new File(path + john + ".html");
				if(tmp.exists()&&in){
					Scanner flscn = new Scanner(tmp);
					String james = null;
					while((james=flscn.nextLine())!=null){
						if(james.indexOf(tbf)!=-1&&list.indexOf(john)==-1){
							lstin.add(john);
						}
					}
				}
			}
			lsscn.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public String gtli(){
		String out = "";
		out+=("Articles: " + '\n');
		for(String tmp:list){
			out+=(tmp+'\n');
		}
		if(al){
			out+=("Also found in: " + '\n');
			for(String tmp:lstin){
				out+=(tmp+'\n');
			}
		}
		return out;
	}
}
