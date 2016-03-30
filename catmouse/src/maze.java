import java.io.*;

public class maze {
	char[][] maze;
	char[][] mazesl;
	int row,x;
	
	public maze(){}
	
	public void getMaze()throws IOException{
		String fle;
		InputStreamReader i = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(i);
		System.out.print("Maze: ");
		fle = b.readLine();
		FileReader f = new FileReader("maze" +fle);
		BufferedReader b1 = new BufferedReader(f);
		String line=b1.readLine();
		int wid = line.length();
		String tmp ="";
		while(line!=null){
		//	System.out.println("reading");
			tmp+=line;
		//	System.out.println("read1");
			line=b1.readLine();
			//System.out.println("read");
		}
		//System.out.println("Done reading");
		//System.out.println(tmp);
		int lng = tmp.length()/wid;
		maze = new char[wid][lng];
		row = lng;
		x=wid;
		int num=0;
		for(int e=0;e<lng;e++){
			//System.out.println("first loop");
			for(int g=0;g<wid;g++){
				maze[g][e]=tmp.charAt(num);
				num++;
			}
		}
		b1.close();
		mazesl = maze.clone();
	}
	
	public void goDown(){
		
	}
	
	public String toString(){
		//System.out.println("making string");
		//System.out.println(x + " & " + row);
		String out="";
		for(int y=0;y<row;y++){
			//System.out.println("Looping");
			for(int c=0;c<x;c++){
				out+=maze[c][y];
			}
			out+='\r';
		}
		return out;
	}
	
	public static void main(String[] g) throws IOException{
		maze m = new maze();
		m.getMaze();
		System.out.println(m.toString());
	}
	
}
