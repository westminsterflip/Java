import java.io.*;
import java.util.*;

public class maze_2 {
	char[][] maze;
	int row,x;
	int[] cat = {0,0};
	int[] mouse = {0,0};
	ArrayList<int[]> cats = new ArrayList<int[]>();
	ArrayList<Integer[]> dead = new ArrayList<Integer[]>();
	ArrayList<Integer[]> path = new ArrayList<Integer[]>();
	String dun;
	
	public maze_2(){}
	
	public maze_2(String in,int z, int y) throws IOException{
		getMaz(in,z,y);
		if(mazer.debug)
			System.out.println(toString());
		//if(mazer.debug)
			//System.out.println(toString());
		mazerize();
		if(mazer.debug)
			System.out.println(toString());
		cleaner();
		if(mazer.debug)
			System.out.println(toString());
		//polisher();
		//System.out.println(toString());
		cats.clear();
	}
	
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
					//cat[0]=c;
					//cat[1]=y;
					cats.add(new int[]{c,y});
				}else if(maze[c][y]=='M'){
					mouse[0]=c;
					mouse[1]=y;
				}
			}
		}
	}
	
	public void getMaz(String h,int z,int y)throws IOException{
		String tmp=h;
		int wid=z;
		int lng = y;
		maze = new char[wid][lng];
		if(mazer.debug)
			System.out.println("w" + wid + " l" + lng);
		row = lng;
		x=wid;
		int num=0;
		for(int e=0;e<lng;e++){
			for(int g=0;g<wid;g++){
				maze[g][e]=tmp.charAt(num);
				num++;
			}
		}
		for(int f=0;f<row;f++){
			for(int c=0;c<x;c++){
				if(maze[c][f]=='C'){
					//cat[0]=c;
					//cat[1]=f;
					cats.add(new int[]{c,f});
					System.out.println(c + " " + f);
				}else if(maze[c][f]=='M'){
					mouse[0]=c;
					mouse[1]=f;
				}
			}
		}
		//System.out.println(toString());
	}
	
	public boolean canDown(int z, int y){
		boolean cand;
		try{
			cand = maze[z][y+1]==' '||maze[z][y+1]=='O';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canUp(int z, int y){
		boolean cand;
		try{
			cand = maze[z][y-1]==' '||maze[z][y-1]=='O';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canLeft(int z, int y){
		boolean cand;
		try{
			cand = maze[z-1][y]==' '||maze[z-1][y]=='O';
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canRight(int z, int y){
		boolean cand;
		try{
			cand = maze[z+1][y]==' '||maze[z+1][y]=='O';
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
	
	public void filler(int w){//System.out.println("W        is            :       " + w);
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				//System.out.println(c + " " + y);
				//System.out.println(toString());
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1;//=c+1==cats.get(0)[0]&&y==cat[1]||c-1==cats.get(0)[0]&&y==cats.get(0)[1]||y+1==cats.get(0)[1]&&c==cats.get(0)[0]||y-1==cats.get(0)[1]&&c==cats.get(0)[0];
					//for(int w = 1;w<cats.size();w++){
						d1=c+1==cats.get(w)[0]&&y==cats.get(w)[1]||c-1==cats.get(w)[0]&&y==cats.get(w)[1]||y+1==cats.get(w)[1]&&c==cats.get(w)[0]||y-1==cats.get(w)[1]&&c==cats.get(w)[0];
					//}
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
						if(mazer.debug)
							System.out.println(d1 + " " + d2);
						if(d1&&d2){if(mazer.debug)
							System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							if(mazer.debug)
								System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
							//System.out.println(toString());
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//if(mazer.debug)
							//System.out.println("wontkd");
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
						//System.out.println(toString());
					}
				}
			}	
		}
		for(int y=row-1;y>=0;y--){
			for(int c=x-1;c>=0;c--){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1;//=c+1==cats.get(0)[0]&&y==cat[1]||c-1==cats.get(0)[0]&&y==cats.get(0)[1]||y+1==cats.get(0)[1]&&c==cats.get(0)[0]||y-1==cats.get(0)[1]&&c==cats.get(0)[0];
					//for(int w = 1;w<cats.size();w++){
						d1=c+1==cats.get(w)[0]&&y==cats.get(w)[1]||c-1==cats.get(w)[0]&&y==cats.get(w)[1]||y+1==cats.get(w)[1]&&c==cats.get(w)[0]||y-1==cats.get(w)[1]&&c==cats.get(w)[0];
					//}
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
						if(mazer.debug)
							System.out.println(d1 + " " + d2);
						if(d1&&d2){}
						else{
						if(d3&&!(d||u||l||r)){
							if(mazer.debug)
								System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						if(mazer.debug)
							System.out.println(toString());
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
		for(int y=row-1;y>=0;y--){
			for(int c=0;c<x;c++){
				if(mazer.debug)
					System.out.println(toString());
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1;//=c+1==cats.get(0)[0]&&y==cat[1]||c-1==cats.get(0)[0]&&y==cats.get(0)[1]||y+1==cats.get(0)[1]&&c==cats.get(0)[0]||y-1==cats.get(0)[1]&&c==cats.get(0)[0];
					//for(int w = 1;w<cats.size();w++){
						d1=c+1==cats.get(w)[0]&&y==cats.get(w)[1]||c-1==cats.get(w)[0]&&y==cats.get(w)[1]||y+1==cats.get(w)[1]&&c==cats.get(w)[0]||y-1==cats.get(w)[1]&&c==cats.get(w)[0];
					//}
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
						if(mazer.debug)
							System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							if(mazer.debug)
								System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						if(mazer.debug)
							System.out.println(toString());
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
					boolean d1;//=c+1==cats.get(0)[0]&&y==cat[1]||c-1==cats.get(0)[0]&&y==cats.get(0)[1]||y+1==cats.get(0)[1]&&c==cats.get(0)[0]||y-1==cats.get(0)[1]&&c==cats.get(0)[0];
					//for(int w = 1;w<cats.size();w++){
						d1=c+1==cats.get(w)[0]&&y==cats.get(w)[1]||c-1==cats.get(w)[0]&&y==cats.get(w)[1]||y+1==cats.get(w)[1]&&c==cats.get(w)[0]||y-1==cats.get(w)[1]&&c==cats.get(w)[0];
					//}
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
						if(mazer.debug)
							System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							if(mazer.debug)
								System.out.println(toString());
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
	
	public void tracer(int z,int y, int dssd){
		//System.out.println("started tracing");
		boolean d = canDown(z,y),u=canUp(z,y),l=canLeft(z,y),r=canRight(z,y);
		if(z+1==mouse[0]&&y==mouse[1]||z-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&z==mouse[0]||y-1==mouse[1]&&z==mouse[0]){
			System.out.println('\r');
		}else if(canTowards(z,y)!=0){
			switch (canTowards(z,y)){
				case 1: maze[z][y-1] = '+';
				//System.out.print("up t");
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,dssd);
						break;
				case 2: maze[z][y+1] = '+';
				//System.out.print("down t");
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,dssd);
						break;
				case 3: maze[z-1][y] = '+';
				//System.out.print("left t");
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,dssd);
						break;
				case 4: maze[z+1][y] = '+';
				//System.out.println("right t");
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,dssd);
						break;
			}
		}else if(d){
			maze[z][y+1] = '+';
			//System.out.print("down");
			path.add(new Integer[]{z,y+1});
			tracer(z,y+1,dssd);
		}else if(u){
			maze[z][y-1] = '+';
			//System.out.print("up");
			path.add(new Integer[]{z,y-1});
			tracer(z,y-1,dssd);
		}else if(l){
			maze[z-1][y] = '+';
			//System.out.print("left");
			path.add(new Integer[]{z-1,y});
			tracer(z-1,y,dssd);
		}else if(r){
			maze[z+1][y] = '+';
			//System.out.print("right");
			path.add(new Integer[]{z+1,y});
			tracer(z+1,y,dssd);
		}else if(!path.isEmpty()){
			maze[z][y] = 'f';
			dead.add(new Integer[]{z,y});
			//System.out.println(toString());
			altclean();
			//System.out.println("filled");
			filler(dssd);
			//System.out.println(toString());
			tracer(z,y,dssd);
		}else{
			//System.out.println('\r' + "No Path");
		}
		//System.out.println(toString());
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
	
	public char[][] gitDun(){
		return maze;
	}
	
	public void cleaner(){
		for(Integer[] i:dead){
			maze[i[0]][i[1]] = ' ';
		}
		for(Integer[] i:path){
			maze[i[0]][i[1]] = 'O';
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
		for(int l=0;l<cats.size();l++){
			//System.out.println(cats.get(l)[0]+ " " + cats.get(l)[1]);
			filler(l);
			filler(l);
			tracer(cats.get(l)[0],cats.get(l)[1],l);
			cleaner();
			path.clear();
			dead.clear();
		}
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
		//System.out.println(toString());
		mazerize();
		//System.out.println(toString());
		cleaner();
		//System.out.println(toString());
		//polisher();
		System.out.println(toString());
		cats.clear();
	}
	
	public static void main(String[] g) throws IOException{
		maze_2 m = new maze_2();
		m.run();
	}
}
