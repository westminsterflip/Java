import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class newey extends JFrame implements ActionListener{
	JTextField le = new JTextField(3);
	JTextField wi = new JTextField(3);
	JLabel by = new JLabel("X");
	JButton nw = new JButton("New");
	
	public newey(){
		super("New Mazer");
		setVisible(true);
		setLayout(new FlowLayout());
		add(wi);
		add(by);
		add(le);
		add(nw);
		nw.addActionListener(thisd);
		pack();
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e){
		dispose();
		mazer n = new mazer(Integer.parseInt(wi.getText()),Integer.parseInt(le.getText()));
	}
	
}
