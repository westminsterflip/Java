
public class elstudent implements student,Comparable<elstudent>{
	
	private String fi;
	private String la;
	private int gr;
	
	public elstudent(){
	}
	
	public void setFName(String a) {
		fi = a;
	}

	public void setLName(String a) {
		la = a;
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
	
	public int getGLevel() {
		return gr;
	}

	public int compareTo(elstudent o) {
		if(getGLevel()>o.getGLevel()){
			return 1;
		}else if(getGLevel()<o.getGLevel()){
			return -1;
		}else if(!getFName().equals(o.getFName())){
			return getFName().compareTo(o.getFName());
		}else{
			return getLName().compareTo(o.getLName());
		}
	}

	public String toString(){
		String output = grd[gr] + " " + fi + " " + la;
		return output;
	}
}
