package jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import jide.jidest;

@SuppressWarnings("serial")
public class yatespash extends JWindow{
	double testwidth = 0;
	public JProgressBar ttfpro = new JProgressBar(0,jidest.fontFileNumber);
	public JWindow test = new JWindow();
	public JWindow testpro = new JWindow();
	public JPanel testColor = new JPanel();
	public JPanel testproColor = new JPanel();
	JPanel opbc = new JPanel();
	public JLabel output = new JLabel("");
	public JWindow outputback = new JWindow();
	JLabel title;
	public yatespash(){
		ImageBackgroundPanel cont = new ImageBackgroundPanel(this.getClass().getResource("/images/splash.png"));
		add(cont);
		int wid=jidest.scrSize.width/4,hig=jidest.scrSize.height/4;
		setBounds((jidest.scrSize.width-wid)/2,(jidest.scrSize.height-hig)/2,wid,hig);
		title = new JLabel("     YATE");
		title.setVisible(true);
		title.setFont(new Font("Univers",Font.PLAIN,getWidth()/4));
		title.setForeground(Color.lightGray);
		cont.add(title,BorderLayout.NORTH);
		setVisible(true);
	}
	
	public void build(){
		test.setBackground(Color.darkGray);testproColor.setSize(640,10);
		testproColor.setBackground(Color.lightGray);
		testpro.add(testproColor);testColor.setSize(0,10);
		testColor.setBackground(Color.darkGray);
		test.add(testColor);
		test.setSize(0, 10);
		opbc.setBackground(Color.darkGray);
		outputback.setSize(this.getWidth()/3*2, 30);
		opbc.setLayout(new FlowLayout(FlowLayout.LEFT));
		opbc.add(output);
		output.setFont(new Font("Univers=",Font.PLAIN,14));
		output.setForeground(Color.lightGray);
		opbc.setSize(this.getWidth()/3*2,30);
		outputback.add(opbc);
		testpro.setSize(this.getWidth(),10);
		testpro.setLocation(this.getLocation().x, this.getLocation().y+getHeight()-10);
		test.setLocation(this.getLocation().x, this.getLocation().y+getHeight()-10);
		outputback.setLocation(this.getLocation().x+this.getWidth()-350, this.getLocation().y+title.getHeight());
		test.setVisible(true);
		outputback.setVisible(true);
		repaint();
		revalidate();
	}
	
	public class ImageBackgroundPanel extends JPanel{
		Image image;
		public ImageBackgroundPanel(URL imagep){
		try{
			image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(imagep)).getImage();
		}catch (Exception e) {e.printStackTrace();}
	}
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
				if (image != null)
					g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
		}
	}
	
	public void widen(){
		testwidth += getWidth()/(double)jidest.fontFileNumber;
		test.setSize(new Dimension((int)Math.floor(testwidth),10));
		testColor.setSize(test.getSize());
		test.revalidate();
		test.repaint();
	}
	
	public void shorten(){
		testwidth -= getWidth()/(double)jidest.fontFileNumber;
		test.setSize(new Dimension((int)Math.floor(testwidth),10));
		testColor.setSize(test.getSize());
		test.revalidate();
		test.repaint();
	}
}
