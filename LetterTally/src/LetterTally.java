import java.io.*;
public class LetterTally {
	
	private int [] tally = new int[26];
	private String file;
	
	public LetterTally(){
		file = "inputfile.txt";
	}
	
	public String getFile(){
		return file;
	}
	
	public void setTally(int pos){
		tally[pos]++;
	}
	
	public int getTally(int pos){
		return tally[pos];
	}
	
	public String read() throws IOException{
		String output = "";
		FileReader line = new FileReader(getFile());
		BufferedReader input = new BufferedReader(line);
		String in = input.readLine();
		while(in != null){
			output += in+ '\r';
			in = input.readLine();
		}
		input.close();
		return output;
	}
	
	public void tallyStuff() throws IOException{
		int x=0;
		String input = read();
		for(x=0;x<input.length();x++){
			char c = input.charAt(x);
			if(Character.isLetter(c) == true){
				c = Character.toUpperCase(c);
				int cs = c - 65;
				setTally(cs);
			}
		}
	}
	
	public String toString(){
		int x=0;
		String output = "", num = "";
		for(x=0;x<26;x++){
			output += (char)(x+65) + ":      ";
		}
		output += '\r';
		for(x=0;x<26;x++){
			num = getTally(x) + "";
			output += num;
			for(int y = num.length();y<8;y++){
				output += " ";
			}
		}
		return output;
		
	}
	
	public String runThis() throws IOException{
		tallyStuff();
		return toString();
	}
	
	public static void main(String[] args) throws IOException{
		LetterTally t1 = new LetterTally();
		System.out.println(t1.runThis());
	}
	

}
