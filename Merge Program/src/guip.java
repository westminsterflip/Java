import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class guip extends JFrame implements ActionListener{
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
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	ArrayList<Integer> a = new ArrayList<Integer>();
	ArrayList<Integer> a3 = new ArrayList<Integer>();
	ArrayList<Integer> b = new ArrayList<Integer>();
	int c,d,t1 = 0,t2 = 0;
	
	
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
		setResizable(false);
		setLayout(new BorderLayout());
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
		arras.add(arra1); arras.add(arra2);
		arras.add(arra3); arras.add(arra4);
		arras.add(arra5); arras.add(arra6);
		arras.add(arra7); arras.add(arra8);
		arras.add(arra9); arras.add(arra10);
		arra3.setVisible(false);
		arra4.setVisible(false);
		arra5.setVisible(false);
		arra6.setVisible(false);
		arra7.setVisible(false);
		arra8.setVisible(false);
		arra9.setVisible(false);
		arra10.setVisible(false);
		add(arra3,BorderLayout.CENTER); add(buttons,BorderLayout.SOUTH);
		arr3.setEditable(false);
		go.setActionCommand("go");
		clear.setActionCommand("clear");
		go.addActionListener(this);
		clear.addActionListener(this);	
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(sl);
		add(arras,BorderLayout.NORTH); 
		pack();
		int wid = (int)Math.round((scr.getWidth()-getWidth())/2);
		int hig = (int)Math.round((scr.getHeight()-getHeight())/2);
		setLocation(wid,hig);
		
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("go")){
			arrayIn();
			sortThose();
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
				a.add(s1.nextInt());
			}
			if(s1.hasNext()==true){
				s1.next();
			}
		}
		
		s1.close();
		
		Scanner s2 = new Scanner(arr2.getText());
		for(;s2.hasNext()==true;){
			for(;s2.hasNextInt()==true;){
				b.add(s2.nextInt());
			}
			if(s2.hasNext()==true){
				s2.next();
			}
		}
		s2.close();
	}

	public void sortThose(){
		System.out.println(a + " and "+ b);
		if(a.isEmpty()==true&&b.isEmpty()==true)
		{
			godostuff();
		}else{
			if(a.isEmpty()==false){
				t1=a.get(0);
				for(c=0;c< a.size();c++){
					if (t1>a.get(c)){
						t1 = a.get(c);
					}
				}
			}
			
			if(b.isEmpty()==false){
				t2 = b.get(0);
				for(d=0;d<b.size();d++){
					if (t2>b.get(d)){
						t2 = b.get(d);
					}
				}
			}
			
			if(t1==t2&&(a.indexOf(t1)!=-1||b.indexOf(t2)!=-1)){
				if(a.indexOf(t1)!=-1){
					a.remove(a.indexOf(t1));
				}
				if(b.indexOf(t2)!=-1){
					b.remove(b.indexOf(t2));
				}
				if(a3.indexOf(t1)==-1){
					a3.add(t1);
				}
			}else if((t1<t2||b.isEmpty())&&a.indexOf(t1)!=-1){
					if(a3.indexOf(t1)==-1){
						a3.add(t1);
					}
					a.remove(a.indexOf(t1));
			}else if ((t2<t1||a.isEmpty())&&b.indexOf(t2)!=-1){
					if(a3.indexOf(t2)==-1){
						a3.add(t2);
					}
					b.remove(b.indexOf(t2));
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
		arr3.setText(output);
	}
	

	public static void main(String[] a){
		@SuppressWarnings("unused")
		MergeGUI g1 = new MergeGUI();
	}
}

