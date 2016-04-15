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
	ArrayList<Integer[]> pod = new ArrayList<Integer[]>();
	
	public maze_2(){}
	
	public maze_2(String in,int z, int y) throws IOException{
		getMaz(in,z,y);
		if(mazer.debug)
			System.out.println(toString());
		mazerize();
		if(mazer.debug)
			System.out.println(toString());
		cleaner();
		if(mazer.debug)
			System.out.println(toString());
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
						cats.add(new int[]{c,y});
					}else if(maze[c][y]=='M'){
						mice.add(new int[]{c,y});
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
					cats.add(new int[]{c,f});
				}else if(maze[c][f]=='M'){
					mice.add(new int[]{c,f});
				}
			}
		}
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
	
	public int canTowards(int z, int y, int f, int mn){
		int hor=mice.get(mn)[0]-z;
		int ver=mice.get(mn)[1]-y;
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
	
	public void tracer(int z,int y, int mx, int my, int dssd, int mn) throws IOException{
		int hor=mx-z;
		int ver=my-y;
		int hor1=z-cats.get(dssd)[0];
		int ver1=y-cats.get(dssd)[1];
		boolean d = canDown(z,y),u=canUp(z,y),l=canLeft(z,y),r=canRight(z,y);
		if(d&&u||d&&r||d&&l||u&&l||u&&r||l&&r){
			pod.add(new Integer[]{z,y});
		}
		if(z+1==mx&&y==my||z-1==mx&&y==my||y+1==my&&z==mx||y-1==my&&z==mx){
		}else if(canTowards(z,y,1,mn)!=0||canTowards(z,y,0,mn)!=0){
			if(Math.abs(hor)<=Math.abs(ver)){
				switch (canTowards(z,y,0,mn)){
				case 1: maze[z][y-1] = '+';
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd,mn);
						break;
				case 2: maze[z][y+1] = '+';
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd,mn);
						break;
				case 3: maze[z-1][y] = '+';
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd,mn);
						break;
				case 4: maze[z+1][y] = '+';
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd,mn);
						break;
				case 0:
					switch (canTowards(z,y,1,mn)){
					case 1: maze[z][y-1] = '+';
							path.add(new Integer[]{z,y-1});
							tracer(z,y-1,mx,my,dssd,mn);
							break;
					case 2: maze[z][y+1] = '+';
							path.add(new Integer[]{z,y+1});
							tracer(z,y+1,mx,my,dssd,mn);
							break;
					case 3: maze[z-1][y] = '+';
							path.add(new Integer[]{z-1,y});
							tracer(z-1,y,mx,my,dssd,mn);
							break;
					case 4: maze[z+1][y] = '+';
							path.add(new Integer[]{z+1,y});
							tracer(z+1,y,mx,my,dssd,mn);
							break;
					}
					break;
				}
			}else if(Math.abs(hor)>Math.abs(ver)){
				switch (canTowards(z,y,1,mn)){
				case 1: maze[z][y-1] = '+';
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd,mn);
						break;
				case 2: maze[z][y+1] = '+';
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd,mn);
						break;
				case 3: maze[z-1][y] = '+';
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd,mn);
						break;
				case 4: maze[z+1][y] = '+';
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd,mn);
						break;
				case 0:
					switch (canTowards(z,y,0,mn)){
					case 1: maze[z][y-1] = '+';
							path.add(new Integer[]{z,y-1});
							tracer(z,y-1,mx,my,dssd,mn);
							break;
					case 2: maze[z][y+1] = '+';
							path.add(new Integer[]{z,y+1});
							tracer(z,y+1,mx,my,dssd,mn);
							break;
					case 3: maze[z-1][y] = '+';
							path.add(new Integer[]{z-1,y});
							tracer(z-1,y,mx,my,dssd,mn);
							break;
					case 4: maze[z+1][y] = '+';
							path.add(new Integer[]{z+1,y});
							tracer(z+1,y,mx,my,dssd,mn);
							break;
					}
					break;
				}
			}
		}else if(canAway(z,y,1,dssd)!=0||canAway(z,y,0,dssd)!=0){
			if(Math.abs(hor1)>=Math.abs(ver1)){
				switch (canAway(z,y,0,dssd)){
				case 1: maze[z][y-1] = '+';
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd,mn);
						break;
				case 2: maze[z][y+1] = '+';
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd,mn);
						break;
				case 3: maze[z-1][y] = '+';
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd,mn);
						break;
				case 4: maze[z+1][y] = '+';
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd,mn);
						break;
				case 0:
					switch (canAway(z,y,1,dssd)){
					case 1: maze[z][y-1] = '+';
							path.add(new Integer[]{z,y-1});
							tracer(z,y-1,mx,my,dssd,mn);
							break;
					case 2: maze[z][y+1] = '+';
							path.add(new Integer[]{z,y+1});
							tracer(z,y+1,mx,my,dssd,mn);
							break;
					case 3: maze[z-1][y] = '+';
							path.add(new Integer[]{z-1,y});
							tracer(z-1,y,mx,my,dssd,mn);
							break;
					case 4: maze[z+1][y] = '+';
							path.add(new Integer[]{z+1,y});
							tracer(z+1,y,mx,my,dssd,mn);
							break;
					}
					break;
			}
		}else if(Math.abs(hor1)<Math.abs(ver1)){
			switch (canAway(z,y,1,dssd)){
			case 1: maze[z][y-1] = '+';
					path.add(new Integer[]{z,y-1});
					tracer(z,y-1,mx,my,dssd,mn);
					break;
			case 2: maze[z][y+1] = '+';
					path.add(new Integer[]{z,y+1});
					tracer(z,y+1,mx,my,dssd,mn);
					break;
			case 3: maze[z-1][y] = '+';
					path.add(new Integer[]{z-1,y});
					tracer(z-1,y,mx,my,dssd,mn);
					break;
			case 4: maze[z+1][y] = '+';
					path.add(new Integer[]{z+1,y});
					tracer(z+1,y,mx,my,dssd,mn);
					break;
			case 0:
				switch (canAway(z,y,0,dssd)){
				case 1: maze[z][y-1] = '+';
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1,mx,my,dssd,mn);
						break;
				case 2: maze[z][y+1] = '+';
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1,mx,my,dssd,mn);
						break;
				case 3: maze[z-1][y] = '+';
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y,mx,my,dssd,mn);
						break;
				case 4: maze[z+1][y] = '+';
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y,mx,my,dssd,mn);
						break;
				}
				break;
			}
		}
	}else if(d){
			maze[z][y+1] = '+';
			path.add(new Integer[]{z,y+1});
			tracer(z,y+1,mx,my,dssd,mn);
		}else if(u){
			maze[z][y-1] = '+';
			path.add(new Integer[]{z,y-1});
			tracer(z,y-1,mx,my,dssd,mn);
		}else if(l){
			maze[z-1][y] = '+';
			path.add(new Integer[]{z-1,y});
			tracer(z-1,y,mx,my,dssd,mn);
		}else if(r){
			maze[z+1][y] = '+';
			path.add(new Integer[]{z+1,y});
			tracer(z+1,y,mx,my,dssd,mn);
		}else if(!path.isEmpty()){
			Integer[] pd = pod.get(pod.size()-1), pth = path.get(path.size()-1);
			int o1=pd[0], o2 = pd[1], o3 =pth[0], o4 = pth[1];
			if(o1!=o3||o2!=o4){
				for(int tmp = find(path,pod.get(pod.size()-1))+1;tmp<path.size();tmp++){
					maze[path.get(tmp)[0]][path.get(tmp)[1]]='f';
					dead.add(new Integer[]{path.get(tmp)[0],path.get(tmp)[1]});
				}
				for(Integer[] tmp:dead){
					int t = find(path,tmp);
					if(t!=-1)
						path.remove(t);
				}
				int tmpx = pod.get(pod.size()-1)[0];
				int tmpy = pod.get(pod.size()-1)[1];
				tracer(tmpx,tmpy,mx,my,dssd,mn);
			}else{
				maze[z][y]='f';
				dead.add(new Integer[]{z,y});
				pod.remove(pod.size()-1);
				path.remove(path.size()-1);
				if(pod.isEmpty()){
					System.out.println("No Path");
				}else{
					for(int tmp = find(path,pod.get(pod.size()-1))+1;tmp<path.size();tmp++){
						maze[path.get(tmp)[0]][path.get(tmp)[1]]='f';
						dead.add(new Integer[]{path.get(tmp)[0],path.get(tmp)[1]});
					}
					int tmpx = pod.get(pod.size()-1)[0];
					int tmpy = pod.get(pod.size()-1)[1];
					tracer(tmpx,tmpy,mx,my,dssd,mn);
				}
			}
		}else{
			System.out.println('\r' + "No Path");
		}
	}
	
	public String toString(){
		String out="";
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
	
	public void cleaner(){
		for(Integer[] i:dead){
			maze[i[0]][i[1]] = ' ';
		}
		for(Integer[] i:path){
			maze[i[0]][i[1]] = 'O';
		}
	}
	
	public void mazerize() throws IOException{
		for(int l=0;l<cats.size();l++){
			for(int j=0;j<mice.size();j++){
				path.add(new Integer[]{cats.get(l)[0],cats.get(l)[1]});
				tracer(cats.get(l)[0],cats.get(l)[1],mice.get(j)[0],mice.get(j)[1],l,j);
				cleaner();
				path.clear();
				dead.clear();
				maze[cats.get(l)[0]][cats.get(l)[1]] = 'C';
			}
		}
	}
	
	public void run() throws IOException{
		getMaze();
		System.out.println(toString());
		mazerize();
		cleaner();
		System.out.println();
		System.out.println(toString());
		cats.clear();
	}
	
	public int find(ArrayList<Integer[]> o, Integer[] toby){
		int tm=-1;
		for(Integer[] ye:o){
			if(toby[0].equals(ye[0])&&toby[1].equals(ye[1]))
				tm=o.indexOf(ye);
		}
		return tm;
	}
	
	public static void main(String[] g) throws IOException{
		maze_2 m = new maze_2();
		m.run();
	}
}
