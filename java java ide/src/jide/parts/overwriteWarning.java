package jide.parts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jide.jidest;

@SuppressWarnings("serial")
public class overwriteWarning extends JDialog implements ActionListener{
	boolean overwrite = false;
	JButton yes,cancel;
	public overwriteWarning(){
		setTitle("File Will Be Overwritten");
		Image m1 = Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+"icon.png"));
		Image m = Toolkit.getDefaultToolkit().getImage(jidest.class.getResource("/images"+File.separator+"warning.png"));
		setIconImage(m1);
		m=m.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		Icon dos = new ImageIcon(m);
		JLabel warning = new JLabel("<html><br>Are you sure you want to overwrite this file?<br><br></html>",dos,JLabel.CENTER);
		warning.setFont(new Font("Arial",Font.PLAIN,14));
		JPanel warningLine = new JPanel();
		warningLine.add(warning,BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		yes = new JButton("Yes");
		cancel = new JButton("No");
		yes.setMnemonic(KeyEvent.VK_Y);
		cancel.setMnemonic(KeyEvent.VK_N);
		buttons.add(cancel);
		buttons.add(yes);
		yes.setPreferredSize(new Dimension(81,23));
		cancel.setPreferredSize(new Dimension(81,23));
		add(warning,BorderLayout.NORTH);
		add(buttons,BorderLayout.SOUTH);
		yes.addActionListener(this);
		cancel.addActionListener(this);
		setModal(true);
		pack();
		setLocation((int)Math.round((jidest.scrSize.getWidth()-getWidth())/2.0),(int)Math.round((jidest.scrSize.getHeight()-getHeight())/2.0));
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(yes)){
			overwrite=true;
		}
		dispose();
	}
}
