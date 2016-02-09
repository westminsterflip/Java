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
		
		if (getCode() == 'h'){
			cost = 5 + .0005*getUsed();
		} else if (getCode()=='c'){
			cost = 1000;
			if (getUsed()>4000000){
				cost += .00025 * (getUsed()-4000000);
			}
		} else if (getCode()=='i'){
			if (getUsed()>10000000){
				cost = 3000;
			} else if (getUsed()>4000000){
				cost = 2000;
			} else cost = 1000;
		}
		cost = Math.round(cost*100)/100;
		return cost;
	}
	
	public String toString(){
		return "ID  Code  Gallons Used  Cost";
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
		double cost = w1.costify();
		System.out.println("ID:  Code:  Gallons Used:  Cost");
		System.out.print(w1.getID() + "     " + w1.getCode() + "    " + w1.getUsed());
		for (int w = galused.length(); w<15; w++){
			System.out.print(" ");
		}
		System.out.println(cost);
	}
	
}
