public abstract class hsstu implements student,Comparable<hsstu>{
	private String fi;
	private String la;
	private int gr;
	private double qpa;
	
	public hsstu(){}
	
	public hsstu(String fi, String la, int gr, double qpa){
		this.fi=fi;
		this.la=la;
		this.gr=gr;
		this.qpa=qpa;
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

	public void setQPA(double a){
		qpa = a;
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
	
	public double getQPA(){
		return qpa;
	}

	public int compareTo(hsstu o) {
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
		String output;
		output = grd[gr] + " " + fi + " " + la + "  " + qpa;
		return output;
	}
	
}