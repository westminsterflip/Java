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
		board.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//board.setLayout(new BorderLayout());
		board.add(upper);
		board.add(left);
		board.add(center);
		board.add(right);
		board.add(lower);
		add(board);
		//left.setOpaque(true);
		//left.setBackground(Color.RED);
		left.add(new JLabel("left"));
		upper.setBackground(Color.PINK);
		center.setBackground(Color.orange);
		left.setBackground(Color.red);
		right.setBackground(Color.green);
		lower.setBackground(Color.yellow);
		board.setPreferredSize(new Dimension(232,232));
		//upper.setPreferredSize(new Dimension(board.getWidth(),(int)((double)board.getHeight()*1150.00/125.00)));
		//lower.setPreferredSize(new Dimension(board.getWidth(),(int)((double)board.getHeight()*1150.00/125.00)));
		//left.setPreferredSize(new Dimension(board.getWidth()*1150/125,board.getHeight()-board.getHeight()*1150/62));
		System.out.println(left.getWidth()+" " +left.getHeight());
		upper.add(new JLabel("upper"));
		//board.setOpaque(true);
		board.setBackground(Color.BLUE);
		//board.add(new JLabel("HI"));
		setMinimumSize(new Dimension(136,170));
		setVisible(true);
		pack();
		addComponentListener(this);
		setLocation((int)Math.round((xpos-getWidth())/2),(int)Math.round((ypos-getHeight())/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void build(){
		int n = upper.getHeight();
		int j = left.getHeight()/9;
		for(int y = 0;y<11;y++){
			if(y==0||y==11){
				JPanel u = new JPanel();
				u.setPreferredSize(new Dimension(n,n));
				u.setBackground(Color.BLACK);
				upp.add(u);
			}else{
				JPanel u = new JPanel();
				u.setPreferredSize(new Dimension());
			}
		}
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		int multi = getWidth()-30;
		System.out.println(getWidth() + " " + getHeight());
		if(getHeight()<getWidth())
			multi = getHeight()-30;
		System.out.println("x " + multi);
		board.setVisible(false);
		upper.setVisible(false);
		left.setVisible(false);
		lower.setVisible(false);
		int small = multi*125/1150;
		board.setPreferredSize(new Dimension(multi,multi));
		upper.setPreferredSize(new Dimension(multi,small));
		lower.setPreferredSize(new Dimension(multi,small));
		left.setPreferredSize(new Dimension(small,multi-2*small));
		right.setPreferredSize(new Dimension(small,multi-2*small));
		center.setPreferredSize(new Dimension(multi-2*small,multi-2*small));
		System.out.println(board.getWidth()+" b " +board.getHeight());
		System.out.println(left.getWidth()+" l " +left.getHeight());
		System.out.println(right.getWidth()+" r " +right.getHeight());
		System.out.println(upper.getWidth()+" u " +upper.getHeight());
		System.out.println(lower.getWidth()+" lo " +lower.getHeight());
		System.out.println(center.getWidth()+" c " +center.getHeight());
		board.setVisible(true);
		upper.setVisible(true);
		left.setVisible(true);
		lower.setVisible(true);
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
