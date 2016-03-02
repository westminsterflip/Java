import java.io.*;
public class LetterTally {

	public static void main(String[] args) throws IOException{
		FileReader readFile = new FileReader("inputfile.txt");
		BufferedReader input = new BufferedReader(readFile);
		
		String in = input.readLine();
		
		while(in != null){
			System.out.println(in);
			in=input.readLine();
		}
	input.close();
	}
	

}
