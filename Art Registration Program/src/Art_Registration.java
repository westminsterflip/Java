//Ian Patterson
//Class stores privat integers ID, classNum, batikSize, calligraphySize, paintingSize, sculptureSize, weavingSize, coursenum,
//calculates the number of people in a class, and how many sections should be made for that class
import java.io.*;
public class Art_Registration {
	
	private int ID, classNum, batikSize, calligraphySize, paintingSize, sculptureSize, weavingSize, coursenum;
	
	public Art_Registration(){
		ID = 0;
		classNum = 0;
	}
	
	public void setID(int id){ //Stores private int ID
		ID = id;
	}
	
	public void setClassNum(int number){//Stores private int classNum
		classNum = number;
	}
	
	public void setCourse(int cse){// Stores private int coursenum
		coursenum = cse;
	}

	public void addtoBatik(){//counter for number of people in the Batik class
		batikSize += 1; 
	}
	
	public void addtoCalli(){//counter for number of people in the Calligraphy class
		calligraphySize += 1;
	}
	
	public void addtoPaint(){//counter for number of people in the painting class
		paintingSize += 1;
	}
	
	public void addtoSculpt(){//counter for number of people in the sculpture class
		sculptureSize += 1;
	}
	
	public void addtoWeav(){// counter fot number of people in weaving class
		weavingSize += 1;
	}
	
	public int getID(){//Accesses private int ID
		return ID;
	}
	
	public int getClassNum(){//Accesses private int classnum
		return classNum;
	}
	
	public int getBatik(){//Accesses private int batikSize
		return batikSize;
	}
	
	public int getCalli(){//Accesses private int calligraphySize
		return calligraphySize;
	}
	
	public int getPaint(){//Accesses private int paintingSize
		return paintingSize;
	}
	
	public int getSculp(){//Accesses private int sculptureSize
		return sculptureSize;
	}
	
	public int getWeav(){//Accesses private int weavingSize
		return weavingSize;
	}
	
	public int getCourse(){//Accesses private int coursenum
		return coursenum;
	}
	
	public int inputID()throws IOException{//Input for id, error traps so only a positive integer may be entered, and returns the input as an integer
		InputStreamReader readerID = new InputStreamReader(System.in);
		BufferedReader inputID = new BufferedReader(readerID);
		boolean validIn = false;
		int id=0, num1=0;
		String numberline = "0123456789", input1 = "";
		
		while (validIn == false){
			System.out.print("Input ID code: ");
			input1 = inputID.readLine();
			if (input1.equals("")){
				System.out.println("ID must be given");
				validIn = false;
			} else {
				for (num1 = 0;num1 < input1.length();num1++){
					if (numberline.indexOf(input1.charAt(num1)) == -1){
						validIn = false;
						System.out.println("ID must be a non-negative integer");
					} else {
						validIn = true;
					}
				}
			}
		}
		id = Integer.parseInt(input1);
		return id;
	}
	
	public int inputCourse()throws IOException{//Input for coursenum, error traps so only an approved code may be entered lower or uppercase, and returns the input as an integer to be used in the toString
		InputStreamReader readerCourse = new InputStreamReader(System.in);
		BufferedReader inputCourse = new BufferedReader(readerCourse);
		String characters = "bBcCpPsSwW";
		boolean validIn = false;
		
		while (validIn == false){
			System.out.print("Input course code: ");
			String course = inputCourse.readLine();
			if (course.equals("")){
				validIn = false;
				System.out.println("Course code must be given");
			} else if (characters.indexOf(course) == -1){
				System.out.println("Invalid course code");
				validIn = false;
			} else if (characters.indexOf(course) == 0 || characters.indexOf(course) == 1){
				validIn = true;
				setCourse(0);
			} else if (characters.indexOf(course) == 2 || characters.indexOf(course) == 3){
				validIn = true;
				setCourse(1);
			} else if (characters.indexOf(course) == 4 || characters.indexOf(course) == 5){
				validIn = true;
				setCourse(2);
			} else if (characters.indexOf(course) == 6 || characters.indexOf(course) == 7){
				validIn = true;
				setCourse(3);
			} else if (characters.indexOf(course) == 8 || characters.indexOf(course) == 9){
				validIn = true;
				setCourse(4);
			}
		}
		return coursenum;
	}
		
	public String counter(){//Counters for number of students in a class and outputs the ID and full class name
		
		String student = "ID  Preferred Class" + '\r';
		if (getClassNum() == 0){
			addtoBatik();
			student += getID() + "  Batik";
		} else if (getClassNum() == 1){
			addtoCalli();
			student += getID() + "  Calligraphy";
		} else if (getClassNum() == 2){
			addtoPaint();
			student += getID() + "  Painting";
		} else if (getClassNum() == 3){
			addtoSculpt();
			student += getID() + "  Sculpture";
		} else if (getClassNum() == 4){
			addtoWeav();
			student += getID() + "  Weaving";
		}
		return student;
	}
	
	public String toString(){//Checks sections needed, unassigned students, and total number in each class, returns data as string
		int[] classSizes = {getBatik(),getCalli(),getPaint(),getSculp(),getWeav()},size = {6,4,7,4,5};
		String[] classNames = {"Batik", "Calligraphy", "Painting", "Sculpture", "Weaving"};
		int county = 0, length = 0, unassigned = 0, length1 = 0, sections = 0;
		String output = "Class Name:  Number of Students:  Sections:";
		
		
		for (county = 0;county < 5;county++){
			output += '\r' + classNames[county];
			for (length = classNames[county].length(); length < 13; length++){
				output += " ";
			}
			if (classSizes[county] < 3){
				unassigned += classSizes[county];
				output += "Class will not be offered due to lack of requests.";
			} else {
				String number = classSizes[county] + "";
				output += number;
				for (length1 = number.length();length1 <22;length1++){
					output += " ";
				}
				sections = (size[county] + classSizes[county])/size[county];
				output += sections;
			}
		}
		return output;
		
	}
	
	public static void main(String[] args)throws IOException{
		Art_Registration aR7 = new Art_Registration();
		int id1 = 0, course1 = 0;
		for(id1 = aR7.inputID(),course1 = aR7.inputCourse();id1 != 0;id1 = aR7.inputID(),course1 = aR7.inputCourse()){
			aR7.setID(id1);
			aR7.setClassNum(course1);
			System.out.println(aR7.counter());
		}
		System.out.println(aR7.toString());
	}
	
}
/*
Input ID code: 18
Input course code: S
ID  Preferred Class
18  Sculpture
Input ID code: 24
Input course code: S
ID  Preferred Class
24  Sculpture
Input ID code: 21
Input course code: C
ID  Preferred Class
21  Calligraphy
Input ID code: 19
Input course code: w
ID  Preferred Class
19  Weaving
Input ID code: 32
Input course code: b
ID  Preferred Class
32  Batik
Input ID code: 20
Input course code: s
ID  Preferred Class
20  Sculpture
Input ID code: 10
Input course code: s
ID  Preferred Class
10  Sculpture
Input ID code: 11
Input course code: c
ID  Preferred Class
11  Calligraphy
Input ID code: 12
Input course code: c
ID  Preferred Class
12  Calligraphy
Input ID code: 35
Input course code: R
Invalid course code
Input course code: b  <------- batik class code was used as the loop will not continue without a valid course number
ID  Preferred Class
35  Batik
Input ID code: Y
ID must be a non-negative integer
Input ID code: M
ID must be a non-negative integer
Input ID code: W
ID must be a non-negative integer
Input ID code: 38
Input course code: W
ID  Preferred Class
38  Weaving
Input ID code: 40
Input course code: B
ID  Preferred Class
40  Batik
Input ID code: 19
Input course code: s
ID  Preferred Class
19  Sculpture
Input ID code: 98
Input course code: s
ID  Preferred Class
98  Sculpture
Input ID code: 87
Input course code: c
ID  Preferred Class
87  Calligraphy
Input ID code: 18
Input course code: w
ID  Preferred Class
18  Weaving
Input ID code: 51
Input course code: s
ID  Preferred Class
51  Sculpture
Input ID code: 52
Input course code: w
ID  Preferred Class
52  Weaving
Input ID code: 67
Input course code: s
ID  Preferred Class
67  Sculpture
Input ID code: 15
Input course code: c
ID  Preferred Class
15  Calligraphy
Input ID code: 44
Input course code: r
Invalid course code
Input course code: C
ID  Preferred Class
44  Calligraphy
Input ID code: 48
Input course code: S
ID  Preferred Class
48  Sculpture
Input ID code: 0
Input course code: w
Class Name:  Number of Students:  Sections:
Batik        3                     1
Calligraphy  6                     2
Painting     Class will not be offered due to lack of requests.
Sculpture    9                     3
Weaving      4                     1
*/
