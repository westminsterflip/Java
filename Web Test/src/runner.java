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
		System.out.println("a) Download more articles (Just downloads a bunch of stuff, don't use)");
		System.out.println("b) Check for updates on known articles/Download from list (Will not work without having run e)");
		System.out.println("c) Search the known library");
		System.out.println("d) View list of known articles (This list is not the same as a list of downloaded articles)");
		System.out.println("e) Make article list w/o downloading");
		System.out.println("f) Delete duplicate articles");
		char choice = Character.toLowerCase(oi.readLine().charAt(0));
		switch(choice){
			case 'a': wikidl canidownloadtheinternetsplease = new wikidl();break;
			case 'b': getknown unknown = new getknown();break;
			case 'c': System.out.println("This is still broke");break;
			case 'd': System.out.println("This is still broke");break;
			case 'e': wikilist lickywist = new wikilist();break;
			case 'f': clean dirty = new clean();break;
			default : System.out.println("Come back when you're ready to actually do something");
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		runner poe = new runner();
	}

}
