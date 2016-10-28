import java.io.*;

public class runner {
	String filepath;
	
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
		System.out.println("c) Generate list of downloaded articles");
		System.out.println("d) Compile/Update article list");
		System.out.println("e) Delete duplicate articles (There should be no need to run this, but wont work withough having run a)");
		System.out.println("f) Specify filepath (If you do not specify a filepath, the default will be used)");
		char choice = Character.toLowerCase(oi.readLine().charAt(0));
		switch(choice){
			case 'a': getknown unknown = new getknown(filepath);break;
			case 'b': System.out.println("This is still broke");break;
			case 'c': System.out.println("This is still broke");break;
			case 'd': wikilist lickywist = new wikilist();break;
			case 'e': clean dirty = new clean(filepath);break;
			case 'f': InputStreamReader j1 = new InputStreamReader(System.in);
			BufferedReader oi1 = new BufferedReader(j1);
			System.out.print("Filepath: ");
			filepath = oi1.readLine();break;
			default : System.out.println("Come back when you're ready to actually do something");
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		runner poe = new runner();
	}

}
