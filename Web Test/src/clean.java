import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class clean {
	
	public ArrayList<String> filenames = new ArrayList<String>();
	
	public clean(){
		undupe();
	}
	
	public void undupe(){
		File je = new File("knownwikis.wot");
		try{
		    Scanner scanner = new Scanner(je);
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String flname = line.substring(3,line.indexOf(":::",2)) + ".html";
		        filenames.add(flname);
		    }
		    //System.out.println(filenames);
		    scanner.close();
		    for(String p23:filenames){
		    	File first = new File(p23);
		    	Scanner scanner1 = new Scanner(first);
		    	boolean found = false;
			    while (scanner1.hasNextLine()&&!found){ 
			    	String line = scanner1.nextLine();
			    	if(line.indexOf("Redirected from")!=-1){
			    		found=true;
			    	}
			    }
			    scanner1.close();
		    	if(found)
		    		first.delete();
		    }
		}catch(FileNotFoundException e){ 
			e.printStackTrace();
			System.out.println("Did you actually download anything before running this?");
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] crepes){
		clean dirty = new clean();
	}
	
}
