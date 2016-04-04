import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class mazer extends JFrame implements ActionListener,MouseListener{
	JPanel maz = new JPanel();
	JPanel[][] blocs;
	JLabel[][] name;
	JPanel kat = new JPanel();
	JLabel ca=new JLabel("C");
	int len = 3, wid = 3;
	int row,x;
	int[] cat = {0,0},mouse = {0,0};
	ArrayList<Integer[]> dead = new ArrayList<Integer[]>();
	ArrayList<Integer[]> path = new ArrayList<Integer[]>();
	
	public mazer(){
		super("Cats, and Mouses, and Mazes! Oh my!");
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		maz.setPreferredSize(new Dimension(66,66));
		maz.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		blocs = new JPanel[3][3];
		name = new JLabel[3][3];
		cat[0] = 0;
		cat[1]= 0;
		mouse[0] = 2;
		mouse[1] = 2;
		int y = 0;
		int z = 1;
		blocs[0][0]=kat;
		name[0][0]=ca;
		kat.add(ca);
		kat.setPreferredSize(new Dimension(20,20));
		kat.setBackground(Color.gray);
		kat.setOpaque(true);
		kat.addMouseListener(this);
		kat.setBorder(BorderFactory.createLineBorder(Color.black,1));
		maz.add(kat);
		for(int x = 0;x<8;x++){
			JPanel j = new JPanel();
			JLabel f = new JLabel("");
			j.setPreferredSize(new Dimension(20,20));
			j.setOpaque(true);
			j.setBackground(Color.red);
			j.add(f);
			j.setBorder(BorderFactory.createLineBorder(Color.black,1));
			maz.add(j);
			if(z==3){
				z=0;
				y++;
			}
			blocs[z][y]=j;
			name[z][y]=f;
			blocs[z][y].addMouseListener(this);
			z++;
		}
		blocs[2][2].setBackground(Color.green);
		add(maz);
		//setPreferredSize(new Dimension(50,50));
		pack();
		setVisible(true);
		System.out.println(maz.getHeight() + " " + blocs[0][0].getHeight() + " " + getWidth());
	}

	public void actionPerformed(ActionEvent g) {
	}
	
	public static void main(String[] yay){
		mazer y = new mazer();
	}

	public void mouseClicked(MouseEvent e) {
		JPanel k = (JPanel) e.getComponent();
		if(e.getComponent().getBackground().equals(Color.green)){
			e.getComponent().setBackground(Color.gray);
			setName(k,"C");
		}else if(e.getComponent().getBackground().equals(Color.gray)){
			e.getComponent().setBackground(Color.cyan);
			setName(k,"m");
		}else if(e.getComponent().getBackground().equals(Color.cyan)){
			e.getComponent().setBackground(Color.red);
			setName(k,"");
		}else if(e.getComponent().getBackground().equals(Color.red)){
			e.getComponent().setBackground(Color.green);
			setName(k,"");
		}
		
	}
	
	public void setName(JPanel h, String u){
		for(int y=0;y<len;y++){
			for(int x=0;x<wid;x++){
				if(h==blocs[x][y])
					name[x][y].setText(u);
			}
		}
	}

	
	public boolean canDown(int z, int y){
		boolean cand;
		try{
			cand = blocs[z][y+1].getBackground().equals(Color.green);
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canUp(int z, int y){
		boolean cand;
		try{
			cand = blocs[z][y-1].getBackground().equals(Color.green);
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canLeft(int z, int y){
		boolean cand;
		try{
			cand = blocs[z-1][y].getBackground().equals(Color.green);
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public boolean canRight(int z, int y){
		boolean cand;
		try{
			cand = blocs[z+1][y].getBackground().equals(Color.green);
		}
		catch(ArrayIndexOutOfBoundsException h){
			cand = false;
		}
		return cand;
	}
	
	public int canTowards(int z, int y){
		int hor=mouse[0]-z;
		int ver=mouse[1]-y;
		if(Math.abs(hor)<Math.abs(ver)){
			if(ver<0){
				if(canUp(z,y))
					return 1;
				else
					return 0;
			}else if(ver>0){
				if(canDown(z,y))
					return 2;
				else
					return 0;
			}else{
				return 0;
			}
		}else{
			if(hor<0){
				if(canLeft(z,y))
					return 3;
				else 
					return 0;
			}else if(hor>0){
				if(canRight(z,y))
					return 4;
				else 
					return 0;
			}else
				return 0;
		}
	}
	
	public void filler(){
		for(int y=0;y<row;y++){
			for(int c=0;c<x;c++){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(blocs[c][y].getBackground().equals(Color.green)){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=blocs[c+1][y].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||blocs[c-1][y].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||blocs[c][y+1].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||blocs[c][y-1].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							blocs[c][y].getBackground().equals(Color.blue);
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//System.out.println(toString());
						blocs[c][y].getBackground().equals(Color.blue);
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
		for(int y=row-1;y>=0;y--){
			for(int c=x-1;c>=0;c--){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(blocs[c][y].getBackground().equals(Color.green)){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=blocs[c+1][y].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||blocs[c-1][y].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||blocs[c][y+1].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||blocs[c][y-1].getBackground().equals(Color.blue);
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							blocs[c][y].getBackground().equals(Color.blue);
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//System.out.println(toString());
						blocs[c][y].getBackground().equals(Color.blue);
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
		for(int y=row-1;y>=0;y--){
			for(int c=0;c<x;c++){
				//System.out.println(toString());
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=maze[c+1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c-1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y+1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y-1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						//System.out.println(toString());
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
		for(int y=0;y<row;y++){
			for(int c=x-1;c>=0;c--){
				boolean d = canDown(c,y),u=canUp(c,y),l=canLeft(c,y),r=canRight(c,y);
				if(maze[c][y]==' '){
					boolean d1=c+1==cat[0]&&y==cat[1]||c-1==cat[0]&&y==cat[1]||y+1==cat[1]&&c==cat[0]||y-1==cat[1]&&c==cat[0];
					boolean d2=c+1==mouse[0]&&y==mouse[1]||c-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&c==mouse[0]||y-1==mouse[1]&&c==mouse[0];
					boolean d3=true;
					try{
						d3=maze[c+1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c-1][y]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y+1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					try{
						d3=d3||maze[c][y-1]=='f';
					}catch(ArrayIndexOutOfBoundsException r1){}
					
					if(d1||d2){
						//System.out.println(d1 + " " + d2);
						if(d1&&d2){}//System.out.println("ff");}
						else{
						if(d3&&!(d||u||l||r)){
							//System.out.println(toString());
							maze[c][y]='f';
							dead.add(new Integer[]{c,y});
						}
						}
					}
					else
					if(!d&&!u&&!l||!d&&!u&&!r||!u&&!l&&!r||!d&&!l&&!r){
						maze[c][y]='f';
						dead.add(new Integer[]{c,y});
					}
				}
			}	
		}
	}
	
	public void tracer(int z,int y){
		boolean d = canDown(z,y),u=canUp(z,y),l=canLeft(z,y),r=canRight(z,y);
		if(z+1==mouse[0]&&y==mouse[1]||z-1==mouse[0]&&y==mouse[1]||y+1==mouse[1]&&z==mouse[0]||y-1==mouse[1]&&z==mouse[0]){
			System.out.println('\r');
		}else if(canTowards(z,y)!=0){
			switch (canTowards(z,y)){
				case 1: maze[z][y-1] = '+';
						path.add(new Integer[]{z,y-1});
						tracer(z,y-1);
						break;
				case 2: maze[z][y+1] = '+';
						path.add(new Integer[]{z,y+1});
						tracer(z,y+1);
						break;
				case 3: maze[z-1][y] = '+';
						path.add(new Integer[]{z-1,y});
						tracer(z-1,y);
						break;
				case 4: maze[z+1][y] = '+';
						path.add(new Integer[]{z+1,y});
						tracer(z+1,y);
						break;
			}
		}else if(d){
			maze[z][y+1] = '+';
			path.add(new Integer[]{z,y+1});
			tracer(z,y+1);
		}else if(u){
			maze[z][y-1] = '+';
			path.add(new Integer[]{z,y-1});
			tracer(z,y-1);
		}else if(l){
			maze[z-1][y] = '+';
			path.add(new Integer[]{z-1,y});
			tracer(z-1,y);
		}else if(r){
			maze[z+1][y] = '+';
			path.add(new Integer[]{z+1,y});
			tracer(z+1,y);
		}else if(!path.isEmpty()){
			maze[z][y] = 'f';
			dead.add(new Integer[]{z,y});
			//System.out.println(toString());
			altclean();
			filler();
			//System.out.println("filled");
			//System.out.println(toString());
			mazerize();
		}else{
			System.out.println('\r' + "No Path");
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
