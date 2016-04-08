import java.io.*;
import java.util.*;

public class maze_2 {
	Character[][] maze;
	int row,x;
	int[] cat = {0,0};
	int[] mouse = {0,0};
	ArrayList<int[]> cats = new ArrayList<int[]>();
	ArrayList<int[]> mice = new ArrayList<int[]>();
	ArrayList<Integer[]> dead = new ArrayList<Integer[]>();
	ArrayList<Integer[]> path = new ArrayList<Integer[]>();
	ArrayList<Integer[]> empty = new ArrayList<Integer[]>();
	String dun;
	ArrayList<Integer[]> ded = new ArrayList<Integer[]>();
	
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
		try{
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
			maze = new Character[wid][lng];
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
						//mouse[0]=c;
						//mouse[1]=y;
						mice.add(new int[]{c,y});
					}else if(maze[c][y]==' '){
						empty.add(new Integer[]{c,y});
					}
				}
			}
		}
		catch(FileNotFoundException ddsdsdsds){
			System.out.println("Invalid Maze");
			getMaze();
		}
		
	}
	
	public void getMaz(String h,int z,int y)throws IOException{
		String tmp=h;
		int wid=z;
		int lng = y;
		maze = new Character[wid][lng];
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
					//System.out.println(c + " " + f);
				}else if(maze[c][f]=='M'){
					//mouse[0]=c;
					//mouse[1]=f;
					mice.add(new int[]{c,y});
				}else if(maze[c][y]==' '){
					empty.add(new Integer[]{c,y});
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
	
	public int canTowards(int z, int y, int f){
		int hor=mouse[0]-z;
		int ver=mouse[1]-y;
		if(f==0){
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
			}else if(hor<0){
				if(canLeft(z,y))
					return 3;
				else 
					return 0;
			}else if(hor>0){
				if(canRight(z,y))
					return 4;
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
			}else if(ver<0){
				if(canUp(z,y))
					return 1;
				else
					return 0;
			}else if(ver>0){
				if(canDown(z,y))
					return 2;
				else
					return 0;
			}else
				return 0;
		}
	}
	
	public int canAway(int z, int y, int f, int l){
		int hor=z-cats.get(l)[0];
		int ver=y-cats.get(l)[1];
		if(f==0){
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
			}else if(hor<0){
				if(canLeft(z,y))
					return 3;
				else 
					return 0;
			}else if(hor>0){
				if(canRight(z,y))
					return 4;
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
			}else if(ver<0){
				if(canUp(z,y))
					return 1;
				else
					return 0;
			}else if(ver>0){
				if(canDown(z,y))
					return 2;
				else
					return 0;
			}else
				return 0;
		}
	}
	
	public void filler2(int w, int mnum, int c, int y){//System.out.println("W        is            :       " + w)
					boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
					if(maze[c][y]==' '){
						boolean d1;//=c+1==cats.get(0)[0]&&y==cat[1]||c-1==cats.get(0)[0]&&y==cats.get(0)[1]||y+1==cats.get(0)[1]&&c==cats.get(0)[0]||y-1==cats.get(0)[1]&&c==cats.get(0)[0];
						//for(int w = 1;w<cats.size();w++){
							d1=c+1==cats.get(w)[0]&&y==cats.get(w)[1]||c-1==cats.get(w)[0]&&y==cats.get(w)[1]||y+1==cats.get(w)[1]&&c==cats.get(w)[0]||y-1==cats.get(w)[1]&&c==cats.get(w)[0];
						//}
						boolean d2;
							d2=c+1==mice.get(mnum)[0]&&y==mice.get(mnum)[1]||c-1==mice.get(mnum)[0]&&y==mice.get(mnum)[1]||y+1==mice.get(mnum)[1]&&c==mice.get(mnum)[0]||y-1==mice.get(mnum)[1]&&c==mice.get(mnum)[0];
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
								//empty.remove(empty.indexOf(inty));
								dead.add(new Integer[]{c,y});
								ded.add(new Integer[]{c,y});
								int g = find(c,y);
								empty.remove(g);
								//System.out.println(toString());
							}
							}
						}
						else
						if(!d&&!u&&!l){
							if(mazer.debug)
								System.out.println("wontkd");
							maze[c][y]='f';
							//empty.remove(empty.indexOf(inty));
							dead.add(new Integer[]{c,y});
							ded.add(new Integer[]{c,y});
							int g = find(c,y);
							empty.remove(g);
							//System.out.println(toString());
							filler2(w,mnum,c+1,y);
						}else if(!d&&!u&&!r){
							if(mazer.debug)
								System.out.println("wontkd");
							maze[c][y]='f';
							//empty.remove(empty.indexOf(inty));
							dead.add(new Integer[]{c,y});
							ded.add(new Integer[]{c,y});
							int g = find(c,y);
							empty.remove(g);
							//System.out.println(toString());
							filler2(w,mnum,c-1,y);
						}else if(!u&&!l&&!r){
							if(mazer.debug)
								System.out.println("wontkd");
							maze[c][y]='f';
							//empty.remove(empty.indexOf(inty));
							dead.add(new Integer[]{c,y});
							ded.add(new Integer[]{c,y});
							int g = find(c,y);
							empty.remove(g);
							filler2(w,mnum,c,y+1);
						}else if(!d&&!l&&!r){
							if(mazer.debug)
								System.out.println("wontkd");
							maze[c][y]='f';
							//empty.remove(empty.indexOf(inty));
							dead.add(new Integer[]{c,y});
							ded.add(new Integer[]{c,y});
							int g = find(c,y);
							empty.remove(g);
							//System.out.println(toString());
							filler2(w,mnum,c,y-1);
						}
					}			
	}
	
	public void filler(int w){//System.out.println("W        is            :       " + w);
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				//System.out.println(c + " " + y);
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
						if(mazer.debug)
							System.out.println("wontkd");
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
						//System.out.println(toString());
					}
				}
			}	
		}
	}
	
	public void tracer(int z,int y, int mx, int my, int dssd){
		int hor=mx-z;
		int ver=my-y;
		int hor1=z-cats.get(dssd)[0];
		int ver1=y-cats.get(dssd)[1];
		//System.out.println("started tracing");
		//System.out.println(toString());
		//System.out.println(hor + " " + ver);
		//System.out.println(hor1 + " " + ver1);
		boolean d = canDown(z,y),u=canUp(z,y),l=canLeft(z,y),r=canRight(z,y);
		if(z+1==mx&&y==my||z-1==mx&&y==my||y+1==my&&z==mx||y-1==my&&z==mx){
			System.out.println(toString());
			System.out.println();
			//System.out.println("Done" + '\r');
		}else if(canTowards(z,y,1)!=0||canTowards(z,y,0)!=0){
			if(Math.abs(hor)<=Math.abs(ver)){
				switch (canTowards(z,y,0)){
				case 1: maze[z][y-1] = '+';
				//System.out.print("up t");
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd);
						break;
				case 2: maze[z][y+1] = '+';
				//System.out.print("down t");
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd);
						break;
				case 3: maze[z-1][y] = '+';
				//System.out.print("left t");
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd);
						break;
				case 4: maze[z+1][y] = '+';
				//System.out.println("right t");
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd);
						break;
				case 0:
					switch (canTowards(z,y,1)){
					case 1: maze[z][y-1] = '+';
					//System.out.print("up t");
							path.add(new Integer[]{z,y-1});
							tracer(z,y-1,mx,my,dssd);
							break;
					case 2: maze[z][y+1] = '+';
					//System.out.print("down t");
							path.add(new Integer[]{z,y+1});
							tracer(z,y+1,mx,my,dssd);
							break;
					case 3: maze[z-1][y] = '+';
					//System.out.print("left t");
							path.add(new Integer[]{z-1,y});
							tracer(z-1,y,mx,my,dssd);
							break;
					case 4: maze[z+1][y] = '+';
					//System.out.println("right t");
							path.add(new Integer[]{z+1,y});
							tracer(z+1,y,mx,my,dssd);
							break;
					}
					break;
				}
			}else if(Math.abs(hor)>Math.abs(ver)){
				switch (canTowards(z,y,1)){
				case 1: maze[z][y-1] = '+';
				//System.out.print("up t");
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd);
						break;
				case 2: maze[z][y+1] = '+';
				//System.out.print("down t");
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd);
						break;
				case 3: maze[z-1][y] = '+';
				//System.out.print("left t");
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd);
						break;
				case 4: maze[z+1][y] = '+';
				//System.out.println("right t");
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd);
						break;
				case 0:
					switch (canTowards(z,y,0)){
					case 1: maze[z][y-1] = '+';
					//System.out.print("up t");
							path.add(new Integer[]{z,y-1});
							tracer(z,y-1,mx,my,dssd);
							break;
					case 2: maze[z][y+1] = '+';
					//System.out.print("down t");
							path.add(new Integer[]{z,y+1});
							tracer(z,y+1,mx,my,dssd);
							break;
					case 3: maze[z-1][y] = '+';
					//System.out.print("left t");
							path.add(new Integer[]{z-1,y});
							tracer(z-1,y,mx,my,dssd);
							break;
					case 4: maze[z+1][y] = '+';
					//System.out.println("right t");
							path.add(new Integer[]{z+1,y});
							tracer(z+1,y,mx,my,dssd);
							break;
					}
					break;
				}
			}
		}else if(canAway(z,y,1,dssd)!=0||canAway(z,y,0,dssd)!=0){
			if(Math.abs(hor1)>=Math.abs(ver1)){
				switch (canAway(z,y,0,dssd)){
				case 1: maze[z][y-1] = '+';
				//System.out.print("up t");
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd);
						break;
				case 2: maze[z][y+1] = '+';
				//System.out.print("down t");
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd);
						break;
				case 3: maze[z-1][y] = '+';
				//System.out.print("left t");
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd);
						break;
				case 4: maze[z+1][y] = '+';
				//System.out.println("right t");
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd);
						break;
				case 0:
					switch (canAway(z,y,1,dssd)){
					case 1: maze[z][y-1] = '+';
					//System.out.print("up t");
							path.add(new Integer[]{z,y-1});
							tracer(z,y-1,mx,my,dssd);
							break;
					case 2: maze[z][y+1] = '+';
					//System.out.print("down t");
							path.add(new Integer[]{z,y+1});
							tracer(z,y+1,mx,my,dssd);
							break;
					case 3: maze[z-1][y] = '+';
					//System.out.print("left t");
							path.add(new Integer[]{z-1,y});
							tracer(z-1,y,mx,my,dssd);
							break;
					case 4: maze[z+1][y] = '+';
					//System.out.println("right t");
							path.add(new Integer[]{z+1,y});
							tracer(z+1,y,mx,my,dssd);
							break;
					}
					break;
			}
		}else if(Math.abs(hor1)<Math.abs(ver1)){
			switch (canAway(z,y,1,dssd)){
			case 1: maze[z][y-1] = '+';
			//System.out.print("up t");
					path.add(new Integer[]{z,y-1});
					tracer(z,y-1,mx,my,dssd);
					break;
			case 2: maze[z][y+1] = '+';
			//System.out.print("down t");
					path.add(new Integer[]{z,y+1});
					tracer(z,y+1,mx,my,dssd);
					break;
			case 3: maze[z-1][y] = '+';
			//System.out.print("left t");
					path.add(new Integer[]{z-1,y});
					tracer(z-1,y,mx,my,dssd);
					break;
			case 4: maze[z+1][y] = '+';
			//System.out.println("right t");
					path.add(new Integer[]{z+1,y});
					tracer(z+1,y,mx,my,dssd);
					break;
			case 0:
				switch (canAway(z,y,0,dssd)){
				case 1: maze[z][y-1] = '+';
				//System.out.print("up t");
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd);
						break;
				case 2: maze[z][y+1] = '+';
				//System.out.print("down t");
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd);
						break;
				case 3: maze[z-1][y] = '+';
				//System.out.print("left t");
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd);
						break;
				case 4: maze[z+1][y] = '+';
				//System.out.println("right t");
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd);
						break;
				}
				break;
			}
		}
	}else if(d){
			maze[z][y+1] = '+';
			//System.out.print("down");
			path.add(new Integer[]{z,y+1});
			tracer(z,y+1,mx,my,dssd);
		}else if(u){
			maze[z][y-1] = '+';
			//System.out.print("up");
			path.add(new Integer[]{z,y-1});
			tracer(z,y-1,mx,my,dssd);
		}else if(l){
			maze[z-1][y] = '+';
			//System.out.print("left");
			path.add(new Integer[]{z-1,y});
			tracer(z-1,y,mx,my,dssd);
		}else if(r){
			maze[z+1][y] = '+';
			//System.out.print("right");
			path.add(new Integer[]{z+1,y});
			tracer(z+1,y,mx,my,dssd);
		}else if(!path.isEmpty()){
			maze[z][y] = 'f';
			path.remove(path.size()-1);
			dead.add(new Integer[]{z,y});
			//System.out.println(toString());
			altclean();
			//System.out.println("filled");
			filler(dssd);
			//System.out.println(toString());
			tracer(cats.get(dssd)[0],cats.get(dssd)[1],mx,my,dssd);
		}else{
			System.out.println('\r' + "No Path");
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
	
	public Character[][] gitDun(){
		return maze;
	}
	
	public int[] find(Character c){
		int[] f = {-1,-1};
		for(int s = 0;s<row;s++){
			for(int d = 0;d<x;d++){
				if(c==maze[d][s]){
					f = new int[]{d,s};
				}
			}
		}
		return f;
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
	
	public void method(int l,int j){
		try{
			ded.clear();
			for(Integer[] inty:empty){
				//System.out.println(inty[0] + " " + inty[1]);
				//System.out.println(maze[inty[0]][inty[1]]);
				filler2(l,j,inty[0],inty[1]);
				//System.out.println(toString());
				//System.out.println(empty.size());
			}
		}
		catch(ConcurrentModificationException dhd){
			//System.out.println("Stop breaking things");
			method(l,j);
		}
	}
	
	public void mazerize(){
		//System.out.println(mice.size());
		for(int l=0;l<cats.size();l++){
			//System.out.println(cats.get(l)[0]+ " " + cats.get(l)[1]);
			for(int j=0;j<mice.size();j++){
				do{
					method(l,j);
				}
				while(!ded.isEmpty());
				//System.out.println(toString());
				if(!empty.isEmpty())
					tracer(cats.get(l)[0],cats.get(l)[1],mice.get(l)[0],mice.get(l)[1],l);
				cleaner();
				path.clear();
				dead.clear();
			}
		}
	}
	
	public int find(int d, int t){
		int g=-1;
		for(Integer[] tmp:empty){
			if(tmp[0]==d&&tmp[1]==t){
				g=empty.indexOf(tmp);
			}
		}
		return g;
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
		System.out.println();
		System.out.println(toString());
		cats.clear();
	}
	
	public static void main(String[] g) throws IOException{
		maze_2 m = new maze_2();
		m.run();
	}
}
