import java.io.*;
import java.util.*;

public class run {
	ArrayList<elstudent> el = new ArrayList<elstudent>();
	ArrayList<hsstu> hs = new ArrayList<hsstu>();
	String fname,stu;
	String in="";

	public run(){
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public void getIn() throws IOException{
		FileReader f = new FileReader(fname);
		BufferedReader b = new BufferedReader(f);
		String h = b.readLine();
		while(h!=null){
			in+=h;
			in+="$break$";
			h = b.readLine();
		}
		b.close();
	}
	
	public void setStu(int b, int e){
		stu = in.substring(b,e).trim();
	}
	
	public void make(){
		int u = 0, ts = in.indexOf("$break$");
		String fn="",ls="",gr="";
		int h;
		System.out.println(in);
		while(u!=-1&&ts!=-1){
			System.out.println(u +" & " + ts);
			setStu(u,ts);
			int s1=stu.indexOf(" ");
			int s2=stu.indexOf(" ",s1+1);
			int s3=stu.indexOf(" ",s2+1);
			u=ts+7;
			ts = in.indexOf("$break$",u);
			fn = stu.substring(0,s1).trim();
			ls = stu.substring(s1,s2).trim();
			System.out.println(fn);
			System.out.println(ls);
			if(s3==-1){
				gr = stu.substring(stu.lastIndexOf(" ")).trim();
				h = Integer.parseInt(gr);
				elstudent e = new elstudent();
				e.setFName(fn);
				e.setLName(ls);
				e.setGLevel(h);
				int ind = 0;
				//try{
				if(el.isEmpty()){
					el.add(e);	
				}else{
					ind=0;
					while(ind<el.size()&&el.get(ind).compareTo(e)<0){
						System.out.println(ind);
						ind++;
					}
					el.add(ind,e);
				}
					
				//}
				//catch(IndexOutOfBoundsException i){
				//}
			}else{
				int s4 = stu.indexOf(" ",s3+1);
				int s5 = stu.indexOf(" ",s4+1);
				gr = stu.substring(s2,s3).trim();
				h = Integer.parseInt(gr);
				System.out.println(s1 +", " + s2 +", " + s3 + ", " + s4 +", " +s5);
				double qp = Double.parseDouble(stu.substring(s3,s4).trim());
				if(h==12){
					senior h1 = new senior();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(qp);
					h1.setFine(Double.parseDouble(stu.substring(s4,s5).trim()));
					h1.setPodone(Boolean.parseBoolean(stu.substring(stu.lastIndexOf(" ")).trim()));
					System.out.println(stu.substring(s3,s4) + " " + stu.substring(s4,s5));
					int ind = 0;
					if(hs.isEmpty()){
						hs.add(h1);	
					}else{
						ind=0;
						while(ind<hs.size()&&hs.get(ind).compareTo(h1)<0){
							System.out.println(ind);
							ind++;
						}
						hs.add(ind,h1);
					}
				}else if(h==11){
					junior h1 = new junior();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(qp);
					h1.setKeys(Integer.parseInt(stu.substring(stu.lastIndexOf(" ")).trim()));
					int ind = 0;
					if(hs.isEmpty()){
						hs.add(h1);	
					}else{
						ind=0;
						while(ind<hs.size()&&hs.get(ind).compareTo(h1)<0){
							System.out.println(ind);
							ind++;
						}
						hs.add(ind,h1);
					}
				}else if(h==10){
					sophmore h1 = new sophmore();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(qp);
					h1.setMath(Double.parseDouble(stu.substring(stu.lastIndexOf(" ")).trim()));
					int ind = 0;
					if(hs.isEmpty()){
						hs.add(h1);	
					}else{
						ind=0;
						while(ind<hs.size()&&hs.get(ind).compareTo(h1)<0){
							System.out.println(ind);
							ind++;
						}
						hs.add(ind,h1);
					}
				}else if(h==9){
					freshman h1 = new freshman();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(qp);
					h1.setDisRef(Integer.parseInt(stu.substring(stu.lastIndexOf(" ")).trim()));
					int ind = 0;
					if(hs.isEmpty()){
						hs.add(h1);	
					}else{
						ind=0;
						while(ind<hs.size()&&hs.get(ind).compareTo(h1)<0){
							System.out.println(ind);
							ind++;
						}
						hs.add(ind,h1);
					}
				}
			}
		}
	}
	
	public void walk() throws IOException{
		InputStreamReader i = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(i);
		System.out.print("Filename: ");
		setFname(b.readLine());
		try{
			getIn();
		}
		catch(FileNotFoundException e){
			fname += ".txt";
			try{
				getIn();
				}
			catch(FileNotFoundException f){
				System.out.println("Invalid File Name");
				walk();
			}
		}
		make();
		System.out.println(toString());
	}
	
	public String toString(){
		String out="";
		for(int x = 0;x<el.size();x++){
			out += el.get(x);
			out+='\r';
		}
		for(int x = 0;x<hs.size();x++){
			out += hs.get(x);
			out+='\r';
		}
		return out;
	}
	
	public static void main(String[] no) throws IOException{
		run r1 = new run();
		r1.walk();
	}
	
}
