//Ian Patterson
//Class has private variables length, width, and area, area is calculated.  
//Length can be 1 to 25, width 1 to 80.  
import java.io.*;
public class Rectangle {

	private int length;
	private int width;
	private int area;
	
	public Rectangle(){
		length = 0;
		width = 0;
	}
	
	public void setLength(int leng){//Sets private int length
		length = leng;
	}
	
	public void setWidth(int wid){	//Sets private int length
		width = wid;
	}
	
	public int getWidth(){ //Accesses private int width
		return width;
	}
	
	public int getLength(){ //Accesses private int length
		return length;
	}
	
	public String toString(){ //forms stars string; the rectangle
		String stars = "";
		for (int lengthy = 0; lengthy < getLength(); lengthy++){
			for (int widthy = 0; widthy < getWidth(); widthy++){
				stars += "* ";
			}
			stars += '\n';
		}
		return stars;
	}
	
	public int areaify(){ //Calculates area given a length and a width
		area = getLength()*getWidth();
		return area;
	}
	
	public int inputLength()throws IOException{//Error traps input length to be 1 to 25
		boolean inputCorrect = false;
		int length1 = 0;
		while (inputCorrect == false){
			InputStreamReader readerL = new InputStreamReader(System.in);
			BufferedReader inputL = new BufferedReader(readerL);
		
			System.out.print("Length: ");
			length1 = Integer.parseInt(inputL.readLine());
			
			if (length1 > 0 && length1 < 26){
				inputCorrect = true;
			} else {
				inputCorrect = false;
				System.out.println("Length can be any value from 1 to 25.");
			}
		}
		return length1;
	}
	
	public int inputWidth()throws IOException{//Error traps input width to be 1 to 25
		boolean inputCorrect = false;
		int width1 = 0;
		while (inputCorrect == false){
			InputStreamReader readerW = new InputStreamReader(System.in);
			BufferedReader inputW = new BufferedReader(readerW);
		
			System.out.print("Width: ");
			width1 = Integer.parseInt(inputW.readLine());
			
			if (width1 > 0 && width1 < 81){
				inputCorrect = true;
			} else {
				inputCorrect = false;
				System.out.println("Width can be any value from 1 to 80.");
			}
		}
		return width1;
	}
	
	public static void main(String[] args)throws IOException{
		String affirmation = "";
		do {
			Rectangle r1 = new Rectangle();
			
			r1.setWidth(r1.inputWidth());
			r1.setLength(r1.inputLength());
			System.out.println("Your rectangle is " + r1.getWidth() + " stars across and "
			+ r1.getLength() + " stars down, with an area of " + r1.areaify() + " square stars.");
			System.out.println(r1.toString());
			
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader inputa = new BufferedReader(reader);
			
			System.out.print("Continue? (exit to end) ");
			affirmation = inputa.readLine();
		}
		while (!affirmation.equals("exit"));//Any input other than "exit" will continue the program
	}
	
}
/*
Width: -8
Width can be any value from 1 to 80.
Width: 100
Width can be any value from 1 to 80.
Width: 81
Width can be any value from 1 to 80.
Width: 1
Length: -5
Length can be any value from 1 to 25.
Length: 30
Length can be any value from 1 to 25.
Length: 25
Your rectangle is 1 stars across and 25 stars down, with an area of 25 square stars.
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 

Continue? (exit to end) maybe
Width: 10
Length: 2
Your rectangle is 10 stars across and 2 stars down, with an area of 20 square stars.
* * * * * * * * * * 
* * * * * * * * * * 

Continue? (exit to end) cheese noodles
Width: 5
Length: 8
Your rectangle is 5 stars across and 8 stars down, with an area of 40 square stars.
* * * * * 
* * * * * 
* * * * * 
* * * * * 
* * * * * 
* * * * * 
* * * * * 
* * * * * 

Continue? (exit to end) exit
*/