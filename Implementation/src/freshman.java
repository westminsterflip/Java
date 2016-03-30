
public class freshman extends hsstu{
	private int disRef;
	
	public freshman(){}
	
	public freshman(String fi, String la, int gr, double qpa,int disRef){
		super(fi,la,gr,qpa);
		this.disRef = disRef;
	}
	
	public int getDisRef() {
		return disRef;
	}

	public void setDisRef(int disRef) {
		this.disRef = disRef;
	}
	
	public String toString(){
		String output = super.toString();
		output += " Discipline Referrals: " + disRef;
		return output;
	}
	
}
