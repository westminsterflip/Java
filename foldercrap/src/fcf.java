import java.io.File;
import java.io.IOException;

public class fcf {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File x = new File("tuft");
		String y = "tuft";
		if(x.exists())
			System.out.println("good to go");
		else{
			x.mkdir();
			System.out.println("no");
		}
		x.delete();
		File u = x;
		int cnt = 0;
		while(u.exists()){
			while(x.exists()){
				x.delete();
				//System.out.println(y);
				y+="\\tuft1";
				x=new File(y);
			}
			System.out.println("filepath length: " +y.length());
			cnt++;
			System.out.println(cnt + " down");
			y="tuft";
			x=u;
		}
		//System.out.println("oi");
	}
	
}
