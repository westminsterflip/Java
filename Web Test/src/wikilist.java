import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class wikilist {
	public String j;
	public static ArrayList<String> list = new ArrayList<String>();
	
	public wikilist(boolean up) throws InterruptedException, IOException{
		System.out.println("class start");
		lister(up);
	}
	
	@SuppressWarnings("unused")
	public void lister(boolean update) throws InterruptedException, IOException{
		String nextpage = "";
		boolean go = true;
		boolean firstpage = true;
		String artname="";
		try{boolean con = true;
			String initial = "";
			File known = new File("knownwikis.wot");
			try {Scanner scanner = new Scanner(known);
				int linen = 0;
				while (scanner.hasNextLine()) {
					linen++;
					list.add(scanner.nextLine());
			    }
				if(linen<2||update)
					initial = "%21%21";
				else
					initial = list.get(list.size()-1);
			    scanner.close();
			} catch(FileNotFoundException e) { 
				e.printStackTrace();
			}
			while(go){
				con=false;
				while(!con){
					try{
						URL testurl = new URL("https://en.wikipedia.org");
						HttpURLConnection tstur = (HttpURLConnection)testurl.openConnection();
						Object tstdta = tstur.getContent();
						con=true;
					}
					catch(UnknownHostException tsu){}
					catch(IOException tsu){}
					if(!con){
						System.out.println("NOCONNECTION");
						Thread.sleep(10000);
					}
				}
				boolean found=false;
				if(con){
					URL url = null;
					URL url1 = null;
					url = new URL("https://en.wikipedia.org/wiki/Special:AllPages?from=" + initial + "&to=&namespace=0&hideredirects=1");
					url1 = new URL("https://en.wikipedia.org/wiki/Special:AllPages?from=" + list.get(list.indexOf(initial)-1) + "&to=&namespace=0&hideredirects=1");
					System.out.println("Now checking: " + url);
			        String james = null;
			        URLConnection conic = url.openConnection();
			        boolean worked = false;
			        InputStream ist = null;
			        int tries = 0;
			        while(!worked&&tries<5){
			        	try{
				        	ist =conic.getInputStream();
				        	worked=true;
				        }
				        catch(IOException t){
				        	System.out.println("An error ocurred, trying again");
				        	Thread.sleep(1000);
				        	tries++;
				        }
			        }
			        if(!worked&&tries>=5){
			        	while(!worked){
			        		try{
			        			conic = url1.openConnection();
					        	ist =conic.getInputStream();
					        	worked=true;
					        }
					        catch(IOException t){
					        	System.out.println("An error ocurred, will try again");
					        	Thread.sleep(1000);
					        }
			        	}
		        	}
			        BufferedReader brq = new BufferedReader(new InputStreamReader(ist));
			        int u = 0;
			        while(u < 95){
			        	brq.readLine();
			        	u++;
			        }
			        go = false;
			        while((james = brq.readLine())!=null&&u<444){
			        	if(james.indexOf("Next page")!=-1){
			        		go = true;
			        	}
			        	if(james.indexOf("<li><a href=\"/wiki/")!=-1){
			        		int tmp3 = james.indexOf("<li><a href=\"/wiki/");
			        		artname = james.substring(tmp3+19);
			        		artname = artname.substring(0,artname.indexOf("\""));
			        		boolean isthere = false;
							if(list.indexOf(artname)!=-1)
								isthere = true;
							if(!isthere){
								System.out.println(artname);
								Files.write(Paths.get("knownwikis.wot"), (artname + '\n').getBytes(), StandardOpenOption.APPEND);
								list.add(artname);
							}
			        	}
			        	u++;
			        }
			        initial = artname;
				}else{
					System.out.println("No conneection, will try again in 10 seconds");
					Thread.sleep(10000);
				}
			}
		}
		catch(MalformedURLException t){System.out.println("BROKEN1");}
		catch(FileNotFoundException t){System.out.println("BROKEN2");}
		catch(IOException t){t.printStackTrace();}
	}
			
	@SuppressWarnings("unused")
	public static void main(String[] james) throws InterruptedException, IOException{
		wikilist wl = new wikilist(Boolean.parseBoolean(james[0]));
	}
	
}
