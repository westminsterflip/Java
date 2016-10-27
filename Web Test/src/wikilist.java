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
	public ArrayList<String> list = new ArrayList<String>();
	
	public wikilist() throws InterruptedException, IOException{
		System.out.println("class start");
		lister();
	}
	
	@SuppressWarnings("unused")
	public void lister() throws InterruptedException, IOException{
		InputStreamReader j = new InputStreamReader(System.in);
		BufferedReader oi = new BufferedReader(j);
		System.out.println("Compile (a) or Update (b)?");
		char comup = oi.readLine().charAt(0);
		boolean update = false;
		if(comup=='b'){
			update = true;
		}
		String nextpage = "";
		boolean go = true;
		boolean firstpage = true;
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
				try{
					URL testurl = new URL("https://en.wikipedia.org");
					HttpURLConnection tstur = (HttpURLConnection)testurl.openConnection();
					Object tstdta = tstur.getContent();
				}
				catch(UnknownHostException tsu){con=false;}
				catch(IOException tsu){con=false;}
				while(!con){
					System.out.println("NOCONNECTION");
					Thread.sleep(10000);
				}
				boolean found=false;
				if(con){
					URL url = null;
					url = new URL("https://en.wikipedia.org/wiki/Special:AllPages?from=" + initial + "&to=&namespace=0&hideredirects=1");
					System.out.println("Now checking: " + url);
			        String james = null;
			        URLConnection conic = url.openConnection();
			        InputStream ist =conic.getInputStream();
			        BufferedReader brq = new BufferedReader(new InputStreamReader(ist));
			        while((james = brq.readLine())!=null&&james.indexOf("</ul>")==-1){
			        	if(james.indexOf("<li><a href=\"/wiki/")!=-1){
			        		int tmp3 = james.indexOf("<li><a href=\"/wiki/");
			        		String artname = james.substring(tmp3+19);
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
			        }
			        initial = list.get(list.size()-1);
				}else{
					System.out.println("No conneection, will try again in 10 seconds");
					Thread.sleep(10000);
				}
				//go=false;
			}
		}
		catch(MalformedURLException t){System.out.println("BROKEN1");}
		catch(FileNotFoundException t){System.out.println("BROKEN2");}
		catch(IOException t){System.out.println("BROKEN3");}
	}
			
	@SuppressWarnings("unused")
	public static void main(String[] james) throws InterruptedException, IOException{
		wikilist wl = new wikilist();
	}
	
}
