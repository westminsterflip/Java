import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class mazer extends JFrame implements ActionListener,MouseListener{
	private static final long serialVersionUID = 2329284045764109317L;
	JPanel maz = new JPanel();
	ArrayList<JPanel> blocs = new ArrayList<JPanel>(); 
	String in;
	ArrayList<JLabel> name = new ArrayList<JLabel>();
	JPanel kat = new JPanel();
	JLabel ca=new JLabel("C");
	int len = 3, wid = 3,mice=0,cats=0;
	JTextField le = new JTextField(3);
	JTextField wi = new JTextField(3);
	JLabel by = new JLabel("X");
	JPanel neu = new JPanel();
	JButton nw = new JButton("New");
	JButton go = new JButton("Go");
	public volatile static boolean cango=false;
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel but = new JPanel();
	JPanel bot = new JPanel();
	JMenuBar menu = new JMenuBar();
	JMenu fle = new JMenu("File");
	JMenu niw = new JMenu("New Maze");
	JMenuItem nwe = new JMenuItem("New Default");
	JMenuItem nwi = new JMenuItem("New Custom");
	
	
	public mazer(){
		super("Cats, and Mouses, and Mazes! Oh my!");
		fle.add(niw);
		niw.add(nwe);
		niw.add(nwi);
		menu.add(fle);
		nwe.addActionListener(this);
		nwi.addActionListener(this);
		//setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		add(menu);
		maz.setPreferredSize(new Dimension(60,60));
		//maz.setSize(new Dimension(60,60));
		maz.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		blocs.add(kat);
		name.add(ca);
		kat.add(ca);
		kat.setPreferredSize(new Dimension(20,20));
		kat.setBackground(Color.yellow);
		kat.setOpaque(true);
		kat.addMouseListener(this);
		//kat.setBorder(BorderFactory.createLineBorder(Color.black,1));
		maz.add(kat);
		neu.setLayout(new BoxLayout(neu,BoxLayout.X_AXIS));
		neu.add(wi);
		neu.add(by);
		neu.add(le);
		but.setLayout(new BoxLayout(but,BoxLayout.X_AXIS));
		but.add(go);
		but.add(nw);
		bot.setLayout(new BorderLayout());
		bot.add(neu,BorderLayout.NORTH);
		bot.add(but, BorderLayout.SOUTH);
		for(int x = 1;x<9;x++){
			JPanel j = new JPanel();
			JLabel f = new JLabel("");
			j.setPreferredSize(new Dimension(20,20));
			j.setOpaque(true);
			j.setBackground(Color.black);
			j.add(f);
			//j.setBorder(BorderFactory.createLineBorder(Color.black,1));
			maz.add(j);
			blocs.add(j);
			name.add(f);
			blocs.get(x).addMouseListener(this);
		}
		blocs.get(8).setBackground(Color.cyan);
		name.get(8).setText("M");
		add(maz);
		add(bot);
		go.addActionListener(this);
		nw.addActionListener(this);
		//setPreferredSize(new Dimension(50,50))
		//setMaximumSize(new Dimension(wid+5,len+5));
		//setSize(new Dimension(wid+5,len+20));
		pack();
		setVisible(true);
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//System.out.println(maz.getHeight() + " " + blocs.get(0).getHeight() + " " + getWidth());
	}
	
	public mazer(int w, int l){
		super("Cats, and Mouses, and Mazes! Oh my!");
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		maz.setPreferredSize(new Dimension(w*20,l*20));
		maz.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		blocs.add(kat);
		name.add(ca);
		kat.add(ca);
		kat.setPreferredSize(new Dimension(20,20));
		kat.setBackground(Color.yellow);
		kat.setOpaque(true);
		kat.addMouseListener(this);
		//kat.setBorder(BorderFactory.createLineBorder(Color.black,1));
		maz.add(kat);
		for(int x = 1;x<w*l;x++){
			JPanel j = new JPanel();
			JLabel f = new JLabel("");
			j.setPreferredSize(new Dimension(20,20));
			j.setOpaque(true);
			j.setBackground(Color.black);
			j.add(f);
			//j.setBorder(BorderFactory.createLineBorder(Color.black,1));
			maz.add(j);
			blocs.add(j);
			name.add(f);
			blocs.get(x).addMouseListener(this);
		}
		neu.setLayout(new BoxLayout(neu,BoxLayout.X_AXIS));
		neu.add(wi);
		neu.add(by);
		neu.add(le);
		but.setLayout(new BoxLayout(but,BoxLayout.X_AXIS));
		but.add(go);
		but.add(nw);
		bot.setLayout(new BorderLayout());
		bot.add(neu,BorderLayout.NORTH);
		bot.add(but, BorderLayout.SOUTH);
		blocs.get(blocs.size()-1).setBackground(Color.cyan);
		name.get(blocs.size()-1).setText("M");
		add(maz);
		add(bot);
		go.addActionListener(this);
		nw.addActionListener(this);
		//setPreferredSize(new Dimension(50,50));
		pack();
		setVisible(true);
		//System.out.println(maz.getHeight() + " " + blocs[0][0].getHeight() + " " + getWidth());
		//System.out.println(w + " " + l);
		len=l;
		wid=w;
		//System.out.println(wid + " " + len);
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void counter(){
		cats=0;
		mice=0;
		for(int z = 0;z<name.size();z++){
			if(name.get(z).getText().equals("C"))
				cats++;
			else if(name.get(z).getText().equals("M"))
				mice++;
		}
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent g) {
		if(g.getSource().equals(go)){
			counter();
			//System.out.println(cats + " " + mice);
			if(cats==1&&mice==1)
				cango=true;
			else if(cats>1){
				error j = new error("cats");
				if(mice>1){
					error h = new error("mice");
				}
			}else if(mice>1){
				error h = new error("mice");
			}else{
				mazer n = new mazer(wid,len);
			}
			try {
				test();
				//name.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(g.getSource()==nwe){
			dispose();
			mazer n = new mazer();
		}else if(g.getSource()==nwi){
			dispose();
			newey n = new newey();
		}else{
			dispose();
			mazer n = new mazer(Integer.parseInt(wi.getText()),Integer.parseInt(le.getText()));
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] yay){
		mazer y = new mazer();
	}

	public void mouseClicked(MouseEvent e) {
		JPanel k = (JPanel) e.getComponent();
		if(e.getComponent().getBackground().equals(Color.white)){
			e.getComponent().setBackground(Color.yellow);
			//setCat(k);
			setName(k,"C");
		}else if(e.getComponent().getBackground().equals(Color.yellow)){
			e.getComponent().setBackground(Color.cyan);
			//setMouse(k);
			setName(k,"M");
		}else if(e.getComponent().getBackground().equals(Color.cyan)){
			e.getComponent().setBackground(Color.black);
			setName(k,"");
		}else if(e.getComponent().getBackground().equals(Color.black)){
			e.getComponent().setBackground(Color.white);
			setName(k,"");
		}
		
	}
	
	public void test() throws IOException{
		in="";
		//System.out.println(wid + " " + len);
		for(int d=0;d<blocs.size();d++){
			if(blocs.get(d).getBackground().equals(Color.black)){
				in+="#";
			}else if(blocs.get(d).getBackground().equals(Color.yellow)&&name.get(d).getText().equals("C")){
				in+="C";
			}else if(blocs.get(d).getBackground().equals(Color.yellow)){
				in+=" ";
			}else if(blocs.get(d).getBackground().equals(Color.white)){
				in+=" ";
			}else if(blocs.get(d).getBackground().equals(Color.cyan)){
				in+="M";
			}
		}
		//System.out.println(in);
		maze_2 m2 = new maze_2(in,wid,len);
		//System.out.println(m2.toString());
		maz.setVisible(false);
		translate(m2.gitDun());
		//translate(m2.gitDun());
		maz.setVisible(true);
	}
	
	public void setName(JPanel h, String u){
		name.get(blocs.indexOf(h)).setText(u);
	}
	
	public void translate(char[][] ma){
		int us = 0;
		for(int f=0;f<len;f++){
			for(int s=0;s<wid;s++){
				//System.out.println(s + " " + f + " " + ma[s][f]);
				switch(ma[s][f]){
				case 'C': blocs.get(us).setBackground(Color.yellow);
						name.get(us).setText("C");
						break;
				case ' ': blocs.get(us).setBackground(Color.white);
				name.get(us).setText("");
						break;
				case 'M': blocs.get(us).setBackground(Color.cyan);
				name.get(us).setText("M");
						break;
				case '#': blocs.get(us).setBackground(Color.black);
				name.get(us).setText("");
						break;
				case '+': blocs.get(us).setBackground(Color.yellow);
				name.get(us).setText("");
						break;
				}
				us++;
			}
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}
