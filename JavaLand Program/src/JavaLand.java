
public class JavaLand {
	private double income;
	
	public JavaLand(double inc){
		income = inc;
	}
	
	public double getIncome(){
		return income;
	}
	public void setIncome(double inc){
		income = inc;
	}
	
	public double taxify(){
		double[] taxes = {.01,.05,.1};
		double tax;
		if (getIncome() <= 1000){
			tax = 0;
		} else {
			tax = 500;
			for(int r=10000;r<40000;r+=10000){
				if (getIncome()>= r){
					double multiplier = 
					tax = tax+
				}
			}
		}
		return tax;
	}
	public static void main(String[] args){
		JavaLand f1 = new JavaLand(1);
		JavaLand f2 = new JavaLand(3);
		JavaLand sum;
		sum = f1.add(f2);
		System.out.println(f1.toString()+" + " + f2.toString()+ " = " + sum.toString());
		
	}
	public String toString(){
		return getNumerator() + " / ";
 	}
}
