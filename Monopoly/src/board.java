import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class board extends JFrame implements ComponentListener{
	JPanel upper = new JPanel();
	JPanel lower = new JPanel();
	JPanel right = new JPanel();
	JPanel left = new JPanel();
	JPanel center = new JPanel();
	JPanel board = new JPanel();
	JPanel shell = new JPanel();
	ArrayList<JPanel> upp = new ArrayList<JPanel>();
	ArrayList<JPanel> low = new ArrayList<JPanel>();
	ArrayList<JPanel> rig = new ArrayList<JPanel>();
	ArrayList<JPanel> lef = new ArrayList<JPanel>();
	ArrayList<JPanel> cen = new ArrayList<JPanel>();
	//ArrayList<JPanel> all = new ArrayList<JPanel>();
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	public int xpos = (int) scr.getWidth(),ypos = (int) scr.getHeight();
	
	public board() throws IOException{
		super("Monopoly");
		setLayout(new FlowLayout());
		board.setPreferredSize(new Dimension(232,232));
		
		setVisible(true);
		pack();
		addComponentListener(this);
		setLocation((int)Math.round((xpos-getWidth())/2),(int)Math.round((ypos-getHeight())/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		BufferedImage bd = ImageIO.read(new File("monobd.jpg"));
		Graphics pic = bd.getGraphics();
		pic.drawImage(bd, 0, 0, 232,232,null);
		paint(pic);
		pack();
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws IOException{
		//TODO Science has occurred
		board b1 = new board();
	}
	
}
