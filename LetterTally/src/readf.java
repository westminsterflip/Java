import java.io.*;

public class readf {
	
	private int [] tally = new int[26];
	
	public readf(){
	}
	
	public void setTally(int pos){
		tally[pos]++;
	}
	
	public int getTally(int pos){
		return tally[pos];
	}
	
	public String read() throws IOException{
		String output = "";
		FileReader line = new FileReader("inputfile.txt");
		@SuppressWarnings("resource")
		BufferedReader input = new BufferedReader(line);
		while(input != null){
			output += input.readLine() + '\r';
		}
		return output;
	}
	
	public void tallyStuff() throws IOException{
		int x=0;
		String input = read();
		for(x=0;x<input.length();x++){
			char c = input.charAt(x);
			int cs = c - 65;
			setTally(cs);
		}
	}
	
	
	
}
