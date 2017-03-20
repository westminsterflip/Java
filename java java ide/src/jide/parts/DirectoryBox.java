package jide.parts;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import jide.jidest;

@SuppressWarnings("serial")
public class DirectoryBox extends JPanel implements TreeExpansionListener, TreeSelectionListener, KeyListener{
	public Map<String, DefaultMutableTreeNode> nodeMap = new HashMap<String, DefaultMutableTreeNode>();
	Object lastSelection;
	public JTree dirTree;
	public String dir;

	public DirectoryBox() {
		dir = System.getProperty("user.home");
		nodeMap.put(dir, new DefaultMutableTreeNode(new File(dir)));
		ArrayList<File> files = new ArrayList<File>();
		for (File t : new File(dir).listFiles()) {
			nodeMap.put(t.getAbsolutePath(), new DefaultMutableTreeNode(t));
			if (t.isDirectory()) {
				if (t.listFiles() != null && t.listFiles().length != 0) {
					nodeMap.get(dir).add(nodeMap.get(t.getAbsolutePath()));
					fillNode(t.getAbsolutePath());
				} else if (t.listFiles() != null) {
					nodeMap.get(dir).add(nodeMap.get(t.getAbsolutePath()));
				}
			} else {
				files.add(t);
			}
		}
		for (File t : files) {
			nodeMap.get(dir).add(nodeMap.get(t.getAbsolutePath()));
		}
		dirTree = new JTree(nodeMap.get(dir));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(dirTree);
		dirTree.addTreeExpansionListener(this);
		dirTree.addTreeSelectionListener(this);
		dirTree.addKeyListener(this);
		dirTree.setCellRenderer(new CustomTreeCellRenderer());
		// Set<Object> kl = UIManager.getLookAndFeelDefaults().keySet();
		// for(Object y:kl){
		// if(y.toString().contains("warning"))
		// System.out.println(y);
		// }
	}

	private class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			if (value instanceof DefaultMutableTreeNode) {
				setText(FileSystemView.getFileSystemView()
						.getSystemDisplayName((File) ((DefaultMutableTreeNode) value).getUserObject()));
				setIcon(FileSystemView.getFileSystemView()
						.getSystemIcon((File) ((DefaultMutableTreeNode) value).getUserObject()));
			}
			super.setBackgroundSelectionColor(UIManager.getColor("Tree.selectionBackground"));
			if (selected) {
				super.setBackground(this.getBackgroundSelectionColor());
				setForeground(getTextSelectionColor());
			} else {
				super.setBackground(this.getBackgroundNonSelectionColor());
				setForeground(getTextNonSelectionColor());
			}
			this.setOpaque(true);
			return this;
		}
	}

	public <E, T> E findTag(Map<E, T> map, T object) {
		for (E tag : map.keySet()) {
			if (map.get(tag) == object) {
				return tag;
			}
		}
		return null;
	}

	public void fillNode(String nodeName) {
		ArrayList<File> files = new ArrayList<File>();
		nodeMap.get(nodeName).removeAllChildren();
		for (File t : new File(nodeName).listFiles()) {
			if (!nodeMap.containsKey(t.getAbsolutePath())) {
				nodeMap.put(t.getAbsolutePath(), new DefaultMutableTreeNode(t));
			}
			if (t.isDirectory()) {
				if (t.listFiles() != null && t.listFiles().length != 0) {
					nodeMap.get(nodeName).add(nodeMap.get(t.getAbsolutePath()));
				} else if (t.listFiles() != null) {
					nodeMap.get(nodeName).add(nodeMap.get(t.getAbsolutePath()));
				}
			} else {
				files.add(t);
			}
		}
		for (File t : files) {
			nodeMap.get(nodeName).add(nodeMap.get(t.getAbsolutePath()));
		}
	}

	@SuppressWarnings("rawtypes")
	public void up() {
		File parent;
		parent = new File(dir).getParentFile();
		DefaultMutableTreeNode dmtn = nodeMap.get(dir);
		Enumeration e = dmtn.children();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode ds = ((DefaultMutableTreeNode) e.nextElement());
			ds.removeAllChildren();
		}
		nodeMap.put(parent.getAbsolutePath(), new DefaultMutableTreeNode(parent));
		dir = parent.getAbsolutePath();
		ArrayList<File> files = new ArrayList<File>();
		for (File t : new File(dir).listFiles()) {
			if (!nodeMap.containsKey(t.getAbsolutePath())) {
				nodeMap.put(t.getAbsolutePath(), new DefaultMutableTreeNode(t));
			}
			if (t.isDirectory()) {
				if (t.listFiles() != null && t.listFiles().length != 0) {
					nodeMap.get(dir).add(nodeMap.get(t.getAbsolutePath()));
					fillNode(t.getAbsolutePath());
				} else if (t.listFiles() != null) {
					nodeMap.get(dir).add(nodeMap.get(t.getAbsolutePath()));
				}
			} else {
				files.add(t);
			}
		}
		for (File t : files) {
			nodeMap.get(dir).add(nodeMap.get(t.getAbsolutePath()));
		}
		DefaultTreeModel m = (DefaultTreeModel) dirTree.getModel();
		m.setRoot(nodeMap.get(dir));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void treeCollapsed(TreeExpansionEvent event) {
		DefaultMutableTreeNode dmtn = ((DefaultMutableTreeNode) event.getPath().getLastPathComponent());
		Enumeration e = dmtn.children();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode ds = ((DefaultMutableTreeNode) e.nextElement());
			ds.removeAllChildren();
		}
	}

	@Override
	public void treeExpanded(TreeExpansionEvent event) {
		ArrayList<File> files = new ArrayList<File>();
		for (File t : ((File) ((DefaultMutableTreeNode) event.getPath().getLastPathComponent()).getUserObject())
				.listFiles()) {
			if (t.isDirectory()) {
				if (t.listFiles() != null && t.listFiles().length != 0) {
					fillNode(t.getAbsolutePath());
				}
			} else {
				files.add(t);
			}
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg) {
		System.out.println(((File) ((DefaultMutableTreeNode) arg.getPath().getLastPathComponent()).getUserObject())
				.getAbsolutePath());
		if (jidest.MainWindow.fileList.contains((File) ((DefaultMutableTreeNode) arg.getPath().getLastPathComponent()).getUserObject()))
			jidest.MainWindow.open(((File) ((DefaultMutableTreeNode) arg.getPath().getLastPathComponent()).getUserObject()).getAbsolutePath());
		lastSelection=arg.getPath().getLastPathComponent();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyChar()=='\n')
			jidest.MainWindow.open(((File) ((DefaultMutableTreeNode) ((JTree)arg0.getSource()).getLastSelectedPathComponent()).getUserObject()).getAbsolutePath());
	}
}
