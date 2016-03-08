import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
@SuppressWarnings("serial")
public class guip extends JFrame implements ActionListener,ChangeListener{
	JSlider sl = new JSlider(JSlider.HORIZONTAL,2,10,2);
	JTextField arr1 = new JTextField(15);
	JTextField arr2 = new JTextField(15);
	JTextField arr3 = new JTextField(15);
	JTextField arr4 = new JTextField(15);
	JTextField arr5 = new JTextField(15);
	JTextField arr6 = new JTextField(15);
	JTextField arr7 = new JTextField(15);
	JTextField arr8 = new JTextField(15);
	JTextField arr9 = new JTextField(15);
	JTextField arr10 = new JTextField(15);
	JTextField arrfin = new JTextField(37);
	JButton go = new JButton("Merge");
	JButton clear = new JButton("Clear");
	JPanel arra1 = new JPanel();
	JPanel arra2 = new JPanel();
	JPanel arra3 = new JPanel();
	JPanel arra4 = new JPanel();
	JPanel arra5 = new JPanel();
	JPanel arra6 = new JPanel();
	JPanel arra7 = new JPanel();
	JPanel arra8 = new JPanel();
	JPanel arra9 = new JPanel();
	JPanel arra10 = new JPanel();
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
	ArrayList<JPanel> panels = new ArrayList<JPanel>();
	int c,d,t1 = 0,t2 = 0,numact = 2;
	
	
	public guip(){
		super("Array Merger");
		JLabel ar1 = new JLabel("Array 1: ");
		JLabel ar2 = new JLabel("Array 2: ");
		JLabel ar3 = new JLabel("Array 3: ");
		JLabel ar4 = new JLabel("Array 4: ");
		JLabel ar5 = new JLabel("Array 5: ");
		JLabel ar6 = new JLabel("Array 6: ");
		JLabel ar7 = new JLabel("Array 7: ");
		JLabel ar8 = new JLabel("Array 8: ");
		JLabel ar9 = new JLabel("Array 9: ");
		JLabel ar10 = new JLabel("Array 10: ");
		JLabel fin = new JLabel("Result: ");
		JLabel sli = new JLabel("Number of Arrays: ");
		setResizable(false);
		setLayout(new BorderLayout());
		slider.setLayout(new BorderLayout());
		slider.add(sli,BorderLayout.NORTH);
		slider.add(sl,BorderLayout.SOUTH);
		arra1.add(ar1); arra1.add(arr1);
		arra2.add(ar2); arra2.add(arr2);
		arra3.add(ar3); arra3.add(arr3);
		arra4.add(ar4); arra4.add(arr4);
		arra5.add(ar5); arra5.add(arr5);
		arra6.add(ar6); arra6.add(arr6);
		arra7.add(ar7); arra7.add(arr7);
		arra8.add(ar8); arra8.add(arr8);
		arra9.add(ar9); arra9.add(arr9);
		arra10.add(ar10); arra10.add(arr10);
		arrafin.add(fin); arrafin.add(arrfin);
		buttons.add(go); buttons.add(clear);
		arras.setLayout(new BorderLayout());
		top.setLayout(new BorderLayout());
		odd.setLayout(new BoxLayout(odd,BoxLayout.Y_AXIS));
		even.setLayout(new BoxLayout(even,BoxLayout.Y_AXIS));
		odd.add(arra1); even.add(arra2);
		odd.add(arra3); even.add(arra4);
		odd.add(arra5); even.add(arra6);
		odd.add(arra7); even.add(arra8);
		odd.add(arra9); even.add(arra10);
		arras.add(odd,BorderLayout.WEST);
		arras.add(even,BorderLayout.EAST);
		arra3.setVisible(false);
		top.add(slider,BorderLayout.NORTH);
		top.add(arras,BorderLayout.SOUTH);
		arra4.setVisible(false);
		arra5.setVisible(false);
		arra6.setVisible(false);
		arra7.setVisible(false);
		arra8.setVisible(false);
		arra9.setVisible(false);
		arra10.setVisible(false);
		add(arrafin,BorderLayout.CENTER); 
		add(buttons,BorderLayout.SOUTH);
		sl.setPaintLabels(true);
		sl.setMajorTickSpacing(1);
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
		panels.addAll(Arrays.asList(arra3,arra4,arra5,arra6,arra7,arra8,arra9,arra10));
		JSlider source = (JSlider)e.getSource();
		int num = source.getValue()-3;
		System.out.println(num);
		if(num == -1){
			for(int x=0;x<8;x++){
				panels.get(x).setVisible(false);
				pack();
			}
		}else if(panels.get(num).isVisible()==true){
			for(int x=7;x>num;x--){
				panels.get(x).setVisible(false);
				pack();
			}
			System.out.println("no");
		}else{
			for(int x=0;x<=num;x++){
				panels.get(x).setVisible(true);
				pack();
			}
			System.out.println("yes");
		}
		numact=num+2;
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("go")){
			arrayIn();
			handler();
		}else{
			arr1.setText(null);
			arr2.setText(null);
			arr3.setText(null);
		}
	}

	public void arrayIn(){
		Scanner s1 = new Scanner(arr1.getText());
		for(;s1.hasNext()==true;){
			for(;s1.hasNextInt()==true;){
				a1.add(s1.nextInt());
			}
			if(s1.hasNext()==true){
				s1.next();
			}
		}
		
		s1.close();
		
		Scanner s2 = new Scanner(arr2.getText());
		for(;s2.hasNext()==true;){
			for(;s2.hasNextInt()==true;){
				a2.add(s2.nextInt());
			}
			if(s2.hasNext()==true){
				s2.next();
			}
		}
		s2.close();
	}

	public void handler(){
		sortThose();
		for(int x=0;){
			
		}
	}
	
	public void sortThose(){
		//System.out.println(a1 + " and "+ a2);
		if(a1.isEmpty()==true&&a2.isEmpty()==true)
		{
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

