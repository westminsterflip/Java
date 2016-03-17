
public abstract class hsstu implements student,Comparable<String>{
	private String fi;
	private String la;
	private int gr;
	private int qpa;
	
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
	
}