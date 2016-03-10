import java.io.*;
public class stats{
	private student[] s1 = new student[30];
	private String file;
	private String codes;
	int num=0;
	
	
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
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader i = new BufferedReader(r);
		setFile(i.readLine());
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
		String nice;
		int index=0;
		int strt;
		boolean last = false;
		while(last==false){
			System.out.println("Trying");
			try{
				student failure = new student();
				strt = index;
				index=in.indexOf("   ");
				nice=in.substring(strt,index);
				failure.setID(nice.substring(0,2));
				failure.setGrade(nice.substring(3,5));
				failure.setMajor(nice.substring(6));
				s1[num] = failure;
				num++;
			}
			catch(Exception e){
				last=true;
			}
		}
	}
	
	public String toString(int ind){
		String output="";
		String[] f = s1[ind].getStu();
		for(int x=0;x<4;x++){
			output += f[0] + " ";
		}
		return output;
	}
	
	public void run() throws IOException{
		inputter();
		makeClass();
		for(int x=0;x<s1.length;x++){
			System.out.println(toString(x));
		}
	}
	
	public static void main(String[] schience) throws IOException{
		stats s1 = new stats();
		s1.run();
	}
	
}
