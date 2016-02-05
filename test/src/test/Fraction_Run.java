package test;
public class Fraction_Run{

	public static void main(String[] args){
		Fraction f1 = new Fraction(1,2);
		Fraction f2 = new Fraction(3,5);
		Fraction sum, difference, product, quotient;
		sum = f1.add(f2);
		System.out.println(f1.toString()+" + " + f2.toString()+ " = " + sum.toString());
		
	}
}
