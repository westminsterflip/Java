package jide.util;

import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import jide.parts.DirectoryBox;
import jide.parts.DirectoryPanel;

public class DirectoryBoxRootSet implements Runnable{
	DirectoryBox db;
	File parent;
	DirectoryPanel p;
	public DirectoryBoxRootSet(DirectoryPanel pan,DirectoryBox dbox, File newRoot){
		db=dbox;
		parent=newRoot;
		p=pan;
	}
	@Override
	public void run() {
		db.nodeMap.put(parent.getAbsolutePath(), new DefaultMutableTreeNode(parent));
		db.dir = parent.getAbsolutePath();
		ArrayList<File> files = new ArrayList<File>();
		File[] fils = new File(db.dir).listFiles();
		System.out.println(db.dir);
		System.out.println(fils.length);
		p.dirChangeProgress.setMaximum(fils.length);
		p.dirChangeProgress.setVisible(true);
		for(File t:fils){
			//System.out.println(t.getName());
			if(!db.nodeMap.containsKey(t.getAbsolutePath())){
				db.nodeMap.put(t.getAbsolutePath(), new DefaultMutableTreeNode(t));
			}
			if(t.isDirectory()){
				if(t.listFiles()!=null&&t.listFiles().length!=0){
					db.nodeMap.get(db.dir).add(db.nodeMap.get(t.getAbsolutePath()));
					db.fillNode(t.getAbsolutePath());
				}else if(t.listFiles()!=null){
					db.nodeMap.get(db.dir).add(db.nodeMap.get(t.getAbsolutePath()));
				}
				p.dirChangeProgress.setValue(p.dirChangeProgress.getValue()+1);
			}else{
				files.add(t);
			}
		}
		for(File t:files){
			db.nodeMap.get(db.dir).add(db.nodeMap.get(t.getAbsolutePath()));
			p.dirChangeProgress.setValue(p.dirChangeProgress.getValue()+1);
		}
		//System.out.println(db.nodeMap.get(db.dir).getChildCount());
		DefaultTreeModel m = (DefaultTreeModel) db.dirTree.getModel();
		m.setRoot(db.nodeMap.get(db.dir));
		p.dirChangeProgress.setVisible(false);
	}
}
