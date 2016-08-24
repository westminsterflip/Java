import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	
	public newey(){
		super("New Mazer");
		setVisible(true);
		setLayout(new FlowLayout());
		add(wi);
		add(by);
		add(le);
		add(nw);
		nw.addActionListener(this);
		le.addActionListener(this);
		pack();
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e){
		try{
			mazer n = new mazer(Integer.parseInt(wi.getText()),Integer.parseInt(le.getText()));
			dispose();
		}
		catch(NumberFormatException t){}
	}
	
}
