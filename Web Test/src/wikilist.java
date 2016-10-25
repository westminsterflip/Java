import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class wikilist {
	public static ArrayList<Integer> let = new ArrayList<Integer>(217);
	public String j;
	
	public wikilist() throws InterruptedException{
		lister();
	}
	
	@SuppressWarnings("unused")
	public void lister() throws InterruptedException{
		try{PrintWriter pw = new PrintWriter("knownwikis.wot");
			pw.close();
			for(int y = 0;y<209;y++){
				let.add(0);
			}
			for(int y = 0;y<1;y++){
				let.set(y, (int)'A');
			}
			let.set(1, let.get(1)-1);
			boolean go=true;
			int til = 0;
			while(go){
				try{
					boolean connected = true;
					try{
						URL testurl = new URL("https://en.wikipedia.org");
						HttpURLConnection tstur = (HttpURLConnection)testurl.openConnection();
						Object tstdta = tstur.getContent();
					}
					catch(UnknownHostException tsu){connected=false;}
					catch(IOException tsu){connected=false;}
					if(connected){
						til = 0;
						for(int yu = 0;yu<209;yu++){
							if(!let.get(yu).equals(0))
								til = yu;
						}
						if(let.get(til).equals(0)){
							let.set(til, 40);
						}else
							let.set(til, let.get(til)+1);
						for(int yu = 208;yu>=0;yu--){
							if(let.get(yu).equals(91)&&yu==0){
								for(int i = 0;i<=til+1;i++){
									let.set(i, 40);
								}
							}else if(let.get(yu).equals(42)){
								let.set(yu, 65);
							}else if(let.get(yu).equals(91)){
								let.set(yu, 95);
							}else if(let.get(yu).equals(96)){
								let.set(yu, 97);
							}else if(let.get(yu).equals(123)){
								let.set(yu, 40);
								let.set(yu-1, let.get(yu-1)+1);
							}
						}
						String nme="";
						for(Integer n:let){
							if(!n.equals(0)){
								nme+=(char)(int)n;
							}
						}
						if(nme.charAt(nme.length()-1)=='_'){
							System.out.println("TRIGGERED");
							nme=nme.substring(0, nme.length()-2);
						}
						int tries = 1;
						boolean con = false;
						while(tries < 2&&!con){
							try{
								URL testurl = new URL("https://en.wikipedia.org/wiki/"+nme);
								HttpURLConnection tstur = (HttpURLConnection)testurl.openConnection();
								Object tstdta = tstur.getContent();
								con=true;
							}
							catch(UnknownHostException tsu){con=false;}
							catch(IOException tsu){con=false;}
							tries++;
						}
						if(con){
							String ot = ":::" + nme + ":::" + '\n';
							File je = new File("knownwikis.wot");
							boolean isthere = false;
							try {
							    Scanner scanner = new Scanner(je);
							    int lineNum = 0;
							    while (scanner.hasNextLine()) {
							        String line = scanner.nextLine();
							        lineNum++;
							        if(ot.equals(line)) { 
							        	isthere=true;
							        }
							    }
							    scanner.close();
							} catch(FileNotFoundException e) { 
								e.printStackTrace();
							}
							if(!isthere){
								Files.write(Paths.get("knownwikis.wot"), ot.getBytes(), StandardOpenOption.APPEND);
								System.out.println("Recorded: " + nme + ":end:");
							}
						}
						go=!stuff();
					}else{
					try{
						System.out.println("No connection, will try again in 10 seconds.");
						Thread.sleep(10000);
					}
					catch(InterruptedException dontinterruptme){
						dontinterruptme.printStackTrace();
					}
					}
				
				}catch(Exception t){
					t.printStackTrace();
				}
			}
			System.out.println("out has been closed");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String letstring(){
		String out = "";
		try{
			for(int u=0;u<209;u++){
				out+=(char)(int)let.get(u);
			}
		}
		catch(NullPointerException y){
			y.printStackTrace();
		}
		return out;
	}
	
	public boolean stuff(){
		boolean g = true;
		if(!letstring().equals("Suzukake no Ki no Michi de \"Kimi no Hohoemi o Yume ni Miru\" to Itte Shimattara Bokutachi no Kankei wa Dō Kawatte Shimau no ka, Bokunari ni Nannichi ka Kangaeta Ue de no Yaya Kihazukashii Ketsuron no Yō na Mono")){
			g=false;
		}
		return g;
	}
			
	@SuppressWarnings("unused")
	public static void main(String[] james) throws InterruptedException{
		wikilist wl = new wikilist();
	}
	
}
