import java.io.*;

public class runner {
	
	public runner() throws IOException{
		try{
			what();
		}
		catch(InterruptedException ty){
			ty.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public void what()throws IOException, InterruptedException{
		InputStreamReader j = new InputStreamReader(System.in);
		BufferedReader oi = new BufferedReader(j);
		System.out.println("What would you like to do?");
		//System.out.println("a) Download more articles (Just downloads a bunch of stuff, don't use)");
		System.out.println("a) Download from list (Will not work without having run d)");
		System.out.println("b) Search the known library");
		System.out.println("c) View list of known articles (List of URL parts, not particularly readable)");
		System.out.println("d) Compile/Update article list");
		System.out.println("e) Delete duplicate articles (There should be no need to run this)");
		char choice = Character.toLowerCase(oi.readLine().charAt(0));
		switch(choice){
			case 'a': getknown unknown = new getknown();break;
			case 'b': System.out.println("This is still broke");break;
			case 'c': System.out.println("This is still broke");break;
			case 'd': wikilist lickywist = new wikilist();break;
			case 'e': clean dirty = new clean();break;
			default : System.out.println("Come back when you're ready to actually do something");
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		runner poe = new runner();
	}

}
