//Ian Patterson

import java.io.*;
public class Water {
	
	private int ID;
	private char code;
	private int galused;
	
	public Water(int id, char cde, int galUsed){
		ID = id;
		code = cde;
		galused = galUsed;
	}
	
	public int getID(){
		return ID;
	}
	
	public char getCode(){
		return code;
	}
	
	public int getUsed(){
		return galused;
	}
	
	public void setID(int id){
		ID = id;
	}
	
	public void setCode(char cde){
		code = cde;
	}
	
	public void setUsed(int galUsed){
		galused = galUsed;
	}
	
	public double costify(){
		double cost = 0;
		
		if (getCode() == 'h' || getCode() == 'H'){
			cost = 5 + .0005*getUsed();
		} else if (getCode()=='c' || getCode() == 'H'){
			cost = 1000;
			if (getUsed()>4000000){
				cost += .00025 * (getUsed()-4000000);
			}
		} else if (getCode()=='i' || getCode() == 'I'){
			if (getUsed()>10000000){
				cost = 3000;
			} else if (getUsed()>4000000){
				cost = 2000;
			} else cost = 1000;
		}
		cost = Math.round(cost*100.0)/100.0;
		return cost;
	}
	
	public String toString(){
		return "Cost: $"  + costify();
	}
	
	public static void main(String[] args) throws IOException{
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader inputID = new BufferedReader(reader);
		System.out.print("ID: ");
		int identification = Integer.parseInt(inputID.readLine());
		
		InputStreamReader reader1 = new InputStreamReader(System.in);
		BufferedReader inputCode = new BufferedReader(reader1);
		System.out.print("Code: ");
		char cOde = inputCode.readLine().charAt(0);
		
		InputStreamReader input2 = new InputStreamReader(System.in);
		BufferedReader inputUsed = new BufferedReader(input2);
		System.out.print("Gallons Used: ");
		String galused = inputUsed.readLine();
		int gallonsused = Integer.parseInt(galused);
		
		Water w1 = new Water(identification, cOde, gallonsused);
		System.out.println(w1.toString());
	}
	
}

/*
 * 10 c 5000022:
 * 
 * ID: 10
 * Code: c
 * Gallons Used: 5000022
 * Cost: $1250.01
 * 
 * 11 i 1234567:
 * 
 * ID: 11
 * Code: i
 * Gallons Used: 1234567
 * Cost: $1000.0
 * 
 * 12 h 8765:
 * 
 * ID: 12
 * Code: h
 * Gallons Used: 8765
 * Cost: $9.38
 * 
 * 13 c 444444:
 * 
 * ID: 13
 * Code: c
 * Gallons Used: 444444
 * Cost: $1000.0
 * 
 * 14 i 5555555:
 * 
 * ID: 14
 * Code: i
 * Gallons Used: 5555555
 * Cost: $2000.0
 * 
 * 15 i 20000000:
 * 
 * ID: 14
 * Code: i
 * Gallons Used: 20000000
 * Cost: $3000.0
 * 
 */
