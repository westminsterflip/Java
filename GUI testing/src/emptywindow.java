import javax.swing.JFrame;

public class emptywindow {
	public static void main(String [] args){
		JFrame frame = new JFrame("Empty Window");
		for(int x=300;x<500;x+=20){
		frame.setSize(x,x);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		}
}
