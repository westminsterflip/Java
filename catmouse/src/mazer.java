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
	int len = 3, wid = 3;
	public volatile static boolean cango=false;
	public static boolean debug = false;
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	JMenuBar menu = new JMenuBar();
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
	JMenuItem white = new JMenuItem("Empty"),black = new JMenuItem("Walls"),cyan = new JMenuItem("Mice"),yellow = new JMenuItem("Cats");
	
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
		wooo.setToolTipText("Drag mouse over squares.  w for white, b for black, c for cat, m for mouse. alt+n to toggle");
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
		maz.add(kat);
		gon.addActionListener(this);
		goc.addActionListener(this);
		for(int x = 1;x<w*l;x++){
			JPanel j = new JPanel();
			j.setPreferredSize(new Dimension(25,25));
			j.setOpaque(true);
			j.setBackground(Color.black);
			maz.add(j);
			blocs.add(j);
			name.add("");
			blocs.get(x).addMouseListener(this);
		}
		blocs.get(blocs.size()-1).setBackground(Color.cyan);
		name.remove(blocs.size()-1);
		name.add("M");
		blocs.get(blocs.size()-1).add(new JLabel(new ImageIcon("mouse.png")));
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
		wid = w;
		len = l;
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent g) {
		if(g.getSource().equals(gon)){
			debug=false;
			try {
				test();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (g.getSource()==goc){
			debug=true;
			try {
				test();
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
			name.remove(blocs.indexOf(k));
			name.add(blocs.indexOf(k), "C");
			k.removeAll();
			k.add(ca);
		}else if(e.getComponent().getBackground().equals(Color.yellow)){
			e.getComponent().setBackground(Color.cyan);
			name.remove(blocs.indexOf(k));
			name.add(blocs.indexOf(k), "M");
			k.setVisible(false);
			k.removeAll();
			k.add(maus);
			k.setVisible(true);
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
		maze_2 m2 = new maze_2(in,wid,len);
		if(debug){
			System.out.println(m2.toString());
			System.out.println(name);
			System.out.println(blocs.size() + " " + name.size());
		}
		maz.setVisible(false);
		translate(m2.gitDun());
		maz.setVisible(true);
	}
	
	public void translate(Character[][] ma){
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
						break;
				case 'M': blocs.get(us).setBackground(Color.cyan);
					blocs.get(us).removeAll();		
					blocs.get(us).add(new JLabel(new ImageIcon("mouse.png")));
					name.remove(us);
					name.add(us, "M");
						break;
				case 'O': blocs.get(us).setBackground(Color.yellow);
				blocs.get(us).removeAll();		
				blocs.get(us).add(new JLabel(new ImageIcon("paws.png")));
				if(debug)
					System.out.println("blank");
						break;
				}
				us++;
			}
		}
		
	}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
		JPanel k = (JPanel) e.getComponent();
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
	
	public void mouseExited(MouseEvent e) {
	}	
}
