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
		System.out.println("a) Download more articles");
		System.out.println("b) Check for updates on known articles (Will not work without having run e)");
		System.out.println("c) Search the library");
		System.out.println("d) View list of currently downloaded articles");
		System.out.println("e) Make article list w/o downloading");
		char choice = Character.toLowerCase(oi.readLine().charAt(0));
		switch(choice){
			case 'a': wikidl w = new wikidl();break;
			case 'b': 
			case 'c': 
			case 'd': 
			case 'e': wikilist wl = new wikilist();break;
			default : System.out.println("Come back when you're ready to actually do something");
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		runner poe = new runner();
	}

}
