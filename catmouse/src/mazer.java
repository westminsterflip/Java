import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class mazer extends JFrame implements ActionListener,MouseListener{
	JPanel maz = new JPanel();
	ArrayList<JPanel> blocs = new ArrayList<JPanel>(); 
	String in;
	ArrayList<JLabel> name = new ArrayList<JLabel>();
	JPanel kat = new JPanel();
	JLabel ca=new JLabel("C");
	int len = 3, wid = 3;
	JTextField le = new JTextField(3);
	JTextField wi = new JTextField(3);
	JLabel by = new JLabel("X");
	JPanel neu = new JPanel();
	JButton nw = new JButton("New");
	JButton go = new JButton("GO");
	
	public mazer(){
		super("Cats, and Mouses, and Mazes! Oh my!");
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		maz.setPreferredSize(new Dimension(66,66));
		maz.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		int y = 0;
		int z = 1;
		blocs.add(kat);
		name.add(ca);
		kat.add(ca);
		kat.setPreferredSize(new Dimension(20,20));
		kat.setBackground(Color.gray);
		kat.setOpaque(true);
		kat.addMouseListener(this);
		//kat.setBorder(BorderFactory.createLineBorder(Color.black,1));
		maz.add(kat);
		neu.setLayout(new BoxLayout(neu,BoxLayout.X_AXIS));
		neu.add(wi);
		neu.add(by);
		neu.add(le);
		neu.add(nw);
		for(int x = 1;x<9;x++){
			JPanel j = new JPanel();
			JLabel f = new JLabel("");
			j.setPreferredSize(new Dimension(20,20));
			j.setOpaque(true);
			j.setBackground(Color.red);
			j.add(f);
			//j.setBorder(BorderFactory.createLineBorder(Color.black,1));
			maz.add(j);
			if(z==3){
				z=0;
				y++;
			}
			blocs.add(j);
			name.add(f);
			blocs.get(x).addMouseListener(this);
			z++;
		}
		blocs.get(8).setBackground(Color.cyan);
		name.get(8).setText("M");
		neu.setLayout(new BoxLayout(neu,BoxLayout.X_AXIS));
		neu.add(wi);
		neu.add(by);
		neu.add(le);
		neu.add(nw);
		add(maz);
		add(go);
		add(neu);
		go.addActionListener(this);
		nw.addActionListener(this);
		//setPreferredSize(new Dimension(50,50));
		pack();
		setVisible(true);
		//System.out.println(maz.getHeight() + " " + blocs.get(0).getHeight() + " " + getWidth());
	}
	
	public mazer(int w, int l){
		super("Cats, and Mouses, and Mazes! Oh my!");
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		maz.setPreferredSize(new Dimension(w*20,l*20));
		maz.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		int y = 0;
		int z = 1;
		blocs.add(kat);
		name.add(ca);
		kat.add(ca);
		kat.setPreferredSize(new Dimension(20,20));
		kat.setBackground(Color.gray);
		kat.setOpaque(true);
		kat.addMouseListener(this);
		//kat.setBorder(BorderFactory.createLineBorder(Color.black,1));
		maz.add(kat);
		for(int x = 1;x<w*l;x++){
			JPanel j = new JPanel();
			JLabel f = new JLabel("");
			j.setPreferredSize(new Dimension(20,20));
			j.setOpaque(true);
			j.setBackground(Color.red);
			j.add(f);
			//j.setBorder(BorderFactory.createLineBorder(Color.black,1));
			maz.add(j);
			if(z==3){
				z=0;
				y++;
			}
			blocs.add(j);
			name.add(f);
			blocs.get(x).addMouseListener(this);
			z++;
		}
		blocs.get(8).setBackground(Color.cyan);
		name.get(8).setText("M");
		add(maz);
		add(go);
		add(neu);
		go.addActionListener(this);
		nw.addActionListener(this);
		//setPreferredSize(new Dimension(50,50));
		pack();
		setVisible(true);
		//System.out.println(maz.getHeight() + " " + blocs[0][0].getHeight() + " " + getWidth());
		len=l;
		wid=w;
	}

	public void actionPerformed(ActionEvent g) {
		if(g.getSource().equals(go)){
			try {
				test();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			mazer n = new mazer(Integer.parseInt(wi.getText()),Integer.parseInt(le.getText()));
		}
	}
	
	public static void main(String[] yay){
		mazer y = new mazer();
	}

	public void mouseClicked(MouseEvent e) {
		JPanel k = (JPanel) e.getComponent();
		if(e.getComponent().getBackground().equals(Color.green)){
			e.getComponent().setBackground(Color.gray);
			//setCat(k);
			setName(k,"C");
		}else if(e.getComponent().getBackground().equals(Color.gray)){
			e.getComponent().setBackground(Color.cyan);
			//setMouse(k);
			setName(k,"M");
		}else if(e.getComponent().getBackground().equals(Color.cyan)){
			e.getComponent().setBackground(Color.red);
			setName(k,"");
		}else if(e.getComponent().getBackground().equals(Color.red)){
			e.getComponent().setBackground(Color.green);
			setName(k,"");
		}
		
	}
	
	public void test() throws IOException{
		in="";
		//System.out.println(wid + " " + len);
		for(int d=0;d<blocs.size();d++){
			if(blocs.get(d).getBackground().equals(Color.red)){
				in+="#";
			}else if(blocs.get(d).getBackground().equals(Color.gray)){
				in+="C";
			}else if(blocs.get(d).getBackground().equals(Color.green)){
				in+=" ";
			}else if(blocs.get(d).getBackground().equals(Color.cyan)){
				in+="M";
			}
		}
		//System.out.println(in);
		maze_2 m2 = new maze_2(in,wid,len);
		translate(m2.gitDun());
	}
	
	public void setName(JPanel h, String u){
		name.get(blocs.indexOf(h)).setText(u);
	}
	
	public void translate(char[][] ma){
		int us = 0;
		for(int f=0;f<len;f++){
			for(int s=0;s<wid;s++){
				switch(ma[s][f]){
				case 'C': blocs.get(us).setBackground(Color.gray);
						name.get(us).setText("C");
						break;
				case ' ': blocs.get(us).setBackground(Color.green);
				name.get(us).setText("");
						break;
				case 'M': blocs.get(us).setBackground(Color.cyan);
				name.get(us).setText("M");
						break;
				case '#': blocs.get(us).setBackground(Color.red);
				name.get(us).setText("");
						break;
				case '+': blocs.get(us).setBackground(Color.gray);
				name.get(us).setText("");
						break;
				}
				us++;
			}
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
