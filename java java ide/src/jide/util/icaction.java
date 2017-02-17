package jide.util;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class icaction extends AbstractAction{
	protected icaction(String name, Icon icon, String descrip,Integer mnemonic, KeyStroke accel) {
		super(name, icon);
		putValue(SHORT_DESCRIPTION, descrip);
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, accel);
	}
	
	public void actionPerformed(ActionEvent e){}
}
