
public class elstudent implements student{
	
	private String fi;
	private String la;
	private int gr;
	private String in;
	
	public elstudent(){
	}
	
	public elstudent(String a){
		in = a;
		fi = in.substring(0, in.indexOf(' '));
		la = in.substring(in.indexOf(' ',in.indexOf(' ')+1)).trim();
		gr = Integer.parseInt(in.substring(in.indexOf(' ',in.indexOf(' ')+1)).trim());
		System.out.println(toString());
	}
	
	public void setIn(String a){
		in = a;
	}
	
	public String getIn(){
		return in;
	}
	
	public void setGLevel(int a) {
		gr = a;
	}

	public String getFName() {
		return fi;
	}

	public String getLName() {
		return la;
	}
	
	public String toString(){
		String output = la + ", " + fi + "  " + grd[gr];
		return output;
	}

	public void setFName(String a) {
		fi = a;
	}

	public void setLName(String a) {
		la = a;
	}

	public int getGLevel() {
		return gr;
	}
}
