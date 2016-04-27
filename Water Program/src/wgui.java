import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class wgui extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2715178773047958327L;
	JRadioButton i = new JRadioButton("Industrial Use");
	JRadioButton h = new JRadioButton("Home Use");
	JRadioButton c = new JRadioButton("Commercial Use");
	JTextField id = new JTextField(20);
	JTextField used = new JTextField(20);
	JTextField cost = new JTextField(20);
	JPanel idp = new JPanel();
	JPanel usedp = new JPanel();
	JPanel costp = new JPanel();
	JLabel idl = new JLabel("ID:");
	JLabel usedl = new JLabel("Gallons Used:");
	JLabel costl = new JLabel("Cost:");
	char sel;
	JPanel in = new JPanel();
	JPanel buttons = new JPanel();
	
	public wgui(){
		super("Water Costs");
		setLayout(new BorderLayout());
		in.setLayout(new BoxLayout(in, BoxLayout.Y_AXIS));
		idp.setLayout(new BoxLayout(idp, BoxLayout.X_AXIS));
		usedp.setLayout(new BoxLayout(usedp, BoxLayout.X_AXIS));
		costp.setLayout(new BoxLayout(costp, BoxLayout.X_AXIS));
		idp.add(idl);idp.add(id);
		usedp.add(usedl);usedp.add(used);
		costp.add(costl);costp.add(cost);
		in.add(idp);
		in.add(usedp);
		in.add(costp);
		cost.setEditable(false);
		ButtonGroup bg = new ButtonGroup();
		bg.add(i);bg.add(h);bg.add(c);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		buttons.add(h);
		buttons.add(c);
		buttons.add(i);
		h.addActionListener(this);
		c.addActionListener(this);
		i.addActionListener(this);
		add(buttons, BorderLayout.WEST);
		add(in, BorderLayout.EAST);
		pack();
		used.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==h)
			sel = 'h';
		else if(e.getSource()==c)
			sel = 'c';
		else if(e.getSource()==i)
			sel = 'i';
		else{
			Water w1 = new Water(Integer.parseInt(id.getText().trim()),sel,Integer.parseInt(used.getText().trim()));
			cost.setText("$" + w1.costify());
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] g){
		wgui w = new wgui();
	}
}
