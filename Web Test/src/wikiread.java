import java.io.*;
import java.net.*;

public class wikiread {
	
	 public static void main(String[] args) throws IOException {

	        // Make a URL to the web page
	        URL url = new URL("http://bulbapedia.bulbagarden.net/wiki/Skitty_(Pok%C3%A9mon)");

	        // Get the input stream through URL Connection
	        URLConnection con = url.openConnection();
	        InputStream is =con.getInputStream();

	        // Once you have the Input Stream, it's just plain old Java IO stuff.

	        // For this case, since you are interested in getting plain-text web page
	        // I'll use a reader and output the text content to System.out.

	        // For binary content, it's better to directly read the bytes from stream and write
	        // to the target file.


	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        String line = null;
	        FileWriter writer = new FileWriter("text.txt");
	        // read each line and write to System.out
//	        while ((line = br.readLine()) != null) {
//	            ((Object) writer).println(line);
//	        }
	        writer.close();
	    }
	
}
