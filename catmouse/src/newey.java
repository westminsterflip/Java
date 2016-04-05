import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class newey extends JFrame implements ActionListener{
	JTextField le = new JTextField(3);
	JTextField wi = new JTextField(3);
	JLabel by = new JLabel("X");
	JButton nw = new JButton("New");
	
	public newey(){
		super("New Mazer");
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
}
