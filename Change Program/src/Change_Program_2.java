//Ian Patterson
public class Change_Program_2 {
	
	public static void main(String[] args) {
		//Amount paid and amount due
		double amtpaid=100.00,amtdue=54.39;
		int fifties,twenties,tens,fives,ones,quarters,dimes,nickels,pennies,changedue;
		
		
		changedue = (int)(100*(amtpaid - amtdue));
		fifties = (changedue/5000);
		changedue = changedue%5000;
		twenties = (changedue/2000);
		changedue = changedue%2000;
		tens = (changedue/1000);
		changedue = changedue%1000;
		fives = (changedue/500);
		changedue = changedue%500;
		ones = (changedue/100);
		changedue = changedue%100;
		quarters = (changedue/25);
		changedue = changedue%25;
		dimes = (changedue/10);
		changedue = changedue%10;
		nickels = (changedue/5);
		changedue = changedue%5;
		pennies = changedue;
		System.out.println(" Fifties: Twenties:   Tens:     Fives:    Ones:   Quarters:   Dimes:   Nickels:  Pennies:");
		System.out.println("    "+fifties+"         "+twenties+"         "+tens+"         "+fives+"         "+ones+"         "+quarters+"         "+dimes+"         "+nickels+"         "+pennies);
	}

}
/*
 *  Fifties: Twenties:   Tens:     Fives:    Ones:   Quarters:   Dimes:   Nickels:  Pennies:
 *     0         2         0         1         0         2         1         0         1     
 */