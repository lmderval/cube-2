package com.torpill.game.component.renderers.combos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

@SuppressWarnings({
		"serial", "rawtypes"
})
public class CubeComboBoxRenderer extends BasicComboBoxRenderer {

	private Color background = new Color(0x444444);
	private Color foreground = Color.BLACK;
	private Color selectionbackground = new Color(0x999999);
	private Color selectionforeground = Color.BLACK;

	public CubeComboBoxRenderer() {

		super();

		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (isSelected) {

			this.setBackground(this.selectionbackground);
			this.setForeground(this.selectionforeground);

		} else {

			this.setBackground(this.background);
			this.setForeground(this.foreground);
		}

		this.setFont(new Font("Roboto", Font.PLAIN, 14));

		if (value instanceof Icon) {

			this.setIcon((Icon) value);

		} else {

			this.setText((value == null) ? "" : value.toString());
		}

		return this;
	}
}