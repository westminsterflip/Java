
public abstract class hsstu implements student,Comparable<hsstu>{
	private String fi;
	private String la;
	private int gr;
	private int qpa;
	
	public hsstu(){}
	
	public String toString(){
		String output;
		output = la + ", " + fi + "  " + grd[gr] + " " + qpa;
		return output;
	}
	
	public int compareTo(hsstu o) {
		if(getGLevel()>o.getGLevel()){
			return -1;
		}else if(getGLevel()<o.getGLevel()){
			return 1;
		}else if(!getFName().equals(o.getFName())){
			return getFName().compareTo(o.getFName());
		}else{
			return getLName().compareTo(o.getLName());
		}
	}
	
	public void setFName(String a) {
		fi = a;
	}
	
	public void setLName(String a) {
		// TODO Auto-generated method stub
		la = a;
	}

	public void setGLevel(int a) {
		// TODO Auto-generated method stub
		gr = a;
	}

	public String getFName() {
		return fi;
	}
	
	public String getLName() {
		// TODO Auto-generated method stub
		return la;
	}

	public int getGLevel() {
		// TODO Auto-generated method stub
		return gr;
	}
	
	public int getQPA(){
		return qpa;
	}
	
	public void setQPA(int a){
		qpa = a;
	}
	
}