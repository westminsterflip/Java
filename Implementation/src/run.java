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
		while(u!=-1&&ts!=-1){
			setStu(u,ts);
			u=ts+1;
			ts = in.indexOf("$break$");
			fn = stu.substring(0,stu.indexOf(" ")).trim();
			ls = stu.substring(stu.indexOf(" "),stu.indexOf(" ",stu.indexOf(" ")+1)).trim();
			gr = stu.substring(stu.indexOf(" ",stu.indexOf(" ")+1),stu.indexOf(" ",stu.indexOf(" ",stu.indexOf(" ")+1))).trim();
			h = Integer.parseInt(gr);
			if(h<9){
				elstudent e = new elstudent();
				e.setFName(fn);
				e.setLName(ls);
				e.setGLevel(h);
				int ind = 0;
				while(el.get(ind).compareTo(e)<0){
					ind++;
				}
				ind--;
				el.add(ind,e);
			}else{
				if(h==12){
					senior h1 = new senior();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(Double.parseDouble(stu.substring(stu.indexOf(" ",stu.indexOf(gr)),stu.indexOf(" ",stu.indexOf(" ",stu.indexOf(gr)))).trim()));
					h1.setFine(Double.parseDouble(stu.substring(stu.indexOf(" ",stu.indexOf(gr)),stu.lastIndexOf(" ")).trim()));
					h1.setPodone(Boolean.parseBoolean(stu.substring(stu.lastIndexOf(" "))));
					int ind = 0;
					while(hs.get(ind).compareTo(h1)<0){
						ind++;
					}
					ind--;
					hs.add(ind,h1);
				}else if(h==11){
					junior h1 = new junior();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(Double.parseDouble(stu.substring(stu.indexOf(" ",stu.indexOf(gr)),stu.indexOf(" ",stu.indexOf(" ",stu.indexOf(gr)))).trim()));
					h1.setKeys(Integer.parseInt(stu.substring(stu.lastIndexOf(" "))));
					int ind = 0;
					while(hs.get(ind).compareTo(h1)<0){
						ind++;
					}
					ind--;
					hs.add(ind,h1);
				}else if(h==10){
					sophmore h1 = new sophmore();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(Double.parseDouble(stu.substring(stu.indexOf(" ",stu.indexOf(gr)),stu.indexOf(" ",stu.indexOf(" ",stu.indexOf(gr)))).trim()));
					h1.setMath(Double.parseDouble(stu.substring(stu.lastIndexOf(" "))));
					int ind = 0;
					while(hs.get(ind).compareTo(h1)<0){
						ind++;
					}
					ind--;
					hs.add(ind,h1);
				}else if(h==9){
					freshman h1 = new freshman();
					h1.setFName(fn);
					h1.setLName(ls);
					h1.setGLevel(h);
					h1.setQPA(Double.parseDouble(stu.substring(stu.indexOf(" ",stu.indexOf(gr)),stu.indexOf(" ",stu.indexOf(" ",stu.indexOf(gr)))).trim()));
					h1.setDisRef(Integer.parseInt(stu.substring(stu.lastIndexOf(" "))));
					int ind = 0;
					while(hs.get(ind).compareTo(h1)<0){
						ind++;
					}
					ind--;
					hs.add(ind,h1);
				}
			}
		}
	}
	
	public void walk() throws IOException{
		InputStreamReader i = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(i);
		System.out.print("Filename: ");
		b.readLine();
	}
	
}
