package jide.parts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileSystemView;
import jide.util.DirectoryBoxRootSet;

@SuppressWarnings("serial")
public class DirectoryPanel extends JPanel implements ActionListener{
	DirectoryBoxScroll dbs = new DirectoryBoxScroll();
	JComboBox<String> driveList;
	File[] paths;
	String[] rootList;
	public JProgressBar dirChangeProgress = new JProgressBar(0,0);
	public DirectoryPanel(){
		this.setLayout(new BorderLayout());
		add(dbs,BorderLayout.CENTER);
		FileSystemView fsv = FileSystemView.getFileSystemView();
		paths = File.listRoots();
		rootList = new String[paths.length];
		for(int i=0;i<paths.length;i++){
			rootList[i] = (fsv.getSystemDisplayName(paths[i]));
		}
		driveList = new JComboBox<String>(rootList);
		add(driveList,BorderLayout.NORTH);
		add(dirChangeProgress,BorderLayout.SOUTH);
		dirChangeProgress.setVisible(false);
		driveList.addActionListener(this);
	}
	
	public DirectoryBox getContent(){
		return dbs.getContent();
	}
	
	public <E> int indexOf(E object,E[] list){
		for(int i=0;i<list.length;i++){
			if(list[i]==object)
				return i;
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DirectoryBoxRootSet dbrs = new DirectoryBoxRootSet(this,getContent(),paths[indexOf((String)driveList.getSelectedItem(),rootList)]);
		Thread dbrst = new Thread(dbrs);
		dbrst.start();
	}
}
