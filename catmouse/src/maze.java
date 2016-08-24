import java.io.*;
import java.util.*;

public class maze {
	char[][] maze;
	char[][] maz1;
	int row,x;
	int[] cat = {0,0};
	
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
		maz1 = new char[wid][lng];
		row = lng;
		x=wid;
		int num=0;
		for(int e=0;e<lng;e++){
			//System.out.println("first loop");
			for(int g=0;g<wid;g++){
				maze[g][e]=tmp.charAt(num);
				maz1[g][e]=tmp.charAt(num);
				num++;
			}
		}
		b1.close();
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				if(maze[c][y]=='C'){
					cat[0]=c;
					cat[1]=y;
				}
			}
		}
	}
	
	public boolean canDown(int z, int y){
		boolean cand;
		try{
			cand = maz1[z][y+1]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		
		return cand;
	}
	
	public boolean canUp(int z, int y){
		boolean cand;
		try{
			cand = maz1[z][y-1]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		
		return cand;
	}
	
	public boolean canLeft(int z, int y){
		boolean cand;
		try{
			cand = maz1[z-1][y]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		
		return cand;
	}
	
	public boolean canRight(int z, int y){
		boolean cand;
		try{
			cand = maz1[z+1][y]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		
		return cand;
	}
	
	public void filler(){
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maz1[c][y]==' ')
					if(maze[c+1][y]=='M'||maze[c-1][y]=='M'||maze[c][y+1]=='M'||maze[c][y-1]=='M'){}
					else
					if(maze[c+1][y]=='C'||maze[c-1][y]=='C'||maze[c][y+1]=='C'||maze[c][y-1]=='C'){}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//System.out.println(toString());
						//System.out.println(c + " " + y);
						//System.out.println(d + " " + u + " " + l + " " + r);
						maz1[c][y]='#';
						
					}
			}
			
		}
	}
	
	public void tracer(int z,int y){
		//String out = "";
		//System.out.println(out);
		boolean d = canDown(z,y),u=canUp(z,y),l=canLeft(z,y),r=canRight(z,y);
		//System.out.println(d + " " + u + " " + l + " " + r);
		if(maze[z+1][y]=='M'||maze[z-1][y]=='M'||maze[z][y+1]=='M'||maze[z][y-1]=='M'){	
		}else if(d){
			maz1[z][y+1] = 'O';
			maze[z][y+1] = 'O';
			tracer(z,y+1);
		}else if(u){
			maz1[z][y-1] = 'O';
			maze[z][y-1] = 'O';
			tracer(z,y-1);
		}else if(l){
			maz1[z-1][y] = 'O';
			maze[z-1][y] = 'O';
			tracer(z-1,y);
		}else if(r){
			maz1[z+1][y] = 'O';
			maze[z+1][y] = 'O';
			tracer(z+1,y);
		}else //if(d==false&&d==u&&u==l&&r==l){
			//maz1=maze.clone();
			//System.out.println(z+ " " + y);
		//}else 
			System.out.println("Oops");
	}
	
	public void pre(){
		String out = "";
		for(int y=0;y<row;y++){
			//System.out.println("Looping");
			for(int c=0;c<x;c++){
				out+=maze[c][y];
			}
			out+='\r';
		}
		System.out.println(out);
	}
	
	public String toString(){
		/*for(int h=cat[1];h<row;h++){
			boolean[] i = {canDown(cat[0],h),canUp(cat[0],h),canLeft(cat[0],h),canRight(cat[0],h)};
			for(boolean tmp1:i){
				System.out.println(tmp1);
			}
		}*/
		//System.out.println("making string");
		//System.out.println(x + " & " + row);
		String out="";
		out+='\r';
		for(int y=0;y<row;y++){
			//System.out.println("Looping");
			for(int c=0;c<x;c++){
				out+=maze[c][y];
			}
			out+='\r';
		}
		return out;
	}
	
	public void run() throws IOException{
		getMaze();
		pre();
		//System.out.println(cat[0]+" " + cat[1]);
		filler();
		//System.out.println(toString());
		tracer(cat[0],cat[1]);
		System.out.println(toString());
	}
	
	public static void main(String[] g) throws IOException{
		maze m = new maze();
		m.run();
	}
	
}
