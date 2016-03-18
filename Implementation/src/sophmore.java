
public class sophmore extends hsstu{
	private int math;
	
	public sophmore(){}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public String toString(){
		String output = super.toString();
		output += "9th Grade Math Score: " + math + "%";
		return output;
	}
	
}
