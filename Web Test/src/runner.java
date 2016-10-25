import java.io.*;

public class runner {
	
	public runner() throws IOException{
		what();
	}
	
	public void what()throws IOException{
		InputStreamReader j = new InputStreamReader(System.in);
		BufferedReader oi = new BufferedReader(j);
		System.out.println("What would you like to do?");
		System.out.println("a) Download more articles");
		System.out.println("b) Check for updates on known articles");
		System.out.println("c) Search the library");
		System.out.println("d) View list of currently downloaded articles");
		char choice = Character.toLowerCase(oi.readLine().charAt(0));
		switch(choice){
			case 'a': wikidl w = new wikidl();break;
			case 'b': 
			case 'c': 
			case 'd': 
			default : System.out.println("Come back when you're ready to actually do something");
		}
	}

	public static void main(String[] args) throws IOException {
		runner poe = new runner();
	}

}
