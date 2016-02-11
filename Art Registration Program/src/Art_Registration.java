

import java.io.*;
public class Art_Registration {
	
	private int ID, classNum, batikSize, calligraphySize, paintingSize, sculptureSize, weavingSize;
	
	
		
	
	
	public Art_Registration(){
		ID = 0;
		classNum = 0;
	}
	
	public void setID(int id){
		ID = id;
	}
	
	public void setClassNum(int number){
		classNum = number;
	}
	
	public void addtoBatik(){
		batikSize += 1; 
	}
	
	public void addtoCalli(){
		calligraphySize += 1;
	}
	
	public void addtoPaint(){
		paintingSize += 1;
	}
	
	public void addtoSculpt(){
		sculptureSize += 1;
	}
	
	public void addtoWeav(){
		weavingSize += 1;
	}
	
	public int getID(){
		return ID;
	}
	
	public int getClassNum(){
		return classNum;
	}
	
	public int getBatik(){
		return batikSize;
	}
	
	public int getCalli(){
		return calligraphySize;
	}
	
	public int getPaint(){
		return paintingSize;
	}
	
	public int getSculp(){
		return sculptureSize;
	}
	
	public int getWeav(){
		return weavingSize;
	}
	
	public int[] inputData()throws IOException{
		InputStreamReader readerID = new InputStreamReader(System.in);
		BufferedReader inputID = new BufferedReader(readerID);
		boolean validIn = false;
		int id = 0;
		
		while (validIn == false){
			System.out.print("Input ID code: ");
			String input1 = inputID.readLine();
			if (input1.equals("")){
				System.out.println("ID must be given");
				validIn = false;
			} else {
				id = Integer.parseInt(input1);
				validIn = true;
			}
		}
	
		InputStreamReader readerCourse = new InputStreamReader(System.in);
		BufferedReader inputCourse = new BufferedReader(readerCourse);
		String characters = "bBcCpPsSwW";
		validIn = false;
		int coursenum = 0;
		
		while (validIn == false){
			System.out.print("Input ID code: ");
			String course = inputCourse.readLine();
			if (course.equals("")){
				validIn = false;
				System.out.println("Course code must be given");
			} else if (characters.indexOf(course) == -1){
				System.out.println("Invalid course code");
				validIn = false;
			} else if (characters.indexOf(course) == 0 || characters.indexOf(course) == 1){
				validIn = true;
			} else if (characters.indexOf(course) == 2 || characters.indexOf(course) == 3){
				validIn = true;
				coursenum = 1;
			} else if (characters.indexOf(course) == 4 || characters.indexOf(course) == 5){
				validIn = true;
				coursenum = 2;
			} else if (characters.indexOf(course) == 6 || characters.indexOf(course) == 7){
				validIn = true;
				coursenum = 3;
			} else if (characters.indexOf(course) == 8 || characters.indexOf(course) == 9){
				validIn = true;
				coursenum = 4;
			}
		}
		int[] data = {id,coursenum};
		return data;
	}
	
	public void counter(){
		
	}
	
	public String toString(){
		
	}
	
	public static void main(String[] args)throws IOException{
		Art_Registration aR7 = new Art_Registration();
		int[] data1 = {0,0};
		for(data1 = aR7.inputData(); aR7.inputData()[0] == 0; data1 = aR7.inputData()){
			aR7.setID(aR7.inputData()[0]);
			aR7.setClassNum(aR7.inputData()[1]);
			
		}
	}
	
}
