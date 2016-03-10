import java.io.*;
public class stats{
	private student[] s1 = new student[30];
	private String file;
	private String codes;
	
	
	public stats(){
	}
	
	public void setStudent(int pos, student yellow){
		s1[pos] = yellow;
	}
	
	public void setFile(String fl){
		file = fl+".txt";
	}
	
	public student getStudent(int pos){
		return s1[pos];
	}
	
	public String getFile(){
		return file;
	}
	
	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public void inputter() throws IOException{
		FileReader line = new FileReader(getFile());
		BufferedReader input = new BufferedReader(line);
		String in = input.readLine();
		while(in != null){
			setCodes(getCodes() + in+ '\r');
			in = input.readLine();
		}
		input.close();
	}
	
	public void makeClass(){
		String in = getCodes();
		
	}
	
}
