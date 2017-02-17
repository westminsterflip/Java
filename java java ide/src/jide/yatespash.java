package jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import jide.jidest;

@SuppressWarnings("serial")
public class yatespash extends JWindow{
	public yatespash() throws FileNotFoundException{
		ImageBackgroundPanel cont = new ImageBackgroundPanel(this.getClass().getResource("/images"+File.separator+"splash.png"));
		//this.setContentPane(cont);
		add(cont);
		int wid=jidest.scrSize.width/4,hig=jidest.scrSize.height/4;
		setBounds((jidest.scrSize.width-wid)/2,(jidest.scrSize.height-hig)/2,wid,hig);
		JLabel title = new JLabel("     YATE");
		title.setFont(new Font("Univers",Font.PLAIN,110));
		title.setForeground(Color.lightGray);
		cont.add(title,BorderLayout.NORTH);
		setVisible(true);
		this.setAlwaysOnTop(true);
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//new mainwin();
		//setVisible(false);
	}
	
	public class ImageBackgroundPanel extends JPanel{
		Image image;
		public ImageBackgroundPanel(URL imagep){
		try{
			image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(imagep)).getImage();
		}catch (Exception e) {e.printStackTrace(System.out);}
	}
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
				if (image != null)
					g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
		}
	}
	
	public void nosplash(){
		dispose();
	}
}
