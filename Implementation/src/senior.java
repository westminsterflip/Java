
public class senior extends hsstu{
	private double fine;
	private boolean podone;
	hsstu h;
	
	public senior(){
		h = new hsstu();
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
		output += " $" + fine + " Portfolio Done: " + podone;
		return output;
	}

}
