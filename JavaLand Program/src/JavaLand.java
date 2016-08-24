//Ian Patterson
//JavaLand calculates an income tax on given multipliers, holds private variable income.  It calculates a double tax.  Input can only be an integer, although the length is not limited by the class.
import java.io.*;
public class JavaLand {
	private int income;
	
	public JavaLand(int inc){
		income = inc;
	}
	
	public int getIncome(){
		return income;
	}
	public void setIncome(int inc){
		income = inc;
	}
	
	public double taxify(){
		double[] taxes = {.01,.05,.1};
		double tax, placeHolder = getIncome();
		if (getIncome() <= 1000.00){
			tax = .01*placeHolder;
		} else {
			tax = 500;
			for(int r=10000;r<40000;r+=10000){
				double multiplier = taxes[r/10000-1];
				if (placeHolder >= r){
					tax += r*multiplier;
					placeHolder -= r;
				} 
				else {
					tax += placeHolder*multiplier;
					r = 40000;
					placeHolder = 0;
				}
			}
			if (getIncome() > 60000){
				tax += (getIncome()-60000)*.1;}	
		}
		return tax;
	}
	
	public String toString(){
		return "Income:                         Tax:";
 	}
	
	public static void main(String[] args)throws IOException{
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(reader);
		
		System.out.print("Enter income amount:");
		String inputString = input.readLine();
		int dot = inputString.indexOf(".");
		int inputNum;
		
		if (dot>0){
			double part1 = (double) Integer.parseInt(inputString.substring(0,dot));
			double part2 = (double) (Integer.parseInt(inputString.substring(dot+1))/100);
		inputNum = (int) Math.round(part1 + part2);
		} else {
			inputNum = Integer.parseInt(inputString);
		}
		
		JavaLand t1 = new JavaLand(inputNum);
		double taxesOwed = t1.taxify();
		
		System.out.println(t1.toString());
		System.out.print("$"+t1.getIncome());
		for(int w = inputString.length()+2; w < 33; w++){
			System.out.print(" ");
		}
		System.out.println("$" + taxesOwed);
		
	}
	
}
/* Output for:
 * 0:
 * 
 * Enter income amount:0
 * Income:                         Tax:
 * $0                              $0.0
 * 
 * 500:
 * 
 * Enter income amount:500
 * Income:                         Tax:
 * $500                            $5.0
 * 
 * 5432:
 * 
 * Enter income amount:5432
 * Income:                         Tax:
 * $5432                           $554.32
 * 
 * 15006:
 * 
 * Enter income amount:15006
 * Income:                         Tax:
 * $15006                          $850.3
 * 
 * 32999:
 * 
 * Enter income amount:32999
 * Income:                         Tax:
 * $32999                          $1899.9
 * 
 * 100555:
 * 
 * Enter income amount:100555
 * Income:                         Tax:
 * $100555                         $8655.5
 * 
 */
