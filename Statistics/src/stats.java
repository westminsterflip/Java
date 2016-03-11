import java.io.*;
public class stats{
	private student[] s1 = new student[30];
	private String file;
	private String codes = "",in="";
	int num=0,high=0,low=100,sum=0;
	double count = 0;
	
	
	public stats() throws IOException{
		run();
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
		file();
	}
	
	public void file() throws IOException{
		FileReader line = new FileReader(getFile());
		BufferedReader input = new BufferedReader(line);
		for(in = input.readLine();in != null;in = input.readLine()){
			setCodes(getCodes() + in+ '\r');
			//System.out.println(in + " and " + getCodes());
		}
		input.close();
	}
	
	public void makeClass(){
		String in = getCodes();
		String nice="";
		int index=0;
		int strt=0;
		int inds1=0,inds2=0;
		boolean last = false;
		while(last==false){
			try{
				student failure = new student();
				index=in.indexOf("   ",strt);
				//System.out.println("trying stuff");
				nice=in.substring(strt,index);
				//System.out.println(in);
				inds1 = nice.indexOf(' ');
				inds2 = nice.indexOf(' ', inds1 + 1);
				//System.out.println(inds1 + " and " + inds2);
				//System.out.println(strt + " and " + index);
				//System.out.println(nice);
				//System.out.println(nice.substring(0,inds1));
			//	System.out.println(nice.substring(3,5));
				//System.out.println(nice.substring(6));
				failure.setID(nice.substring(0,inds1).trim());
				failure.setGrade(nice.substring(inds1,inds2).trim());
				try{
					int score = failure.getGrade();
					if(score>high)
						high=score;
					if(score<low){
						low = score;
					}
					count++;
					sum+=score;
				}
				catch(StringIndexOutOfBoundsException e){}
				failure.setMajor(nice.substring(inds2).trim());
				s1[num] = failure;
				//System.out.println(getStudent(num).getStuff());
				num++;
				strt = index+3;
			}
			catch(StringIndexOutOfBoundsException e){
				//System.out.println("stopped trying");
				last=true;
				nice=in.substring(in.lastIndexOf("   ")).trim();
				//System.out.println(in.lastIndexOf("   "));
				inds1 = nice.indexOf(' ');
				inds2 = nice.indexOf(' ', inds1 + 1);
				//System.out.println(inds1 + " and " + inds2);
				student failure = new student();
				//System.out.println(strt + " and " + index);
				//System.out.println(nice);
				//System.out.println(nice.substring(0,inds1).trim());
				//System.out.println(nice.substring(inds1,inds2).trim());
				//System.out.println(nice.substring(inds2).trim());
				failure.setID(nice.substring(0,inds1).trim());
				failure.setGrade(nice.substring(inds1,inds2).trim());
				failure.setMajor(nice.substring(inds2).trim());
				s1[num] = failure;
				//System.out.println(getStudent(num).getStuff());
			}
		}
	}
	
	public String toString(int ind){
		return getStudent(ind).getStuff();
	}
	
	public void run() throws IOException{
		inputter();
		makeClass();
		System.out.println("ID:  Grade:  Letter Grade:  Major:");
		for(int x=0;s1[x]!=null;x++){
			//System.out.println(x);
			System.out.println(toString(x));
		}
		System.out.println("Max:  Min:  Mean:  Median:");
		System.out.print(high+"%");
		for(int x = (high+"").length();x<5;x++){
			System.out.print(" ");
		}
		System.out.print(low+"%");
		double mean = Math.round((double)sum/count*10)/10.0;
		for(int x = (low+"").length();x<5;x++){
			System.out.print(" ");
		}
		System.out.print(mean+"%");
	}
	
	public static void main(String[] schience) throws IOException{
	    @SuppressWarnings("unused")
		stats s1 = new stats();
	}
	
}
