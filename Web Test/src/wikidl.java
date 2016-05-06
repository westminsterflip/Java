import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class wikidl {
	public static ArrayList<Integer> let = new ArrayList<Integer>(217);
	
	public wikidl() throws FileNotFoundException, IOException{
		trying();
	}
	
	public void trying() throws IOException, FileNotFoundException{
		InputStreamReader o = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(o);
		System.out.print("Overwrite mode: ");
		boolean no = Boolean.parseBoolean(b.readLine());
		System.out.print("Start Letters: ");
		int dd = Integer.parseInt(b.readLine());
		System.out.print("End Letters: ");
		int sd = Integer.parseInt(b.readLine());
		for(int y = 0;y<sd;y++){
			let.add(0);
		}
		boolean go=true;
		int til = 0;
		for(int as = 0; as < dd;as++){
			let.set(as, 40);
		}
		while(go){
			til = 0;
			for(int yu = 0;yu<sd;yu++){
				if(!let.get(yu).equals(0))
					til = yu;
			}
			if(let.get(til).equals(0)){
				let.set(til, 40);
			}else
				let.set(til, let.get(til)+1);
			for(int yu = sd-1;yu>=0;yu--){
				if(let.get(yu).equals(91)&&yu==0){
					for(int i = 0;i<=til;i++){
						let.set(i, 40);
					}
					if(til!=sd-1)
						let.set(til+1, 40);
					else
						go=false;
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
			File g = new File(nme + ".html");
			String name = nme;
			int mod = 1;
			if(!no)
				while(g.exists()){
					name += mod;
					System.out.println("This exists: " + mod);
					mod++;
					g = new File(name + ".html");
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
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] franny) throws IOException{
		wikidl w = new wikidl();
	}
	
}
