package test;

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
}