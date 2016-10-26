import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

public class getknown {
	
	public getknown(){
		maindl();
	}
	
	@SuppressWarnings("unused")
	public void maindl(){
		try{
			File known = new File("knownwikis.wot");
			Scanner scanner2 = new Scanner(known);
			boolean connected=true;
			try{
				URL testurl = new URL("https://en.wikipedia.org");
				HttpURLConnection tstur = (HttpURLConnection)testurl.openConnection();
				Object tstdta = tstur.getContent();
			}
			catch(UnknownHostException tsu){connected=false;}
			catch(IOException tsu){connected=false;}
		    while (scanner2.hasNextLine()) {
		    	String line = scanner2.nextLine();
		    	int tries = 1;
		    	String flname = line.substring(3,line.indexOf(":::",2));
		    	while(tries < 4){
					if(connected){
						URL url = new URL("https://en.wikipedia.org/wiki/" + flname);
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
					        while(page.indexOf("\"/static/")!=-1){
					        	fixlinks="";
					        	fixlinks+=page.substring(0,page.indexOf("\"/static/")+1);
					        	fixlinks+="https://en.wikipedia.org";
					        	fixlinks+=page.substring(page.indexOf("\"/static/")+1);
					        	page=fixlinks;
					        }
					        while(page.indexOf("\"//upload")!=-1){
					        	fixlinks="";
					        	fixlinks+=page.substring(0,page.indexOf("\"//upload")+1);
					        	fixlinks+="https:";
					        	fixlinks+=page.substring(page.indexOf("\"//upload")+1);
					        	page=fixlinks;
					        }
					        if(page.indexOf("Wikipedia does not have an article with this exact name.")==-1&&page.indexOf("Redirected from")==-1){
					        	PrintWriter writer = new PrintWriter(flname + ".html");
					        	writer.println(page);
					        	writer.close();
					        	System.out.println(url);
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
		    }
		    scanner2.close();
		}
		catch(MalformedURLException t){}
		catch(FileNotFoundException t){}
		catch(IOException t){}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] boe){
		getknown unknown = new getknown();
	}
	
}
