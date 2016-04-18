import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class board extends JFrame implements ComponentListener{
	JPanel upper = new JPanel();
	JPanel lower = new JPanel();
	JPanel right = new JPanel();
	JPanel left = new JPanel();
	JPanel center = new JPanel();
	ArrayList<JPanel> upp = new ArrayList<JPanel>();
	ArrayList<JPanel> low = new ArrayList<JPanel>();
	ArrayList<JPanel> rig = new ArrayList<JPanel>();
	ArrayList<JPanel> lef = new ArrayList<JPanel>();
	ArrayList<JPanel> cen = new ArrayList<JPanel>();
	int xdim = 0, ydim = 0;
	
	
	public board(){
		super("Monopoly");
		setLayout(new BorderLayout());
		
		add(upper, BorderLayout.NORTH);
		add(lower, BorderLayout.SOUTH);
		add(right, BorderLayout.WEST);
		add(left, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
		setVisible(true);
		pack();
		xdim = getWidth();
		ydim = getHeight();
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
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

}
