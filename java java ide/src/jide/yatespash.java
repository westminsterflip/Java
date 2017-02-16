package jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class yatespash extends JWindow{
	public yatespash(){
		ImageBackgroundPanel cont = new ImageBackgroundPanel();
		this.setContentPane(cont);
		int wid=jidest.scrSize.width/4,hig=jidest.scrSize.height/4;
		setBounds((jidest.scrSize.width-wid)/2,(jidest.scrSize.height-hig)/2,wid,hig);
		JLabel title = new JLabel("     YATE");
		title.setFont(new Font("Univers",Font.PLAIN,110));
		title.setForeground(Color.lightGray);
		cont.add(title,BorderLayout.NORTH);
		setVisible(true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setVisible(false);
	}
	
	class ImageBackgroundPanel extends JPanel{
		Image image;
		public ImageBackgroundPanel(){
		try{
			image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images\\splash.png"))).getImage();
		}catch (Exception e) {e.printStackTrace(System.out);}
	}
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
				if (image != null)
					g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
		}
	}
}
