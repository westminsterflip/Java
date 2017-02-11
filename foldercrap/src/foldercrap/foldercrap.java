package foldercrap;

import java.io.File;
import java.io.IOException;

public class foldercrap {
	public static void main(String[] args) throws IOException{
		String james = "tuft";
		for(int u = 0;u<1000001;u++){
			/*for(int y = 0;y<=u;y++){
				File n = new File(james+"hax" + y + ".jpeg");
				n.createNewFile();
			}*/
			File m = new File(james);
			m.mkdir();
			james = james+"\\tuft"+u;
			//System.out.println(james);
		}
	}
}
