//Ian Patterson
public class Change_Program_3 {
	
	public static void main(String[] args) {
		//Amount paid and amount due
		double amtpaid=100.00,amtdue=54.39;
		int changedue,num; //num is placeholder value for inside for loop
		int [] nums = {5000,2000,1000,500,100,25,10,5,1};  //numbers, in cents, to divide by
		
		System.out.println(" Fifties: Twenties:   Tens:     Fives:    Ones:   Quarters:   Dimes:   Nickels:  Pennies:");
		changedue = (int)(100*(amtpaid-amtdue));
		
		for(int x:nums){//x is serving solely as a counter
			num = changedue/x;
			System.out.print("    "+num+"     ");
			changedue = changedue%x;
		}
	}
}
/*
 *  Fifties: Twenties:   Tens:     Fives:    Ones:   Quarters:   Dimes:   Nickels:  Pennies:
 *     0         2         0         1         0         2         1         0         1     
 */