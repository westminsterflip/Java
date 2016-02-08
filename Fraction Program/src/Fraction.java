

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
	public String toString(){
		return getNumerator() + " / "+getDenominator();
			 	}
	
	public static void main(String[] args){
		Fraction f1 = new Fraction(1,2);
		Fraction f2 = new Fraction(3,5);
		Fraction sum, difference, product, quotient;
		sum = f1.add(f2);
		System.out.println(f1.toString()+" + " + f2.toString()+ " = " + sum.toString());
		
	}
}
