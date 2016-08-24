
public class sophmore extends hsstu{
	private double math;
	
	public sophmore(){}
	
	public sophmore(String fi, String la, int gr, double qpa,double math){
		super(fi,la,gr,qpa);
		this.math = math;
	}

	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public String toString(){
		String output = super.toString();
		output += " 9th Grade Math Score: " + math + "%";
		return output;
	}
	
}
