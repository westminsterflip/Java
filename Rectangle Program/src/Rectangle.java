//Ian Patterson
import java.io.*;
public class Rectangle {

	private int length;
	private int width;
	private int area;
	
	public Rectangle(){
		length = 0;
		width = 0;
	}
	
	public void setLength(int leng){
		length = leng;
	}
	
	public void setWidth(int wid){	
		width = wid;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getLength(){
		return length;
	}
	
	public String toString(){
		String stars = "";
		for (int lengthy = 0; lengthy < getLength(); lengthy++){
			for (int widthy = 0; widthy < getWidth(); widthy++){
				stars += "* ";
			}
			stars += '\n';
		}
		return stars;
	}
	
	public int areaify(){
		area = getLength()*getWidth();
		return area;
	}
	
	public int inputLength()throws IOException{
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
			}
		}
		return length1;
	}
	
	public int inputWidth()throws IOException{
		boolean inputCorrect = false;
		int width1 = 0;
		while (inputCorrect == false){
			InputStreamReader readerW = new InputStreamReader(System.in);
			BufferedReader inputW = new BufferedReader(readerW);
		
			System.out.print("Width: ");
			width1 = Integer.parseInt(inputW.readLine());
			
			if (width1 > 0 && width1 < 26){
				inputCorrect = true;
			} else {
				inputCorrect = false;
			}
		}
		return width1;
	}
	
	public static void main(String[] args)throws IOException{
		String affirmation = "";
		do{
			Rectangle r1 = new Rectangle();
			
			r1.setWidth(r1.inputWidth());
			r1.setLength(r1.inputLength());
			System.out.println("Your rectangle is " + r1.getWidth() + " stars across and "
			+ r1.getLength() + " stars down, whith an area of " + r1.areaify() + " square stars.");
			System.out.println(r1.toString());
			
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader inputa = new BufferedReader(reader);
			
			System.out.print("Continue? (exit to end) ");
			affirmation = inputa.readLine();
		}
		while (affirmation != "exit");
	}
	
}
