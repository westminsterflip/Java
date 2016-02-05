package test;

public class Fraction {
	private int numerator;
	private int denominator;
	


	public Fraction(int num, int den){
		numerator=num;
		denominator=den;
	
	
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
	
}