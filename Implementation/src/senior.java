
public class senior extends hsstu{
	private double fine;
	private boolean podone;
	
	public senior(){}
	
	public senior(String fi, String la, int gr, double qpa,double fine,boolean podone){
		super(fi,la,gr,qpa);
		this.fine = fine;
		this.podone=podone;
	}
	
	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public boolean isPodone() {
		return podone;
	}

	public void setPodone(boolean podone) {
		this.podone = podone;
	}
	
	public String toString(){
		String output = super.toString();
		output += " Fine: $" + fine + " Portfolio Done: " + podone;
		return output;
	}

}
