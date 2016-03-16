
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
}