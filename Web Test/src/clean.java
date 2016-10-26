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
		    	String tline1="";
		    	Scanner scanner1 = new Scanner(first);
		    	boolean found = false;
			    while (scanner1.hasNextLine()&&!found){ 
			    	String line = scanner1.nextLine();
			    	if(line.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">")!=-1){
			    		found=true;
			    		//System.out.println("Beginning: " + line.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">"));
			    		//System.out.println("End: " + line.indexOf("</h1>",line.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">")));
			    		tline1 = line.substring(line.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">")+53/*, line.indexOf("</h1>",line.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">"))*/);
			    		//System.out.println(tline1);
			    		tline1 = tline1.substring(0,tline1.indexOf("</h1>"));
			    		System.out.println(tline1);
			    	}
			    }
			    scanner1.close();
		    	for(String p24:filenames){
		    		String tline2="";
		    		if(!p23.equals(p24)){
		    			File second = new File(p24);
		    			Scanner scanner2 = new Scanner(second);
		    			found=false;
					    while (scanner2.hasNextLine()&&!found) {
					    	String line = scanner2.nextLine();
					    	if(line.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">")!=-1){
					    		found=true;
					    		tline2 = line.substring(line.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">")+53);
					    		tline2 = line.substring(0, tline2.indexOf("</h1>"));
					    	}
					    }
					    scanner2.close();
					    if(tline1.equals(tline2)){
					    	second.delete();
					    	System.out.println(tline1 + " is the same as " + tline2);
					    }
		    		}
		    	}
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
