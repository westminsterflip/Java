import java.util.*;
public class merge {
	
	public merge(){
	}
	Scanner s1 = new Scanner(System.in);
	
	public ArrayList<Integer> array1In(){
		System.out.print("Length of list one: ");
		int len = s1.nextInt();
		ArrayList<Integer> a = new ArrayList<Integer>();
		System.out.print("Input Numbers: ");
		for(int x=0;x<len;x++){
			a.add(s1.nextInt());
		}
		return a;
	}
	
	public ArrayList<Integer> array2In(){
		System.out.print("Length of list two: ");
		int len = s1.nextInt();
		ArrayList<Integer> a = new ArrayList<Integer>();
		System.out.print("Input Numbers: ");
		for(int x=0;x<len;x++){
			a.add(s1.nextInt());
		}
		return a;
	}
	
	public ArrayList<Integer> sortThose(){
		ArrayList<Integer> a1 = array1In(), a2 = array2In(), a3 = new ArrayList<Integer>();
		s1.close();
		int t1 = 0, t2 = 0,pi=(int)(Math.PI*1000000);
		int c,d;
		if(a1.size()==1 && a2.size()==1){
			if(a1.get(0)<a2.get(0)){
				a3.add(a1.get(0));
				a3.add(a2.get(0));
			}else{
				a3.add(a2.get(0));
				a3.add(a1.get(0));
			}}else{
				for(int w3=0;w3!=pi;){
					if(a1.isEmpty()==false){
						t1=a1.get(0);
						for(c=0;c< a1.size();c++){
							if (t1>a1.get(c)){
								t1 = a1.get(c);
							}
						}
					}
					
					if(a2.isEmpty()==false){
						t2 = a2.get(0);
						for(d=0;d<a2.size();d++){
							if (t2>a2.get(d)){
								t2 = a2.get(d);
							}
						}
					}
					
				if(t1==t2&&(a1.indexOf(t1)!=-1||a2.indexOf(t2)!=-1)){
					if(a1.indexOf(t1)!=-1){
						a1.remove(a1.indexOf(t1));
					}
					if(a2.indexOf(t2)!=-1){
						a2.remove(a2.indexOf(t2));
					}
					a3.add(t1);
					w3+=2;
				}else if((t1<t2||a2.isEmpty())&&a1.indexOf(t1)!=-1){
					a3.add(t1);
					a1.remove(a1.indexOf(t1));
					w3++;
				}else if ((t2<t1||a1.isEmpty())&&a2.indexOf(t2)!=-1){
					a3.add(t2);
					w3++;
					a2.remove(a2.indexOf(t2));
				}else w3=pi;
			}
		}
		
		return a3;
	}
	
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
