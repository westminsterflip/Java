import java.io.*;
import java.util.*;
public class magic {
	int[][] sqr;
	int x;
	
	public magic() throws IOException{
		in();
		decider();
		System.out.println(toString());
	}
	
	public void in() throws IOException{
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader li = new BufferedReader(in);
		try{
			System.out.print("Order: ");
			x = Integer.parseInt(li.readLine());
			if(x!=1&&!(x>2)){
				throw(new NumberFormatException());
			}
		}
		catch(NumberFormatException e){
			System.out.println("Invalid Order.");
			in();
		}
	}
	
	public void decider(){
		sqr = new int[x][x];
		System.out.println(toString());
		if(Integer.numberOfTrailingZeros(x)>0)
			even();
		else
			odd();
	}
	
	public void even(){
		ArrayList<Integer> in = new ArrayList<Integer>();
		int tmp=1;
		for(int y = 0;y<x;y++){
			sqr[y][y] = tmp;
			in.add(tmp);
			tmp += x + 1;
		}
		tmp = x;
		for(int y = 0;y<x;y++){
			sqr[y][x-1-y] = tmp;
			in.add(tmp);
			tmp += x - 1;
		}
		tmp = (int) (Math.pow(x, 2)-1);
		for(int y = 0;y<x;y++){
			for(int n = 0;n<x;){
				while(sqr[y][n] == 0){
					if(in.indexOf(tmp)==-1){
						sqr[y][n] = tmp;
					}
					tmp--;
				}
				n++;
			}
		}
	}
	
	public void odd(){
		sqr[0][x/2]=1;
		int y = 0;
		int x1 = x/2;
		int tmp = 2;
		int yold = 0;
		int xold = 0;
		while(tmp<=Math.pow(x, 2)){
			xold = x1;
			yold = y;
			y--;
			x1++;
			if(y==-1){
				y=x-1;
			}
			if(x1==x){
				x1 = 0;
			}
			if(sqr[y][x1]==0){
			}else{
				yold++;
				if(y==x){
					y=0;
				}
				y = yold;
				x1 = xold;
			}
			sqr[y][x1] = tmp;
			tmp++;
		}
	}
	
	public String toString(){
		String output = "";
		for(int y = 0;y<x;y++){
			for(int n = 0;n<x;n++){
				output += sqr[y][n];
				for(int u = (sqr[y][n]+"").length(); u<(Math.pow(x,2)+"").length();u++){
					output += " ";
				}
			}
			output += '\r';
		}
		return output;
	}
	
	public static void main(String[] a) throws IOException{
		@SuppressWarnings("unused")
		magic m1 = new magic();
	}
	
}
