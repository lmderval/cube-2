package com.torpill.game.component.panels;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TransparentPanel extends JPanel {

	public TransparentPanel() {

		super();
		
		this.setBackground(new Color(0x00000000, true));
	}
}
