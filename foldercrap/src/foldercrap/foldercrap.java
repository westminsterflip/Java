package foldercrap;

import java.io.File;
import java.io.IOException;

public class foldercrap {
	public static void main(String[] args) throws IOException{
		for(int i =0;i<2000000;i++){
			new File(i+".bottlesofbeeronthewall").createNewFile();
		}
	}
}
