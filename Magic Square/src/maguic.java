import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
public class maguic extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6667347255736594456L;
	public int[][] sqr;
	public int[] er;
	public int x;
	public JScrollPane tabl;
	public JTable tab;
	public JPanel in = new JPanel();
	public JTextField inp = new JTextField(35);
	public JLabel i = new JLabel("Order:");
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	
	public maguic() throws IOException{
		super("hi");
		setVisible(true);
		in.setLayout(new BorderLayout());
		in.add(i,BorderLayout.WEST);
		in.add(inp, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(in,BorderLayout.NORTH);
		inp.addActionListener(this);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		in();
		decider();
		tabler();
	}

	public void in(){
		try{
			x = Integer.parseInt(inp.getText());
			if(x!=1&&!(x>2)){
				throw(new NumberFormatException());
			}
		}
		catch(NumberFormatException e){
			inp.setText("Invaild Order");
		}
	}
	
	public void decider(){
		sqr = new int[x][x];
		//System.out.println(toString());
		er = new int[x];
		if(Integer.numberOfTrailingZeros(x)>0){
			even();
		}
		else{
			odd();
		}
	}
	
	public int getS(int e, int t){
		return sqr[e][t];
	}
	
	public void even(){
		ArrayList<Integer> in = new ArrayList<Integer>();
		int tmp=1;
		for(int y = 0;y<x;y++){
			sqr[y][y] = tmp;
			in.add(tmp);
			tmp += x + 1;
		}
		tmp = x;
		for(int y = 0;y<x;y++){
			sqr[y][x-1-y] = tmp;
			in.add(tmp);
			tmp += x - 1;
		}
		tmp = (int) (Math.pow(x, 2)-1);
		for(int y = 0;y<x;y++){
			for(int n = 0;n<x;){
				while(getS(y,n)== 0){
					if(in.indexOf(tmp)==-1){
						sqr[y][n] = tmp;
					}
					tmp--;
				}
				n++;
			}
		}
	}
	
	public void odd(){
		sqr[0][x/2]=1;
		int y = 0;
		int x1 = x/2;
		int tmp = 2;
		int yold = 0;
		int xold = 0;
		while(tmp<=Math.pow(x, 2)){
			xold = x1;
			yold = y;
			y--;
			x1++;
			if(y==-1){
				y=x-1;
			}
			if(x1==x){
				x1 = 0;
			}
			if(sqr[y][x1]==0){
			}else{
				yold++;
				if(y==x){
					y=0;
				}
				y = yold;
				x1 = xold;
			}
			sqr[y][x1] = tmp;
			tmp++;
		}
	}
	
	public void tabler(){
		Integer[][]hi = new Integer[x][x];
		for(int y = 0;y<x;y++){
			for(int n = 0;n<x;n++){
				hi[y][n] = getS(y,n);	
			}
		}
		TableModel t = new DefaultTableModel(hi,new String[x]);
		tab = new JTable(t);
		tab.setTableHeader(null);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabl = new JScrollPane(tab);
		tabl.createHorizontalScrollBar();
		tabl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tabl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tab.setShowGrid(false);
		System.out.println(tab.getHeight());
		tabl.setPreferredSize(new Dimension(tab.getPreferredScrollableViewportSize()));
		tabl.setMinimumSize(new Dimension(tab.getPreferredScrollableViewportSize()));
		add(tabl);
		tabl.setVisible(true);
		pack();
		inp.removeActionListener(this);
		inp.setEditable(false);
	}
	
	public String toString(){
		String output = "";
		for(int y = 0;y<x;y++){
			for(int n = 0;n<x;n++){
				output += sqr[y][n];
				for(int u = (sqr[y][n]+"").length(); u<(Math.pow(x,2)+"").length();u++){
					output += " ";
				}
			}
			output += '\r';
		}
		return output;
	}
	
	public static void main(String[] a) throws IOException{
		maguic m1 = new maguic();
	}
	
}
