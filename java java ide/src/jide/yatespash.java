package jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import jide.jidest;

@SuppressWarnings("serial")
public class yatespash extends JWindow{
	@SuppressWarnings("unused")
	public yatespash() throws FileNotFoundException{
		ImageBackgroundPanel cont = new ImageBackgroundPanel(this.getClass().getResource("/images\\splash.png"));
		this.setContentPane(cont);
		int wid=jidest.scrSize.width/4,hig=jidest.scrSize.height/4;
		setBounds((jidest.scrSize.width-wid)/2,(jidest.scrSize.height-hig)/2,wid,hig);
		JLabel title = new JLabel("     YATE");
		title.setFont(new Font("Univers",Font.PLAIN,110));
		title.setForeground(Color.lightGray);
		cont.add(title,BorderLayout.NORTH);
		setVisible(true);
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		String path = System.getProperty("user.home");
		switch(jidest.osc()){
		case 'w': path+="\\AppData\\Roaming\\YATE\\";
			if(System.getProperty("os.arch").equals("amd64"))
				jidest.is64=true;break;
		case 'm': path+="/Library/Application Support/YATE/";
			if(System.getProperty("os.arch").equals("ppc"))
				jidest.is64=true;break;
		case 'l': path+="/.YATE/";
			if(System.getProperty("os.arch").equals("amd64"))
				jidest.is64=true;break;
		default: path="";
		}
		jidest.YATE_FOLDER_PATH=path;
		jidest.settingsFile = new File(path+"Settings");
		if(!jidest.settingsFile.exists())
			try {
				jidest.settingsFile.createNewFile();
				PrintWriter ne = new PrintWriter(jidest.settingsFile);
				ne.println("window.size:0,0");
				ne.println("window.location:-8,0");
				ne.flush();
				ne.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		new File(path).mkdir();
		mainwin ok = new mainwin();
		setVisible(false);
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
		this.setVisible(false);
	}
}
