import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class wikilist {
	public String j;
	
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
				initial = scanner.nextLine();
		    }
			if(linen<2||update)
				initial = "%21%21";
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
					while(!found){
						URL url = null;
						//System.out.println(nextpage);
						System.out.println("looped " + firstpage);
						if(firstpage){
							url = new URL("https://en.wikipedia.org/wiki/Special:AllPages?from=" + initial + "&to=&namespace=0&hideredirects=1");
						}else{
							url = new URL(nextpage);
						}
						System.out.println("Now checking: " + url);
				        URLConnection coni = url.openConnection();
				        try{
				        	InputStream is =coni.getInputStream();
				        	BufferedReader br = new BufferedReader(new InputStreamReader(is));
					        String page = "";
					        String james = null;
					        int lines = 1;
					        while ((james = br.readLine()) != null&&lines<90){
					        	if(lines==89){
					        		int tmp = james.indexOf("<a href=\"");
					        		if(firstpage&&update){
					        			nextpage = "https://en.wikipedia.org" + james.substring(tmp + 9, james.indexOf("\"",tmp+9));
					        		}else{
					        			int tmp1 = james.indexOf("Previous page");
					        			int tmp2 = james.indexOf("<a href=\"",tmp1);
					        			nextpage = "https://en.wikipedia.org" + james.substring(tmp2 + 9, james.indexOf("\"",tmp2+9));
					        		}
					        		while(nextpage.indexOf("amp;")!=-1){
					        			nextpage = nextpage.substring(0,nextpage.indexOf("amp;"))+nextpage.substring(nextpage.indexOf("amp;")+4);
					        		}
					        		System.out.println("Next page: " + nextpage);
					        	}
					        	lines++;
					        }
					        URLConnection conic = url.openConnection();
					        InputStream ist =conic.getInputStream();
					        BufferedReader brq = new BufferedReader(new InputStreamReader(ist));
					        //lines=0;
					        while((james = brq.readLine())!=null){
					        	//System.out.println(lines);
					        	//System.out.println(james);
					        	if(james.indexOf("<li><a href=\"/wiki/")!=-1){
					        		int tmp3 = james.indexOf("<li><a href=\"/wiki/");
					        		String artname = james.substring(tmp3+19);
					        		artname = artname.substring(0,artname.indexOf("\""));
					        		boolean isthere = false;
									try {
									    Scanner scanner = new Scanner(known);
									    while (scanner.hasNextLine()) {
									        String line = scanner.nextLine();
									        if(artname.equals(line)) {
									        	isthere=true;
									        }
									    }
									    scanner.close();
									} catch(FileNotFoundException e) { 
										e.printStackTrace();
									}
									if(!isthere){
										System.out.println(artname);
										Files.write(Paths.get("knownwikis.wot"), (artname + '\n').getBytes(), StandardOpenOption.APPEND);
									}
					        	}
					        }
				        }
				        catch(FileNotFoundException y){
				        	System.out.print("failed");
				        }
				        catch(IOException y){
				        	System.out.print("failed");
				        }
				        firstpage=false;
					}
				}else{
					System.out.println("No conneection, will try again in 10 seconds");
					Thread.sleep(10000);
				}
				go=false;
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
