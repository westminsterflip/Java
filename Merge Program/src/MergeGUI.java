import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class MergeGUI extends JFrame implements ActionListener{
	Scanner s1;
	JLabel ar1 = new JLabel("Array 1: "),ar2 = new JLabel("Array 2: "),ar3 = new JLabel("Result: ");
	ArrayList<Integer> a = new ArrayList<Integer>();
	ArrayList<Integer> b = new ArrayList<Integer>();
	JTextField arr1 = new JTextField(10);
	JTextField arr2 = new JTextField(10);
	JTextField arr3 = new JTextField(35);
	JButton go = new JButton("Initiate Scienceing.");
	JButton clear = new JButton("Clear");
	
	public MergeGUI(String title){
		super(title);
		setLayout(new FlowLayout());
		add(ar1);
		add(arr1);
		add(ar2);
		add(arr2);
		add(go);
		add(ar3);
		add(arr3);
		add(clear);
		arr3.setEditable(false);
		go.setActionCommand("go");
		clear.setActionCommand("clear");
		go.addActionListener(this);
		clear.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("go")){
			array1In();
			godostuff();
		}else{
			arr1.setText(null);
			arr2.setText(null);
			arr3.setText(null);
		}
	}

	public void array1In(){
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

	public ArrayList<Integer> sortThose(){
		ArrayList<Integer> a1 = a, a2 = b, a3 = new ArrayList<Integer>();
		int t1 = 0, t2 = 0,pi=(int)(Math.PI*1000000);
		int c,d;
		if(a1.size()==1 && a2.size()==1){
			if(a1.get(0)<a2.get(0)){
				a3.add(a1.get(0));
				a3.add(a2.get(0));
			}else{
				a3.add(a2.get(0));
				a3.add(a1.get(0));
			}}else{
				for(int w3=0;w3!=pi;){
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
					w3+=2;
				}else if((t1<t2||a2.isEmpty())&&a1.indexOf(t1)!=-1){
					if(a3.indexOf(t1)==-1){
						a3.add(t1);
					}
					a1.remove(a1.indexOf(t1));
					w3++;
				}else if ((t2<t1||a1.isEmpty())&&a2.indexOf(t2)!=-1){
					if(a3.indexOf(t2)==-1){
						a3.add(t2);
					}
					w3++;
					a2.remove(a2.indexOf(t2));
				}else w3=pi;
			}
		}
		
		return a3;
	}

	public void godostuff(){
		String output = '\r'+"";
		ArrayList<Integer> a5 = sortThose();
		for(int t=0;t < a5.size();t++){
			output += a5.get(t) + " ";
		}
		arr3.setText(output);
		System.out.println(output);
	}
	
	public static void main(String[] a){
		MergeGUI g1 = new MergeGUI("Science");
		g1.setBounds(390,150,500,500);
		g1.setVisible(true);
	}
}
