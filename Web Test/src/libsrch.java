import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class libsrch {
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> lstin = new ArrayList<String>();
	public boolean al;
	
	public libsrch(String path, String tbf, boolean in) throws IOException{
		File li = new File(path + "list\\readablelist.27");
		try{
			Scanner lsscn = new Scanner(li);
			String john = null;
			while((john=lsscn.nextLine())!=null){
				if(john.indexOf(tbf)!=-1){
					list.add(john);
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
					flscn.close();
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
