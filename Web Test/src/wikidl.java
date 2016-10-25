import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class wikidl {
	public static ArrayList<Integer> let = new ArrayList<Integer>(217);
	public String j;
	
	public wikidl() throws FileNotFoundException, IOException{
		trying();
	}
	
	@SuppressWarnings("unused")
	public void trying() throws IOException, FileNotFoundException{
		InputStreamReader o = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(o);
		System.out.print("Overwrite mode: ");
		boolean no = Boolean.parseBoolean(b.readLine());
		System.out.print("Start Letters: ");
		String dd = b.readLine();
		System.out.print("End Letters: ");
		String sd = b.readLine();
		String temp = dd;
		dd = Character.toUpperCase(temp.charAt(0))+temp.substring(1);
		temp = sd;
		sd = Character.toUpperCase(temp.charAt(0))+temp.substring(1);
		j=sd;
		for(int y = 0;y<sd.length();y++){
			let.add(0);
		}
		for(int y = 0;y<dd.length();y++){
			let.set(y, (int)dd.charAt(y));
		}
		let.set(dd.length()-1, let.get(dd.length()-1)-1);
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
					for(int yu = 0;yu<sd.length();yu++){
						if(!let.get(yu).equals(0))
							til = yu;
					}
					if(let.get(til).equals(0)){
						let.set(til, 40);
					}else
						let.set(til, let.get(til)+1);
					for(int yu = sd.length()-1;yu>=0;yu--){
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
					if(nme.charAt(nme.length()-1)=='_')
						nme=nme.substring(0, nme.length()-2);
					File g = new File(nme + ".html");
					String name = nme;
					int mod = 1;
					String name1;
					if(!no)
						while(g.exists()){
							name1 = name + mod;
							System.out.println("This exists: " + mod);
							mod++;
							g = new File(name1 + ".html");
							
						}
					int tries = 1;
					while(tries < 4){
						URL url = new URL("https://en.wikipedia.org/wiki/" + nme);
						System.out.println(url);
				        URLConnection con = url.openConnection();
				        try{
				        	InputStream is =con.getInputStream();
				        	BufferedReader br = new BufferedReader(new InputStreamReader(is));
					        String page = "";
					        String james = null;
					        
					        while ((james = br.readLine()) != null) {
					            page+=james;
					        }
					        String fixlinks = "";
					        int strt = 0;
					        int gotil=0;
					        while(page.indexOf("/wiki/",strt)!=-1){
					        	boolean wot = page.indexOf(".org/wiki/",page.indexOf("/wiki/",strt)-10)+4!=page.indexOf("/wiki/",strt);
					        	if(wot){
						        	gotil=page.indexOf("/wiki/",strt);
						        	fixlinks+=page.substring(0, gotil);
						        	fixlinks+="/Users/irpat/git/Web%20Test/";
						        	String neme = page.substring(gotil+6,page.indexOf("\"", gotil)) + ".html";
						        	fixlinks+=neme;
						        	fixlinks+=page.substring(page.indexOf("\"",gotil));
						        	page=fixlinks;
						        	fixlinks="";
					        	}else{
					        		strt = page.indexOf("/wiki/",strt)+6;
					        	}
					        }
					        fixlinks="";
					        while(page.indexOf("href=\"//")!=-1){
					        	fixlinks+=page.substring(0,page.indexOf("href=\"//")+6);
					        	fixlinks+="https:";
					        	fixlinks+=page.substring(page.indexOf("href=\"//")+6);
					        	page=fixlinks;
					        	fixlinks="";
					        }
					        if(page.indexOf("Wikipedia does not have an article with this exact name.")==-1&&page.indexOf("Redirected from")==-1){
					        	PrintWriter writer = new PrintWriter(name + ".html");
					        	writer.println(page);
					        	writer.close();
					        }
					        tries = 4;
				        }
				        catch(FileNotFoundException y){
				        	System.out.print("failed");
				        	for(int c = 0; c<tries;c++){
				        		System.out.print(".");
				        	}
				        	System.out.println(tries);
				        	tries ++;
				        }
				        catch(IOException y){
				        	System.out.print("failed");
				        	for(int c = 0; c<tries;c++){
				        		System.out.print(".");
				        	}
				        	System.out.println(tries);
				        	tries ++;
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
			}
			catch(IOException t){
				System.out.println("oi");
			}
		}
		System.out.println("Done");
	}
	
	public boolean stuff(){
		boolean g = true;
		for(int y = 0;y<j.length();y++){
			if((int)j.charAt(y)!=let.get(y))
				g=false;
		}
		return g;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] franny) throws IOException{
		wikidl w = new wikidl();
	}
	
}
