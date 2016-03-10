import java.util.*;
public class student {
	private String[] studen = new String[4];
	
	public student(){
	}
	
	public String[] getStu(){
		return studen;
	}
	
	public void setID(String id){
		studen[0] = id;
	}
	
	public void setGrade(String grade){
		studen[1] = grade;
		try{
			int grd = Integer.parseInt(grade);
			String letter;
			if(grd<0||grd>100){
				letter = "Invalid grade";
				studen[1]=null;
			}
			else if(grd>89)
				letter = "A";
			else if(grd>79)
				letter = "B";
			else if(grd>69)
				letter = "C";
			else if (grd>64)
				letter = "D";
			else
				letter = "F";
			
			studen[2] = letter;
		}
		catch(Exception ex){
			studen[2] = null;
		}
	}
	
	public void setMajor(String major){
		String[] code = {"C","E","M","B","N","A"};
		String[] names = {"Computer Science","Education","Mathematics","Buisness","Engineering","Art"};
		studen[3] = names[Arrays.asList(code).indexOf(major.toUpperCase())];
	}
	
}
