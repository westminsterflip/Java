
public class sophmore extends hsstu{
	private double math;
	
	public sophmore(){}

	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public String toString(){
		String output = super.toString();
		output += "9th Grade Math Score: " + math + "%";
		return output;
	}
	
}
