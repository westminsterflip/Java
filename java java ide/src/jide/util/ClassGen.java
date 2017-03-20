package jide.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClassGen {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		File s = new File("D:\\Libraries\\Downloads\\jdk-8u121-docs-all\\docs\\api\\allclasses-noframe.html");
		File a = new File("classlist.list");
		try {
			a.createNewFile();
			@SuppressWarnings("unused")
			PrintWriter p = new PrintWriter(a);
			Scanner m = new Scanner(s);
			while(m.hasNextLine()){
				String w = m.nextLine();
				if(w.startsWith("<li>")){
					w=w.substring(w.indexOf("\">")+2,w.indexOf("</a>"));
					if(w.indexOf("interfaceName")!=-1)
						w=w.substring(w.indexOf("\">")+2,w.indexOf("</span>"));
					System.out.println(w);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
