import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class MergeGUI extends JFrame implements ActionListener{
	JLabel ar1 = new JLabel("Array 1: ");
	JLabel ar2 = new JLabel("Array 2: ");
	JLabel ar3 = new JLabel("Result: ");
	JTextField arr1 = new JTextField(10);
	JTextField arr2 = new JTextField(10);
	JTextField arr3 = new JTextField(35);
	JButton go = new JButton("Merge");
	JButton clear = new JButton("Clear");
	JPanel arra = new JPanel();
	JPanel arrb = new JPanel();
	JPanel arra3 = new JPanel();
	JPanel buttons = new JPanel();
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	ArrayList<Integer> a = new ArrayList<Integer>();
	ArrayList<Integer> a3 = new ArrayList<Integer>();
	ArrayList<Integer> b = new ArrayList<Integer>();
	int c,d,t1 = 0,t2 = 0;
	
	
	public MergeGUI(String title){
		super(title);
		setResizable(false);
		setLayout(new FlowLayout());
		arra.add(ar1); arra.add(arr1);
		arrb.add(ar2); arrb.add(arr2);
		arra3.add(ar3); arra3.add(arr3);
		buttons.add(go); buttons.add(clear);
		add(arra); add(arrb); add(arra3); add(buttons);
		arr3.setEditable(false);
		go.setActionCommand("go");
		clear.setActionCommand("clear");
		go.addActionListener(this);
		clear.addActionListener(this);
		int wid = (int)Math.round((scr.getWidth()-500)/2);
		int hig = (int)Math.round((scr.getHeight()-150)/2);
		setBounds(wid,hig,500,150); setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("go")){
			arrayIn();
			sortThose();
			godostuff();
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
		if((a.size()==1 && b.size()==0)){
			if(a3.indexOf(a.get(0))==-1){
				a3.add(a.get(0));
			}
		}else if(a.size()==0 && b.size()==1){
			if(a3.indexOf(b.get(0))==-1){
				a3.add(b.get(0));
			}
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
		arr3.setText(output);
	}
	

	public static void main(String[] a){
		@SuppressWarnings("unused")
		MergeGUI g1 = new MergeGUI("Array Merge");
	}
}
