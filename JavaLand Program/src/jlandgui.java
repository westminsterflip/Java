import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class jlandgui extends JFrame implements ActionListener{
	JTextField een = new JTextField(10);
	JTextField aut = new JTextField(10);
	
	public jlandgui(){
		super("TAXES");
		JPanel in = new JPanel();
		JPanel out = new JPanel();
		setLayout(new BorderLayout());
		in.setLayout(new BoxLayout(in,BoxLayout.X_AXIS));
		out.setLayout(new BoxLayout(out,BoxLayout.X_AXIS));
		in.add(new JLabel("Income:"));
		in.add(een);
		out.add(new JLabel("Taxes:"));
		out.add(aut);
		aut.setEditable(false);
		een.addActionListener(this);
		setVisible(true);
		add(in,BorderLayout.NORTH);
		add(out,BorderLayout.SOUTH);
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		JavaLand_ver2 n = new JavaLand_ver2(Integer.parseInt(een.getText().trim()));
		aut.setText("$" + n.taxify());
	}
	
	public static void main(String[] d){
		jlandgui j = new jlandgui();
	}
}
