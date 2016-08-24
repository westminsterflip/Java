//Ian Patterson
//JavaLand calculates an income tax on given multipliers, holds private variable income.  It calculates a double tax.  Input can only be an integer, although the length is not limited by the class.
//This program will run until -999 is entered as income, at which point it will output total taxes paid.
import java.io.*;
public class JavaLand_ver2 {
	private int income;
	
	public JavaLand_ver2(int inc){
		income = inc;
	}
	
	public int getIncome(){ //Accesses private income int
		return income;
	}
	public void setIncome(int inc){ //Sets private income int
		income = inc;
	}
	
	public double taxify(){ //Finds tax based off of income
		double tax;
		if (getIncome()>30000){
			tax = 1600 + (getIncome()-30000)*.1;
		} else if (getIncome()>10000){
			tax = 600 + (getIncome()-10000)*.05;
		} else if (getIncome()>1000){
			tax = 500 + getIncome()*.01;
		} else 
			{tax = .01*getIncome();}
		return tax;
	}
	
	public String toString(){ //Sets up header for output
		return "Income:                         Tax:";
 	}
	
	public static void main(String[] args)throws IOException{
		double totalTaxes = 0;
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(reader);
		
		System.out.print("Enter income amount: ");
		for (String inputString = input.readLine(); !inputString.equals("-999"); inputString = input.readLine()){
		int dot = inputString.indexOf(".");
		int inputNum;
		
		if (dot>0){
			double part1 = (double) Integer.parseInt(inputString.substring(0,dot));
			double part2 = (double) (Integer.parseInt(inputString.substring(dot+1))/100);
		inputNum = (int) Math.round(part1 + part2);
		} else {
			inputNum = Integer.parseInt(inputString);
		}
		
		JavaLand_ver2 t1 = new JavaLand_ver2(inputNum);
		double taxesOwed = t1.taxify();
		
		System.out.println(t1.toString());
		System.out.print("$"+t1.getIncome());
		for(int w = inputString.length()+2; w < 33; w++){
			System.out.print(" ");
		}
		System.out.println("$" + taxesOwed);
		System.out.println("");
		totalTaxes += taxesOwed;
		System.out.print("Enter income amount: ");
		}
		System.out.println("");
		System.out.println("Total taxes paid:               $" + (totalTaxes));
	}
}

/*
Enter income amount: 0
Income:                         Tax:
$0                              $0.0

Enter income amount: 500
Income:                         Tax:
$500                            $5.0

Enter income amount: 5432
Income:                         Tax:
$5432                           $554.32

Enter income amount: 15006
Income:                         Tax:
$15006                          $850.3

Enter income amount: 32999
Income:                         Tax:
$32999                          $1899.9

Enter income amount: 100555
Income:                         Tax:
$100555                         $8655.5

Enter income amount: -999

Total taxes paid:               $11965.02
 */
