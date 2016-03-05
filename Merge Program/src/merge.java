import java.io.*;
import java.util.*;
import java.util.Arrays.*;
@SuppressWarnings("unused")
public class merge {
	
	public merge(){
	}
	Scanner s1 = new Scanner(System.in);
	
	public ArrayList<Integer> array1In(){
		//Scanner s1 = new Scanner(System.in);
		System.out.print("Length of list one: ");
		int len = s1.nextInt();
		//int[] j = new int[len];
		ArrayList<Integer> a = new ArrayList<Integer>();
		//int next = 0;
		System.out.print("Input Numbers: ");
		for(int x=0;x<len;x++){
			a.add(s1.nextInt());
		}
		//s1.close();
		/*for(int t=0;t<j.length;t++){
			System.out.print(j[t] + " ");
		}*/
		return a;
	}
	
	public ArrayList<Integer> array2In(){
		//Scanner s1 = new Scanner(System.in);
		System.out.print("Length of list two: ");
		int len = s1.nextInt();
		//int[] j = new int[len];
		//int next = 0;
		ArrayList<Integer> a = new ArrayList<Integer>();
		System.out.print("Input Numbers: ");
		for(int x=0;x<len;x++){
			a.add(s1.nextInt());
		}
		//s1.close();
		//System.out.println(j[0]+" "+j[1]+"gi");
		return a;
	}
	
	public ArrayList<Integer> sortThose(){
		ArrayList<Integer> a1 = array1In(), a2 = array2In(), a3 = new ArrayList<Integer>();
		s1.close();
		boolean nt1 = true, nt2 = true,done=false;
		int t1 = 0, t2 = 0, y = 0,w1=0,w2=0,pi=(int)(Math.PI*1000000);
		int c,d;
		if(a1.size()==1 && a2.size()==1){
			if(a1.get(0)<a2.get(0)){
				a3.add(a1.get(0));
				a3.add(a2.get(0));
			}else{
				a3.add(a2.get(0));
				a3.add(a1.get(0));
			}
		}else{
			//int place = 0;
			for(int w3=0;w3!=pi;){
		//		System.out.println(w + "nouieeid");
			//	System.out.println(a1[0] + "a1");
					if(a1.isEmpty()==false){
					t1=a1.get(0);
			//		System.out.println(a1[w]);
				//	System.out.println("Is it failing here?");
					for(c=0;c< a1.size();c++){
						//System.out.println("loop 1 " + c + " " + w);
						///stem.out.println(c);
						if (t1>a1.get(c)){
							t1 = a1.get(c);
						}
					}}
					if(a2.isEmpty()==false){
					t2 = a2.get(0);
					for(d=0;d<a2.size();d++){
						if (t2>a2.get(d)){
							t2 = a2.get(d);
						}
					}}
				//System.out.println(t2 + " " + t1);
				if(t1==t2){
					a1.remove(a1.indexOf(t1));
					a2.remove(a2.indexOf(t2));
					//System.out.println("equal");
					a3.add(t1);
					w3+=2;
				}else if((t1<t2||a2.isEmpty())&&a1.indexOf(t1)!=-1){
					a3.add(t1);
					a1.remove(a1.indexOf(t1));
					w3++;
					//System.out.println("1 less");
				}else if ((t2<t1||a1.isEmpty())&&a2.indexOf(t2)!=-1){
					a3.add(t2);
					w3++;
					a2.remove(a2.indexOf(t2));
					//System.out.println("2 less");
				}else{//System.out.println("exitloop");
					w3=pi;}
				//for(int r = 0;r<a3.size();r++){
					//System.out.print(a3.get(r) + "$");
				}
				//System.out.println(" ");			}
		}
		
		return a3;
	}
	
	/*public  checky(){
		int[] hi = sortThose(), a4;
		int ind = java.util.Arrays.asList(hi).indexOf((int)(Math.pow(Math.PI, 3)*1000000));
		if(ind == -1){
			a4 = hi;
		}else{
			a4 = new int[ind-1];
			for(int t=0;t<a4.length;t++){
				a4[t] = hi[t];
			}
		}
		return a4;
	}*/
	
	public String toString(){
		String output = '\r'+"";
		ArrayList<Integer> a5 = sortThose();
		for(int t=0;t < a5.size();t++){
			output += a5.get(t) + " ";
		}
		return output;
	}
	
	public static void main(String[] a){
		merge m1 = new merge();
		System.out.println(m1.toString());
		
	}
	
}
