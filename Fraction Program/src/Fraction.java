//Ian Patterson
//Class takes two predetermined fractions, holds the private variables numerator and denominator
// and calculates and outputs the sum, difference, quotient, and product of the two fractions
public class Fraction {
	private int numerator;
	private int denominator;
	
	public Fraction(int num, int den){
		denominator = den;
		numerator = num;
	}
	
	public int getNumerator(){
		return numerator;
	}

	public int getDenominator(){
		return denominator;
	}
	
	public void setNumerator(int num){
		numerator = num;
	}
	
	public void setDenominator(int den){
		denominator = den;
	}
	
	public Fraction add(Fraction otherFrac){
		int sumNumerator = getNumerator()*otherFrac.getDenominator()+otherFrac.getNumerator()*getDenominator();
		int sumDenominator = getDenominator()*otherFrac.getDenominator();
		Fraction result = new Fraction(sumNumerator, sumDenominator);
		return result;
	}
	
	public Fraction subtract(Fraction otherFrac){
		int diffNumerator = getNumerator()*otherFrac.getDenominator()-otherFrac.getNumerator()*getDenominator();
		int diffDenominator = getDenominator()*otherFrac.getDenominator();
		Fraction result = new Fraction(diffNumerator, diffDenominator);
		return result;
	}
	
	public Fraction multiply(Fraction otherFrac){
		int multNumerator = getNumerator()*otherFrac.getNumerator();
		int multDenominator = getDenominator()*otherFrac.getDenominator();
		Fraction result = new Fraction(multNumerator,multDenominator);
		return result;
	}
	
	public Fraction divide(Fraction otherFrac){
		int divNumerator = getNumerator()*otherFrac.getDenominator();
		int divDenominator = getDenominator()*otherFrac.getNumerator();
		Fraction result = new Fraction(divNumerator,divDenominator);
		return result;
	}
	
	public String toString(){
		return getNumerator() + "/"+getDenominator();
	}
	
	public static void main(String[] args){
		Fraction f1 = new Fraction(1,2);
		Fraction f2 = new Fraction(3,5);
		Fraction sum = f1.add(f2), difference = f1.subtract(f2), product = f1.multiply(f2), quotient = f1.divide(f2);
		System.out.println(f1.toString()+" + " + f2.toString()+ " = " + sum.toString());
		System.out.println(f1.toString()+" - " + f2.toString()+ " = " + difference.toString());
		System.out.println(f1.toString()+" * " + f2.toString()+ " = " + product.toString());
		System.out.println(f1.toString()+" / " + f2.toString()+ " = " + quotient.toString());
	}
}
/* 1/2 + 3/5 = 11/10
 * 1/2 - 3/5 = -1/10
 * 1/2 * 3/5 = 3/10
 * 1/2 / 3/5 = 5/6 */



