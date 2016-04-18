import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
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
	
	public board(){
		super("Monopoly");
		setLayout(new FlowLayout());
		board.setLayout(new FlowLayout());
		board.add(upper);
		board.add(left);
		board.add(center);
		board.add(lower);
		board.add(right);
		add(board);
		board.setPreferredSize(new Dimension(230,230));
		board.setOpaque(true);
		board.setBackground(Color.BLUE);
		//board.add(new JLabel("HI"));
		setMinimumSize(new Dimension(136,170));
		setVisible(true);
		pack();
		addComponentListener(this);
		setLocation((int)Math.round((xpos-getWidth())/2),(int)Math.round((ypos-getHeight())/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		int multi = getWidth()-10;
		System.out.println(getWidth() + " " + getHeight());
		if(getHeight()<getWidth())
			multi = getHeight()-10;
		System.out.println("x " + multi);
		board.setPreferredSize(new Dimension(multi,multi));
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
	
	public static void main(String[] args){
		//TODO Science has occurred
		board b1 = new board();
	}
	
}
