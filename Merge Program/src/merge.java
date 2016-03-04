import java.io.*;
import java.util.*;
@SuppressWarnings("unused")
public class merge {
	
	public merge(){
	}
	
	public int[] array1In(){
		Scanner s1 = new Scanner(System.in);
		System.out.print("Length of list one: ");
		int len = s1.nextInt();
		int[] j = new int[len];
		int next = 0;
		System.out.print("Input Numbers: ");
		for(int x=0;x<len;x++){
			j[x]=s1.nextInt();
		}
		s1.close();
		return j;
	}
	
	public int[] array2In(){
		Scanner s1 = new Scanner(System.in);
		System.out.print("Length of list two: ");
		int len = s1.nextInt();
		int[] j = new int[len];
		int next = 0;
		System.out.print("Input Numbers: ");
		for(int x=0;x<len;x++){
			j[x]=s1.nextInt();
		}
		s1.close();
		return j;
	}
	
	@SuppressWarnings("null")
	public void sortThose(){
		int[] a1 = array1In(), a2 = array2In(), a3 = new int[a1.length+a2.length];
		int t1 = 0, t2 = 0, y = 0, p=0;
		if(a1.length>=a2.length){
			y=a1.length;
		}else{
			y=a2.length;
		}
		if(a1.length==1 && a2.length==1){
			if(a1[0]<a2[0]){
				a3[0] = a1[0];a3[1]=a2[0];
			}else{
				a3[0] = a2[0];a3[1]=a1[0];
			}
		}else{
			for(int w=0;w<y-1;w++){
				for(int c=0;c<y;c++){
					try{
						t1 = a1[c];
					}
					catch(ArrayIndexOutOfBoundsException exception){t1 = (Integer)null;}
					try{
						t2 = a2[c];
					}
					catch(ArrayIndexOutOfBoundsException exception){t2 = (Integer)null;}
					
				}
			}
			
		}
	}
	
	
	public static void main(String[] a){
		merge m1 = new merge();
		int[] u = {2,2};
		
	}
	
}
