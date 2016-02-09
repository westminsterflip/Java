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
	
	public int getNumerator(){//Accesses private numerator int
		return numerator;
	}

	public int getDenominator(){//Accesses private denominator int
		return denominator;
	}
	
	public void setNumerator(int num){//Stores int to private int numerator
		numerator = num;
	}
	
	public void setDenominator(int den){//Stores int to private denominator
		denominator = den;
	}
	
	public Fraction add(Fraction otherFrac){//Adds fraction with common denominator, input is another frac
		int sumNumerator = getNumerator()*otherFrac.getDenominator()+otherFrac.getNumerator()*getDenominator();
		int sumDenominator = getDenominator()*otherFrac.getDenominator();
		Fraction result = new Fraction(sumNumerator, sumDenominator);
		return result;
	}
	
	public Fraction subtract(Fraction otherFrac){//subtracts fraction with common denominator, input is another frac
		int diffNumerator = getNumerator()*otherFrac.getDenominator()-otherFrac.getNumerator()*getDenominator();
		int diffDenominator = getDenominator()*otherFrac.getDenominator();
		Fraction result = new Fraction(diffNumerator, diffDenominator);
		return result;
	}
	
	public Fraction multiply(Fraction otherFrac){//Multiplies numerator and denominator separately , input is another frac
		int multNumerator = getNumerator()*otherFrac.getNumerator();
		int multDenominator = getDenominator()*otherFrac.getDenominator();
		Fraction result = new Fraction(multNumerator,multDenominator);
		return result;
	}
	
	public Fraction divide(Fraction otherFrac){//Divides numerator and denominator separately , input is another frac
		int divNumerator = getNumerator()*otherFrac.getDenominator();
		int divDenominator = getDenominator()*otherFrac.getNumerator();
		Fraction result = new Fraction(divNumerator,divDenominator);
		return result;
	}
	
	public String toString(){ //Setup fraction bar given int,int fraction
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



