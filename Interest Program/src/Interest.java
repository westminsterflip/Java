//Ian Patterson
//This program outputs interest starting at an initial value of $1000 and rates of 6% to 12%
public class Interest {
	
	public static void main(String[] args){
		double interestRate = 0;
		int investment = 1000;
		for (interestRate = 6; interestRate < 13; interestRate += 1){
			System.out.println("");
			System.out.println("Interest rate of %" + (interestRate));
			System.out.println("");
			System.out.println("Investment at year 0 is $" + investment);
			System.out.println("");
			double amountHad = investment;
			for (int year = 1;year < 11; year++){
				amountHad *= 1+interestRate*.01;
				System.out.println("Investment at year " + year + " is $" + Math.round(amountHad*100)/100.0);
				System.out.println("");
			}
		}
	}

}
/*

Interest rate of %6.0

Investment at year 0 is $1000

Investment at year 1 is $1060.0

Investment at year 2 is $1123.6

Investment at year 3 is $1191.02

Investment at year 4 is $1262.48

Investment at year 5 is $1338.23

Investment at year 6 is $1418.52

Investment at year 7 is $1503.63

Investment at year 8 is $1593.85

Investment at year 9 is $1689.48

Investment at year 10 is $1790.85


Interest rate of %7.0

Investment at year 0 is $1000

Investment at year 1 is $1070.0

Investment at year 2 is $1144.9

Investment at year 3 is $1225.04

Investment at year 4 is $1310.8

Investment at year 5 is $1402.55

Investment at year 6 is $1500.73

Investment at year 7 is $1605.78

Investment at year 8 is $1718.19

Investment at year 9 is $1838.46

Investment at year 10 is $1967.15


Interest rate of %8.0

Investment at year 0 is $1000

Investment at year 1 is $1080.0

Investment at year 2 is $1166.4

Investment at year 3 is $1259.71

Investment at year 4 is $1360.49

Investment at year 5 is $1469.33

Investment at year 6 is $1586.87

Investment at year 7 is $1713.82

Investment at year 8 is $1850.93

Investment at year 9 is $1999.0

Investment at year 10 is $2158.92


Interest rate of %9.0

Investment at year 0 is $1000

Investment at year 1 is $1090.0

Investment at year 2 is $1188.1

Investment at year 3 is $1295.03

Investment at year 4 is $1411.58

Investment at year 5 is $1538.62

Investment at year 6 is $1677.1

Investment at year 7 is $1828.04

Investment at year 8 is $1992.56

Investment at year 9 is $2171.89

Investment at year 10 is $2367.36


Interest rate of %10.0

Investment at year 0 is $1000

Investment at year 1 is $1100.0

Investment at year 2 is $1210.0

Investment at year 3 is $1331.0

Investment at year 4 is $1464.1

Investment at year 5 is $1610.51

Investment at year 6 is $1771.56

Investment at year 7 is $1948.72

Investment at year 8 is $2143.59

Investment at year 9 is $2357.95

Investment at year 10 is $2593.74


Interest rate of %11.0

Investment at year 0 is $1000

Investment at year 1 is $1110.0

Investment at year 2 is $1232.1

Investment at year 3 is $1367.63

Investment at year 4 is $1518.07

Investment at year 5 is $1685.06

Investment at year 6 is $1870.41

Investment at year 7 is $2076.16

Investment at year 8 is $2304.54

Investment at year 9 is $2558.04

Investment at year 10 is $2839.42


Interest rate of %12.0

Investment at year 0 is $1000

Investment at year 1 is $1120.0

Investment at year 2 is $1254.4

Investment at year 3 is $1404.93

Investment at year 4 is $1573.52

Investment at year 5 is $1762.34

Investment at year 6 is $1973.82

Investment at year 7 is $2210.68

Investment at year 8 is $2475.96

Investment at year 9 is $2773.08

Investment at year 10 is $3105.85

*/