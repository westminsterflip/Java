import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
@SuppressWarnings("serial")
public class guip extends JFrame implements ActionListener,ChangeListener{
	JSlider sl = new JSlider(JSlider.HORIZONTAL,2,30,2);
	JTextField arrfin = new JTextField(37);
	JButton go = new JButton("Merge");
	JButton clear = new JButton("Clear");
	JPanel arras = new JPanel();
	JPanel arrafin = new JPanel();
	JPanel buttons = new JPanel();
	JPanel slider = new JPanel();
	JPanel top = new JPanel();
	JPanel odd = new JPanel();
	JPanel even = new JPanel();
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	ArrayList<Integer> a1 = new ArrayList<Integer>();
	ArrayList<Integer> a2 = new ArrayList<Integer>();
	ArrayList<Integer> a3 = new ArrayList<Integer>();
	ArrayList<JLabel> labels = new ArrayList<JLabel>();
	ArrayList<JTextField> text = new ArrayList<JTextField>();
	ArrayList<JPanel> panels = new ArrayList<JPanel>();
	int c,d,t1 = 0,t2 = 0,numact = 1;
	
	
	public guip(){
		super("Array Merger");
		for(int x=1;x<3;x++){
			JPanel c = new JPanel();
			c.setLayout(new FlowLayout());
			JLabel a = new JLabel("Array " + x + ": ");
			labels.add(a);
			JTextField b = new JTextField(15);
			text.add(b);
			c.add(a);
			c.add(b);
			panels.add(c);
		}
		JLabel fin = new JLabel("Result: ");
		JLabel sli = new JLabel("Number of Arrays: ");
		setResizable(false);
		setLayout(new BorderLayout());
		slider.setLayout(new BorderLayout());
		slider.add(sli,BorderLayout.NORTH);
		slider.add(sl,BorderLayout.SOUTH);
		arrafin.add(fin); arrafin.add(arrfin);
		buttons.add(go); buttons.add(clear);
		arras.setLayout(new BorderLayout());
		top.setLayout(new BorderLayout());
		odd.setLayout(new BoxLayout(odd,BoxLayout.Y_AXIS));
		even.setLayout(new BoxLayout(even,BoxLayout.Y_AXIS));
		odd.add(panels.get(0)); even.add(panels.get(1));
		arras.add(odd,BorderLayout.WEST);
		arras.add(even,BorderLayout.EAST);
		top.add(slider,BorderLayout.NORTH);
		top.add(arras,BorderLayout.SOUTH);
		add(arrafin,BorderLayout.CENTER); 
		add(buttons,BorderLayout.SOUTH);
		sl.setPaintLabels(true);
		sl.setMajorTickSpacing(4);
		sl.setMinorTickSpacing(1);
		sl.setPaintTicks(true);
		sl.setSnapToTicks(true);
		arrfin.setEditable(false);
		go.setActionCommand("go");
		clear.setActionCommand("clear");
		go.addActionListener(this);
		clear.addActionListener(this);	
		setVisible(true);
		sl.addChangeListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(top,BorderLayout.NORTH); 
		pack();
		int wid = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wid,hig);
		
	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		int num = source.getValue();
		System.out.println(numact + " and " + num);
		Integer x;
		if(num>panels.size()){
			for(x=panels.size()+1;x<=num;x++){
				JPanel c = new JPanel();
				c.setLayout(new FlowLayout());
				JLabel a = new JLabel("Array " + x + ": ");
				labels.add(a);
				JTextField b = new JTextField(15);
				text.add(b);
				c.add(a);
				c.add(b);
				panels.add(c);
				if(Integer.numberOfTrailingZeros(x)==0){
					System.out.println("odd");
					odd.add(panels.get(x-1));
				}else{
					System.out.println("even");
					even.add(panels.get(x-1));
				}
				pack();
				numact = panels.size()-1;
			}
		}else{
			
		}
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("go")){
			handler();
		}else{
			for(int x=0;x<=numact;x++){
				text.get(x).setText(null);
			}
			arrfin.setText(null);
		}
	}

	public void arrayIn(int field){
		Scanner s1 = new Scanner(text.get(field-1).getText());
		for(;s1.hasNext()==true;){
			//System.out.println("Input Running" + text.get(field-1).getText());
			for(;s1.hasNextInt()==true;){
				a1.add(s1.nextInt());
			}
			if(s1.hasNext()==true){
				s1.next();
			}
		}
		s1.close();
	}

	@SuppressWarnings("unchecked")
	public void handler(){
		arrayIn(1);
		a2=(ArrayList<Integer>) a1.clone();
		for(int x=2;x<=numact+1;x++){
			//System.out.println("Handler running: ");
			a1.clear();
			arrayIn(x);
			//System.out.println("a1 " + a1 +"a2 "+ a2 + "a3 " + a3);
			sortThose();
			
		}
	}
	
	public void sortThose(){
		//System.out.println(a1 + " and "+ a2);
		//System.out.println("Sorter running");
		if(a1.isEmpty()==true&&a2.isEmpty()==true)
		{
			for(int x=0;x<a3.size();x++){
				a2.add(a3.get(x));
			}
			godostuff();
		}else{
			if(a1.isEmpty()==false){
				t1=a1.get(0);
				for(c=0;c< a1.size();c++){
					if (t1>a1.get(c)){
						t1 = a1.get(c);
					}
				}
			}
			
			if(a2.isEmpty()==false){
				t2 = a2.get(0);
				for(d=0;d<a2.size();d++){
					if (t2>a2.get(d)){
						t2 = a2.get(d);
					}
				}
			}
			
			if(t1==t2&&(a1.indexOf(t1)!=-1||a2.indexOf(t2)!=-1)){
				if(a1.indexOf(t1)!=-1){
					a1.remove(a1.indexOf(t1));
				}
				if(a2.indexOf(t2)!=-1){
					a2.remove(a2.indexOf(t2));
				}
				if(a3.indexOf(t1)==-1){
					a3.add(t1);
				}
			}else if((t1<t2||a2.isEmpty())&&a1.indexOf(t1)!=-1){
					if(a3.indexOf(t1)==-1){
						a3.add(t1);
					}
					a1.remove(a1.indexOf(t1));
			}else if ((t2<t1||a1.isEmpty())&&a2.indexOf(t2)!=-1){
					if(a3.indexOf(t2)==-1){
						a3.add(t2);
					}
					a2.remove(a2.indexOf(t2));
			}
			sortThose();
		}
	}

	public void godostuff(){
		//System.out.println("output running " + a3);
		String output = '\r'+"";
		for(int t=0;t < a3.size();t++){
			output += a3.get(t) + " ";
		}
		a3.clear();
		arrfin.setText(output);
	}
	

	public static void main(String[] a){
		@SuppressWarnings("unused")
		guip g1 = new guip();
	}

}

