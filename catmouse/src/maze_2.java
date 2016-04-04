import java.io.*;
import java.util.*;

public class maze_2 {
	char[][] maze;
	int row,x;
	int[] cat = {0,0},mouse = {0,0};
	ArrayList<Integer[]> dead = new ArrayList<Integer[]>();
	ArrayList<Integer[]> path = new ArrayList<Integer[]>();
	
	public maze_2(){}
	
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
			//System.out.println(line);
			tmp+=line;
			line=b1.readLine();
		}
		int lng = tmp.length()/wid;
		maze = new char[wid][lng];
		row = lng;
		x=wid;
		int num=0;
		for(int e=0;e<lng;e++){
			for(int g=0;g<wid;g++){
				maze[g][e]=tmp.charAt(num);
				num++;
			}
		}
		b1.close();
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				if(maze[c][y]=='C'){
					cat[0]=c;
					cat[1]=y;
				}else if(maze[c][y]=='M'){
					mouse[0]=c;
					mouse[1]=y;
				}
			}
		}
	}
	
	public boolean canDown(int z, int y){
		boolean cand;
		try{
			cand = maze[z][y+1]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canUp(int z, int y){
		boolean cand;
		try{
			cand = maze[z][y-1]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canLeft(int z, int y){
		boolean cand;
		try{
			cand = maze[z-1][y]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canRight(int z, int y){
		boolean cand;
		try{
			cand = maze[z+1][y]==' ';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public int canTowards(int z, int y){
		int hor=mouse[0]-z;
		int ver=mouse[1]-y;
		if(Math.abs(hor)<Math.abs(ver)){
			if(ver<0){
				if(canUp(z,y))
					return 1;
				else
					return 0;
			}else if(ver>0){
				if(canDown(z,y))
					return 2;
				else
					return 0;
			}else{
				return 0;
			}
		}else{
			if(hor<0){
				if(canLeft(z,y))
					return 3;
				else 
					return 0;
			}else if(hor>0){
				if(canRight(z,y))
					return 4;
				else 
					return 0;
			}else
				return 0;
		}
	}
	
	public void filler(){
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=maze[c+1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c-1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y+1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y-1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//System.out.println(toString());
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
		for(int y=row-1;y>=0;y--){
			for(int c=x-1;c>=0;c--){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=maze[c+1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c-1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y+1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y-1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//System.out.println(toString());
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
		for(int y=row-1;y>=0;y--){
			for(int c=0;c<x;c++){
				//System.out.println(toString());
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=maze[c+1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c-1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y+1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y-1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//System.out.println(toString());
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
		for(int y=0;y<row;y++){
			for(int c=x-1;c>=0;c--){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=maze[c+1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c-1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y+1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y-1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
	}
	
	public void tracer(int z,int y){
		boolean d = canDown(z,y),u=canUp(z,y),l=canLeft(z,y),r=canRight(z,y);
		if(z+1==mouse[0]&&y==mouse[1]||z-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&z==mouse[0]||y-1==mouse[1]&&z==mouse[0]){
			System.out.println('\r');
		}else if(canTowards(z,y)!=0){
			switch (canTowards(z,y)){
				case 1: maze[z][y-1] = '+';
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1);
						break;
				case 2: maze[z][y+1] = '+';
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1);
						break;
				case 3: maze[z-1][y] = '+';
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y);
						break;
				case 4: maze[z+1][y] = '+';
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y);
						break;
			}
		}else if(d){
			maze[z][y+1] = '+';
			path.add(new Integer[]{z,y+1});
			tracer(z,y+1);
		}else if(u){
			maze[z][y-1] = '+';
			path.add(new Integer[]{z,y-1});
			tracer(z,y-1);
		}else if(l){
			maze[z-1][y] = '+';
			path.add(new Integer[]{z-1,y});
			tracer(z-1,y);
		}else if(r){
			maze[z+1][y] = '+';
			path.add(new Integer[]{z+1,y});
			tracer(z+1,y);
		}else if(!path.isEmpty()){
			maze[z][y] = 'f';
			dead.add(new Integer[]{z,y});
			//System.out.println(toString());
			altclean();
			filler();
			//System.out.println("filled");
			//System.out.println(toString());
			mazerize();
		}else{
			System.out.println('\r' + "No Path");
		}
	}
	
	public String toString(){
		String out="";
		//out+='\r';
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				out+=maze[c][y];
			}
			out+='\r';
		}
		return out;
	}
	
	public void cleaner(){
		for(Integer[] i:dead){
			maze[i[0]][i[1]] = ' ';
		}
	}
	
	public void altclean(){
		for(Integer[] i:path){
			if(maze[i[0]][i[1]]!='f')
				maze[i[0]][i[1]] = ' ';
		}
		path.clear();
	}
	
	public void mazerize(){
		tracer(cat[0],cat[1]);
	}
	
	/*public void polisher(){
		boolean d1=false;
		for(Integer[] i:path){
			int z = i[0],y = i[1];
			try{
				d1=maze[z][y-1]=='+'&&maze[z+1][y-1]=='+'&&maze[z+1][y]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			try{
				d1=d1||maze[z+1][y]=='+'&&maze[z+1][y-1]=='+'&&maze[z][y-1]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			try{
				d1=d1||maze[z+1][y]=='+'&&maze[z+1][y+1]=='+'&&maze[z][y+1]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			try{
				d1=d1||maze[z][y+1]=='+'&&maze[z+1][y+1]=='+'&&maze[z+1][y]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			try{
				d1=d1||maze[z][y+1]=='+'&&maze[z-1][y+1]=='+'&&maze[z-1][y]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			try{
				d1=d1||maze[z-1][y]=='+'&&maze[z-1][y+1]=='+'&&maze[z][y+1]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			try{
				d1=d1||maze[z-1][y]=='+'&&maze[z-1][y-1]=='+'&&maze[z][y-1]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			try{
				d1=d1||maze[z][y-1]=='+'&&maze[z-1][y-1]=='+'&&maze[z-1][y]=='+';
			}catch(ArrayIndexOutOfBoundsException r1){}
			if(d1){
				dead.add(i);
			}
			cleaner();
		}
	}*/
	
	public void run() throws IOException{
		getMaze();
		System.out.println(toString());
		filler();
		filler();
		//System.out.println(toString());
		mazerize();
		//System.out.println(toString());
		cleaner();
		//System.out.println(toString());
		//polisher();
		System.out.println(toString());
	}
	
	public static void main(String[] g) throws IOException{
		maze_2 m = new maze_2();
		m.run();
	}
}
