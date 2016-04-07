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
	ArrayList<String> name = new ArrayList<String>();
	JPanel kat = new JPanel();
	JLabel ca=new JLabel(new ImageIcon("cat.png")), paw = new JLabel(new ImageIcon("paws.png"));
	JLabel maus=new JLabel(new ImageIcon("mouse.png")) ,blnk = new JLabel("");
	int len = 3, wid = 3,mice=0;//,cats=0;
	//JTextField le = new JTextField(3);
	//JTextField wi = new JTextField(3);
	//JLabel by = new JLabel("X");
	//JPanel neu = new JPanel();
	//JButton nw = new JButton("New");
	//JButton go = new JButton("Go");
	public volatile static boolean cango=false;
	public static boolean debug = false;
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	//JPanel but = new JPanel();
	//JPanel bot = new JPanel();
	JMenuBar menu = new JMenuBar();
	//JMenu fle = new JMenu("File");
	JMenu niw = new JMenu("New");
	JMenu run = new JMenu("Run");
	JMenu halp = new JMenu("Help");
	JMenu color = new JMenu("Color");
	JMenuItem hlp = new JMenuItem("Help");
	JMenuItem gon = new JMenuItem("Go");
	JMenuItem goc = new JMenuItem("Go/Debug");
	JMenuItem nwe = new JMenuItem("New Default");
	JMenuItem nwi = new JMenuItem("New Custom");
	JPanel mazens = new JPanel();
	JPanel menubar = new JPanel();
	int index,keypressed=0;
	JToolBar tools = new JToolBar("Mazebox");
	JToggleButton wooo = new JToggleButton(new ImageIcon("squares.png"));
	public boolean isdragging = false;
	JMenuItem white = new JMenuItem(" "),black = new JMenuItem(" "),cyan = new JMenuItem(" "),yellow = new JMenuItem(" ");
	
	public mazer(){
		super("Cats, and Mouses, and Mazes! Oh my!");
		wooo.setToolTipText("Drag mouse over squares.  w for white, b for black, c for cat, m for mouse.  'n' to toggle");
		wooo.setMnemonic('n');
		wooo.addActionListener(this);
		tools.add(wooo);
		tools.setFloatable(false);
		white.addActionListener(this);
		black.addActionListener(this);
		cyan.addActionListener(this);
		yellow.addActionListener(this);
		white.setAccelerator(KeyStroke.getKeyStroke('w'));
		black.setAccelerator(KeyStroke.getKeyStroke('b'));
		cyan.setAccelerator(KeyStroke.getKeyStroke('m'));
		yellow.setAccelerator(KeyStroke.getKeyStroke('c'));
		menu.add(niw);
		niw.add(nwe);
		niw.add(nwi);
		menu.add(run);
		menu.add(halp);
		color.add(yellow);
		color.add(cyan);
		color.add(black);
		color.add(white);
		menu.add(color);
		halp.add(hlp);
		hlp.addActionListener(this);
		run.add(gon);
		run.add(goc);
		nwe.addActionListener(this);
		nwi.addActionListener(this);
		menubar.setLayout(new BoxLayout(menubar,BoxLayout.X_AXIS));
		mazens.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setLayout(new BorderLayout());
		menubar.add(menu);
		add(menu,BorderLayout.NORTH);
		maz.setPreferredSize(new Dimension(75,75));
		maz.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		blocs.add(kat);
		name.add("C");
		kat.add(ca);
		kat.setPreferredSize(new Dimension(25,25));
		kat.setBackground(Color.yellow);
		kat.setOpaque(true);
		kat.addMouseListener(this);
		maz.add(kat);
		gon.addActionListener(this);
		goc.addActionListener(this);
		for(int x = 1;x<9;x++){
			JPanel j = new JPanel();
			j.setPreferredSize(new Dimension(25,25));
			j.setOpaque(true);
			j.setBackground(Color.black);
			maz.add(j);
			blocs.add(j);
			name.add("");
			blocs.get(x).addMouseListener(this);
		}
		blocs.get(8).setBackground(Color.cyan);
		name.remove(8);
		blocs.get(8).add(maus);
		name.add("M");
		mazens.add(maz);
		nwe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		nwi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + KeyEvent.SHIFT_DOWN_MASK));
		gon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		goc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + KeyEvent.SHIFT_DOWN_MASK));
		hlp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		add(mazens,BorderLayout.CENTER);
		add(tools,BorderLayout.EAST);
		pack();
		setVisible(true);
		int wifd = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wifd,hig);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public mazer(int w, int l){
		super("Cats, and Mouses, and Mazes! Oh my!");
		wooo.setToolTipText("Drag mouse over squares.  w for white, b for black, c for cat, m for mouse.  'n' to toggle");
		wooo.setMnemonic('n');
		wooo.addActionListener(this);
		tools.add(wooo);
		tools.setFloatable(false);
		white.addActionListener(this);
		black.addActionListener(this);
		cyan.addActionListener(this);
		yellow.addActionListener(this);
		white.setAccelerator(KeyStroke.getKeyStroke('w'));
		black.setAccelerator(KeyStroke.getKeyStroke('b'));
		cyan.setAccelerator(KeyStroke.getKeyStroke('m'));
		yellow.setAccelerator(KeyStroke.getKeyStroke('c'));
		menu.add(niw);
		niw.add(nwe);
		niw.add(nwi);
		//menu.add(fle);
		menu.add(run);
		menu.add(halp);
		halp.add(hlp);
		color.add(yellow);
		color.add(cyan);
		color.add(black);
		color.add(white);
		menu.add(color);
		hlp.addActionListener(this);
		run.add(gon);
		run.add(goc);
		nwe.addActionListener(this);
		nwi.addActionListener(this);
		menubar.setLayout(new BoxLayout(menubar,BoxLayout.X_AXIS));
		mazens.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setLayout(new BorderLayout());
		menubar.add(menu);
		add(menu,BorderLayout.NORTH);
		maz.setPreferredSize(new Dimension(w*25,l*25));
		maz.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		blocs.add(kat);
		name.add("C");
		kat.add(ca);
		kat.setPreferredSize(new Dimension(25,25));
		kat.setBackground(Color.yellow);
		kat.setOpaque(true);
		kat.addMouseListener(this);
		//kat.setBorder(BorderFactory.createLineBorder(Color.black,1));
		maz.add(kat);
		gon.addActionListener(this);
		goc.addActionListener(this);
		//neu.setLayout(new BoxLayout(neu,BoxLayout.X_AXIS));
		//neu.add(wi);
		//neu.add(by);
		//neu.add(le);
		//but.setLayout(new BoxLayout(but,BoxLayout.X_AXIS));
		//but.add(go);
		//but.add(nw);
		//bot.setLayout(new BorderLayout());
		//bot.add(neu,BorderLayout.NORTH);
		//bot.add(but, BorderLayout.SOUTH);
		for(int x = 1;x<w*l;x++){
			JPanel j = new JPanel();
			//JLabel f = new JLabel("");
			j.setPreferredSize(new Dimension(25,25));
			j.setOpaque(true);
			j.setBackground(Color.black);
			//j.add(f);
			//j.setBorder(BorderFactory.createLineBorder(Color.black,1));
			maz.add(j);
			blocs.add(j);
			name.add("");
			blocs.get(x).addMouseListener(this);
		}
		blocs.get(blocs.size()-1).setBackground(Color.cyan);
		name.remove(blocs.size()-1);
		name.add("M");
		//name.remove(8);
		//name.add(new JLabel(new ImageIcon("mouse.png")));
		blocs.get(blocs.size()-1).add(new JLabel(new ImageIcon("mouse.png")));
		//name.add("M");
		mazens.add(maz);
		nwe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		nwi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + KeyEvent.SHIFT_DOWN_MASK));
		gon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		goc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + KeyEvent.SHIFT_DOWN_MASK));
		hlp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		//mazens.add(bot);
		add(mazens,BorderLayout.CENTER);
		add(tools,BorderLayout.EAST);
		//blocs.get(8).getComponent(1);
		//go.addActionListener(this);
		//nw.addActionListener(this);
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
		wid = w;
		len = l;
	}

	public void counter(){
		//cats=0;
		mice=0;
		for(int z = 0;z<name.size();z++){
			if(name.get(z)=="M"){
				mice++;
			//}else if(name.get(z)=="C"){
				//cats++;
			}
		}
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent g) {
		if(g.getSource().equals(gon)){
			debug=false;
			counter();
			if(mice==1){
				cango=true;
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
		}else if (g.getSource()==goc){
			debug=true;
			counter();
			//if(debug)
				//System.out.println(cats + " " + mice);
			if(mice==1){
				cango=true;
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
			debug=false;
		}else if(g.getSource()==nwe){
			dispose();
			mazer n = new mazer();
		}else if(g.getSource()==nwi){
			dispose();
			newey n = new newey();
		}else if(g.getSource()==hlp){
			help h = new help();
		}else if(g.getSource()==wooo){
			if(isdragging){
				isdragging=false;
			}else{
				keypressed=0;
				isdragging=true;
			}
		}else if(g.getSource()==white){
			keypressed=4;
		}else if(g.getSource()==black){
			keypressed=3;
		}else if(g.getSource()==cyan){
			keypressed=2;
		}else if(g.getSource()==yellow){
			keypressed=1;
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
			name.remove(blocs.indexOf(k));
			name.add(blocs.indexOf(k), "C");
			k.removeAll();
			k.add(ca);
		}else if(e.getComponent().getBackground().equals(Color.yellow)){
			e.getComponent().setBackground(Color.cyan);
			//setMouse(k);
			name.remove(blocs.indexOf(k));
			name.add(blocs.indexOf(k), "M");
			k.setVisible(false);
			k.removeAll();
			k.add(maus);
			k.setVisible(true);
			//k.add(new JLabel(new ImageIcon("mouse.png")));
		}else if(e.getComponent().getBackground().equals(Color.cyan)){
			e.getComponent().setBackground(Color.black);
			name.remove(blocs.indexOf(k));
			name.add(blocs.indexOf(k), "");
			k.removeAll();
			k.add(blnk);
		}else if(e.getComponent().getBackground().equals(Color.black)){
			e.getComponent().setBackground(Color.white);
			k.removeAll();
			k.add(blnk);
			name.remove(blocs.indexOf(k));
			name.add(blocs.indexOf(k), "");
		}
		if(debug)
			System.out.println(name);
	}
	
	public void test() throws IOException{
		in="";
		if(debug)
			System.out.println(wid + " " + len);
		for(int d=0;d<blocs.size();d++){
			if(blocs.get(d).getBackground().equals(Color.black)){
				in+="#";
			}else if(blocs.get(d).getBackground().equals(Color.yellow)&&name.get(d).equals("C")){
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
		
		if(debug){
			System.out.println(m2.toString());
			System.out.println(name);
			System.out.println(blocs.size() + " " + name.size());
		}
		System.out.println(m2.toString());
		maz.setVisible(false);
		translate(m2.gitDun());
		//translate(m2.gitDun());
		maz.setVisible(true);
		//name.clear();
	}
	
	//public void setName(JPanel h, String u){
		
	//}
	
	public void translate(char[][] ma){
		int us = 0;
		for(int f=0;f<len;f++){
			for(int s=0;s<wid;s++){
				if(debug)
					System.out.println(s + " " + f + " " + ma[s][f]);
				switch(ma[s][f]){
				case 'C': blocs.get(us).setBackground(Color.yellow);
				blocs.get(us).removeAll();		
				blocs.get(us).add(new JLabel(new ImageIcon("cat.png")));
				name.remove(us);
				name.add(us, "C");
						break;
				case ' ': blocs.get(us).setBackground(Color.white);
				blocs.get(us).removeAll();
				//blocs.get(us).add(blnk);
						break;
				case 'M': blocs.get(us).setBackground(Color.cyan);
					blocs.get(us).removeAll();		
					blocs.get(us).add(new JLabel(new ImageIcon("mouse.png")));
					name.remove(us);
					name.add(us, "M");
						break;
				//case '#': blocs.get(us).setBackground(Color.black);
				//blocs.get(us).removeAll();
				//blocs.get(us).add(blnk);
						//break;
				case 'O': blocs.get(us).setBackground(Color.yellow);
				blocs.get(us).removeAll();		
				blocs.get(us).add(new JLabel(new ImageIcon("paws.png")));
				if(debug)
					System.out.println("blank");
				//blocs.get(us).removeAll();
				//blocs.get(us).add(blnk);
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

	public void mouseEntered(MouseEvent e) {
		JPanel k = (JPanel) e.getComponent();
		System.out.println(blocs.indexOf(k)+ " " +isdragging + " " + keypressed);
		if(isdragging){
			switch(keypressed){
			case 1:
				e.getComponent().setBackground(Color.yellow);
				name.remove(blocs.indexOf(k));
				name.add(blocs.indexOf(k), "C");
				k.removeAll();
				k.add(ca);
				break;
			case 2:
				e.getComponent().setBackground(Color.cyan);
				name.remove(blocs.indexOf(k));
				name.add(blocs.indexOf(k), "M");
				k.setVisible(false);
				k.removeAll();
				k.add(maus);
				k.setVisible(true);
				break;
			case 3:
				e.getComponent().setBackground(Color.black);
				name.remove(blocs.indexOf(k));
				name.add(blocs.indexOf(k), "");
				k.removeAll();
				k.add(blnk);
				break;
			case 4:
				e.getComponent().setBackground(Color.white);
				k.removeAll();
				k.add(blnk);
				name.remove(blocs.indexOf(k));
				name.add(blocs.indexOf(k), "");
				break;
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}	
}
