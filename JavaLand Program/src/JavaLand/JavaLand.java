package JavaLand;

public class JavaLand {
	private int income;
	
	public JavaLand(int inc){
		income = inc;
	}
	
	public int getNumerator(){
		return numerator;
	}
	public void setNumerator(int num){
		numerator = num;
	}
	
	public JavaLand add(JavaLand otherFrac){
		int sumNumerator = getNumerator()+otherFrac.getNumerator();
		JavaLand result = new JavaLand(sumNumerator);
		return result;
	}
	public static void main(String[] args){
		JavaLand f1 = new JavaLand(1);
		JavaLand f2 = new JavaLand(3);
		JavaLand sum, difference, product, quotient;
		sum = f1.add(f2);
		System.out.println(f1.toString()+" + " + f2.toString()+ " = " + sum.toString());
		
	}
	public String toString(){
		return getNumerator() + " / ";
 	}
}
