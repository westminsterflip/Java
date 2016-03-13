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
	
	public String getOne(){
		return studen[1];
	}
	
	public int getGrade(){
		return Integer.parseInt(studen[1].substring(0,studen[1].indexOf("%")));
	}
	
	public void setGrade(String grade){
		try{
			studen[1] = grade + "%";
			int grd = Integer.parseInt(grade);
			String letter;
			if(grd<0||grd>100){
				letter = "Grade";
				studen[1]="Invalid";
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
			studen[1] = "Invalid";
			studen[2] = "Grade";
		}
	}
	
	public String getID(){
		return studen[0];
	}
	
	public void setMajor(String major){
		//System.out.println(major);
		String[] code = {"C","E","M","B","N","A"};
		String[] names = {"Computer Science","Education","Mathematics","Buisness","Engineering","Art"};
		try{
			studen[3] = names[Arrays.asList(code).indexOf(major.toUpperCase().trim())];
		}
		catch(Exception e){
			studen[3] = "Invalid Course Code";
		}
	}
	
	public String getStuff(){
		String output = studen[0].trim();
		for(int x=studen[0].trim().length();x<5;x++){
			output+=" ";
		}
		output += studen[1].trim();
		for(int x=studen[1].trim().length();x<8;x++){
			output+=" ";
		}
		output += studen[2].trim();
		for(int x=studen[2].trim().length();x<15;x++){
			output+=" ";
		}
		output += studen[3].trim();
		return output;
	}
	
}
