import java.util.*;
public class merge {
	
	public merge(){}
	ArrayList<Integer> b = new ArrayList<Integer>();
	ArrayList<Integer> a = new ArrayList<Integer>();
	ArrayList<Integer> a3 = new ArrayList<Integer>();
	int t1,t2,c,d;
	boolean onegood=false;
	
	public void arrayIn(){
		Scanner s1 = new Scanner(System.in);
		int x=0;
		if(onegood==false){
			try{
				System.out.print("Length of list one: ");
				int len = s1.nextInt();
				if(len<=0){
					System.out.println("List length must be greater than 0");
					s1.nextLine();
					arrayIn();
				}else{
					System.out.print("Input Numbers: ");
					for(x=0;x<len;x++){
						a.add(s1.nextInt());
					}
					onegood=true;
				}
			}
			catch(InputMismatchException exception){
				System.out.println("Input must be integers.");
				s1.nextLine();
				int d = a.size()-x;
				for(int y=a.size()-1;y>=d;y--){
					a.remove(y);
				}
				arrayIn();
				
			}
		}
		x=0;
		try{
			System.out.print("Length of list two: ");
			int len1 = s1.nextInt();
			if(len1<=0){
				System.out.println("List length must be greater than 0");
				s1.nextLine();
				arrayIn();
			}else{
				System.out.print("Input Numbers: ");
				for(x=0;x<len1;x++){
					a.add(s1.nextInt());
				}
			}
		}
		catch(InputMismatchException exception){
			System.out.println("Input must be integers.");
			s1.nextLine();
			int d = a.size()-x;
			for(int y=a.size()-1;y>=d;y--){
				a.remove(y);
			}
			arrayIn();
		}
		s1.close();
	}
	
	public void sortThose(){
		//System.out.println(a + " and "+ b);
		if(a.isEmpty()==true&&b.isEmpty()==true)
		{
			godostuff();
		}else{
			if(a.isEmpty()==false){
				t1=a.get(0);
				for(c=0;c< a.size();c++){
					if (t1>a.get(c)){
						t1 = a.get(c);
					}
				}
			}
			
			if(b.isEmpty()==false){
				t2 = b.get(0);
				for(d=0;d<b.size();d++){
					if (t2>b.get(d)){
						t2 = b.get(d);
					}
				}
			}
			
			if(t1==t2&&(a.indexOf(t1)!=-1||b.indexOf(t2)!=-1)){
				if(a.indexOf(t1)!=-1){
					a.remove(a.indexOf(t1));
				}
				if(b.indexOf(t2)!=-1){
					b.remove(b.indexOf(t2));
				}
				if(a3.indexOf(t1)==-1){
					a3.add(t1);
				}
			}else if((t1<t2||b.isEmpty())&&a.indexOf(t1)!=-1){
					if(a3.indexOf(t1)==-1){
						a3.add(t1);
					}
					a.remove(a.indexOf(t1));
			}else if ((t2<t1||a.isEmpty())&&b.indexOf(t2)!=-1){
					if(a3.indexOf(t2)==-1){
						a3.add(t2);
					}
					b.remove(b.indexOf(t2));
			}
			sortThose();
		}
	}
	
	public void godostuff(){
		String output = '\r'+"";
		for(int t=0;t < a3.size();t++){
			output += a3.get(t) + " ";
		}
		a3.clear();
		System.out.println(output);
	}
	
	public static void main(String[] a){
		merge m1 = new merge();
		m1.arrayIn();
		m1.sortThose();
		m1.godostuff();
	}
	
}
