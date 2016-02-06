package JavaLand;
import java.io.*;
public class JavaLand {
	
	public class Taxes{
		
		private int income;
		
		public Taxes(int inco){
			income = inco;
		}
		public void setIncome(int inco) throws IOException{
			income = inco;
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader input = new BufferedReader(reader);
			
			System.out.print("Enter a String: ");
			String inputString = input.readLine();
			System.out.println("String is: " + inputString);
		}
		public int getIncome(){
			return income;
		}
	}
	
	public static void main(String[] args) {
		Taxes t1 = new Taxes(1);
		t1.getIncome();
	}

}
