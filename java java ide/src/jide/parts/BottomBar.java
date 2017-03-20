package jide.parts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jide.jidest;

@SuppressWarnings("serial")
public class BottomBar extends JPanel implements MouseListener{
	JLabel spacer = new JLabel("  | ");
	JLabel charCount = new JLabel("charcount");
	JLabel lineCount = new JLabel("linecount");
	JLabel cursorPosition = new JLabel("TEST:TEST");
	JPanel words = new JPanel();
	public BottomBar(){
		spacer.setFont(new Font("Arial",Font.PLAIN,18));
		//BoxLayout m = new BoxLayout(this,BoxLayout.X_AXIS);
		words.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//this.setLayout(m);
		setPreferredSize(new Dimension((int) jidest.x_size,20));
		setBackground(Color.red);
		words.add(spacer);
		words.add(lineCount);
		words.add(clone(spacer));
		words.add(charCount);
		words.add(clone(spacer));
		words.add(cursorPosition);
		add(words,BorderLayout.EAST);
	}
	
	public JLabel clone(JLabel in){
		JLabel out = new JLabel(in.getText());
		out.setHorizontalAlignment(in.getHorizontalAlignment());
		out.setFont(in.getFont());
		out.setActionMap(in.getActionMap());
		out.setAlignmentX(in.getAlignmentX());
		out.setAlignmentY(in.getAlignmentY());
		out.setAutoscrolls(in.getAutoscrolls());
		out.setLabelFor(in.getLabelFor());
		out.setDisplayedMnemonic(in.getDisplayedMnemonic());
		out.setDisplayedMnemonicIndex(in.getDisplayedMnemonicIndex());
		out.setHorizontalTextPosition(in.getHorizontalTextPosition());
		out.setIcon(in.getIcon());
		out.setIconTextGap(in.getIconTextGap());
		out.setLabelFor(in.getLabelFor());
		out.setUI(in.getUI());
		out.setBorder(in.getBorder());
		out.setMaximumSize(in.getMaximumSize());
		out.setMinimumSize(in.getMinimumSize());
		out.setPreferredSize(in.getPreferredSize());
		out.setBackground(in.getBackground());
		out.setBounds(in.getBounds());
		out.setComponentOrientation(in.getComponentOrientation());
		out.setText(in.getText());
		out.setComponentPopupMenu(in.getComponentPopupMenu());
		return out;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
