import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class getknown {
	ArrayList<String> list = new ArrayList<String>();
	
	public getknown(String pth) throws IOException{
		updl(pth);
	}
	
	@SuppressWarnings("unused")
	public void updl(String path) throws IOException{
		try{
			if(path.length()==0)
				path="";
		}catch(NullPointerException t){
			path="";
		}
		InputStreamReader j = new InputStreamReader(System.in);
		BufferedReader oi = new BufferedReader(j);
		System.out.println("Add missing files (a) or Update (b)?");
		char comup = oi.readLine().charAt(0);
		boolean update = false;
		if(comup=='b'){
			update = true;
		}
		File li = new File("knownwikis.wot");
		System.out.println(li.exists());
		String john = null;
		try {
			Scanner lsscn = new Scanner(li);
			while((john = lsscn.nextLine())!=null){
				//System.out.println(john);
				list.add(john);
			}
			lsscn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(NoSuchElementException e){
			//System.out.println(john);
		}
		for(String urlpt:list){
			boolean con = false;
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
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			URL url = new URL("https://en.wikipedia.org/wiki/" + urlpt);
	        URLConnection conn = url.openConnection();
	        String flname = urlpt;
	        while(flname.indexOf('%')!=-1){
	        	int tmp = flname.indexOf('%');
	        	String cha = flname.substring(tmp+1,tmp+3);
	        	String t = (char)(int)Integer.valueOf(cha, 16) + "";
	        	if(t.equals("%"))
	        		t="~|~";
	        	flname = flname.substring(0, tmp) + t + flname.substring(tmp+3);
	        }
	        while(flname.indexOf("~|~")!=-1){
	        	int tmp = flname.indexOf("~|~");
	        	String cha = flname.substring(tmp+1,tmp+3);
	        	flname = flname.substring(0, tmp) + '%' + flname.substring(tmp+3);
	        }
	        boolean there = false;
	        try{
	        	InputStream is =conn.getInputStream();
	        	BufferedReader br = new BufferedReader(new InputStreamReader(is));
		        File pg = new File(path + flname + ".html");
	        	there = pg.exists();
	        	String page = "";
		        String james = null;
		        if(!there||update){
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
				        	fixlinks+=path;
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
			        if(page.indexOf("Wikipedia does not have an article with this exact name.")==-1){
			        	PrintWriter writer = new PrintWriter(pg);
			        	writer.println(page);
			        	writer.close();
			        	System.out.println(url);
			        }
		        }
	        }catch(IOException t){
	        	t.printStackTrace();
	        }
		}
	}

	public static void main(String[] arg) throws IOException{
		/*try {
			getknown m = new getknown(arg[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
