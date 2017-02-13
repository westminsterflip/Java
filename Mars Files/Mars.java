import java.io.File;
import java.io.IOException;

public class Mars {public static void main(String[] args) {
	new mars.MarsLaunch(args);
	File tb = new File("tb.bat");
	if(tb.exists())
		tb.delete();
	File tb1 = new File("tb1.bat");
	if(tb1.exists())
		tb1.delete();
	if(new File("mars\\tools\\new_creation").exists()){
		Runtime m = Runtime.getRuntime();
		try {
			Process n = m.exec("cmd start /c rmdir mars /s /q");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}} 