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
		System.out.print("Order: ");
		x = Integer.parseInt(li.readLine());
		sqr = new int[x][x];
	}
	
	public void decider(){
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
		magic m1 = new magic();
	}
	
}
