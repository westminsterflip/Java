
public class freshman extends hsstu{
	private int disRef;
	
	public freshman(){}
	
	public int getDisRef() {
		return disRef;
	}

	public void setDisRef(int disRef) {
		this.disRef = disRef;
	}
	
	public String toString(){
		String output = super.toString();
		output += "Discipline Referrals: " + disRef;
		return output;
	}
	
}
