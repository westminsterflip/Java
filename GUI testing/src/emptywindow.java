import javax.swing.*;

@SuppressWarnings("serial")
public class emptywindow extends JFrame{
	
	public emptywindow(){
		super("test");
		JTextField test = new JTextField();
		add(test);
		for(int x=1;x<11;x++){
			test.setColumns(x);
			pack();
			System.out.println(getWidth());
		}
		setVisible(true);
	}
	
	public static void main(String [] args){
	emptywindow w1 = new emptywindow();
	}	
}
