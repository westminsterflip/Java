import java.io.*;
public class stats{
	private student[] s1 = new student[30];
	private int[] scr = new int[30];
	private String file;
	private String codes = "",in="";
	int num=0,high=0,low=100,sum=0,count = 0,cnt1=0;
	
	
	public stats() throws IOException{
		run();
	}
	
	public void setStudent(int pos, student yellow){
		s1[pos] = yellow;
	}
	
	public void setFile(String fl){
		file = fl;
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
		System.out.print("Filename: ");
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader i = new BufferedReader(r);
		setFile(i.readLine());
		try{
			file();
		}
		catch(FileNotFoundException ei){
			try{
				file = file+".txt";
				file();
			}
			catch(FileNotFoundException re){
				System.out.println("Invalid Filename.");
				inputter();
			}
		}
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
			//System.out.println(last);
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
					scr[(int)count] = score;
					count++;
					sum+=score;
				}
				catch(StringIndexOutOfBoundsException e1){}
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
				try{
					//System.out.println("checkpoint 2");
					failure.setID(nice.substring(0,inds1).trim());
					failure.setGrade(nice.substring(inds1,inds2).trim());
					failure.setMajor(nice.substring(inds2).trim());
					s1[num] = failure;
				}
				catch(StringIndexOutOfBoundsException i){}
				try{
					int score = failure.getGrade();
					if(score>high)
						high=score;
					if(score<low){
						low = score;
					}
					scr[(int)count] = score;
					count++;
					sum+=score;
				}
				catch(StringIndexOutOfBoundsException e2){}
				//System.out.println(getStudent(num).getStuff());
			}
		}
	}

	public void sort(){
		int tmp=0,temp = 0;
		student tp = new student(),tep = new student();
		for(int x=0;x<count-1;x++){
			
			System.out.println(scr[x]+"and");
			System.out.println(x);
			tmp = scr[x];	
			temp = tmp;
			int y;
			for(y=x+1;y<count;y++){
				System.out.print(scr[y] + ",  , ");
				if(scr[y]<tmp){
					//System.out.println("changed");
					tmp=scr[y];
				}
			}
			scr[x]=tmp;
			scr[y]=temp;
		}
		
	}
	
	public String toString(int ind){
		return getStudent(ind).getStuff();
	}
	
	public void run() throws IOException{
		inputter();
		makeClass();
		sort();
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
		for(int x = (mean+"").length();x<6;x++){
			System.out.print(" ");
		}
		System.out.println((scr[count/2]+scr[count-1-count/2])/2.0);
		for(int x=0;x<count;x++){
			System.out.println(scr[x]+ ", ");
		}
	}
	
	public static void main(String[] schience) throws IOException{
	    @SuppressWarnings("unused")
		stats s1 = new stats();
	}
	
}
