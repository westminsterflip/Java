import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class opener {
	public opener(File k) throws IOException{
		Desktop naw = Desktop.getDesktop();
		naw.open(k);
	}
}