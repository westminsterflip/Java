import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class wikidl {
	public static ArrayList<Integer> let = new ArrayList<Integer>(217);
	
	public wikidl(){}
	
	public void trying() throws IOException{
		for(int y = 0;y<217;y++){
			let.add(0);
		}
		while(let.get(216)<256){
			InputStreamReader o = new InputStreamReader(System.in);
			BufferedReader b = new BufferedReader(o);
			//b.readLine();
			int til = 0;
			for(int yu = 0;yu<217;yu++){
				if(!let.get(yu).equals(0))
					til = yu;
			}
			let.set(til, let.get(til)+1);
			for(int yu = 216;yu>=0;yu--){
				if(let.get(yu).equals(256)){
					if(yu==0){
						System.out.println(let);
						for(int i = 0;i<=til;i++){
							let.set(i, 1);
						}
						let.set(til+1, 1);
					}else{
						let.set(yu, 1);
						let.set(yu-1, let.get(yu-1)+1);
					}
				}
			}
			//for(int yu = 0;yu<217;yu++){
			//	System.out.print((char)(int)let.get(yu));
			//}
			//System.out.println();
		}
		System.out.println(let);
	}
	
	public static void main(String[] franny) throws IOException{
		wikidl w = new wikidl();
		w.trying();
	}
	
}
