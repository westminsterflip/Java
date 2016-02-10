

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
	
	public int inputID()throws IOException{
		InputStreamReader readerID = new InputStreamReader(System.in);
		BufferedReader inputID = new BufferedReader(readerID);
		
		System.out.print("Input ID code: ");
		int id = Integer.parseInt(inputID.readLine());
		return id;
	}
	
	public int inputCourse(){
		InputStreamReader readerCourse = new InputStreamReader(System.in);
		BufferedReader inputCourse = new BufferedReader(readerCourse);
		String bBcCpPsSwW
		
		do{
		System.out.print("Input ID code: ");
		String course = inputCourse.readLine();
		}
	}

}
