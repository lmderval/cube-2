package com.torpill.game.component.editors.combos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JTextField;

public class CubeComboBoxEditor implements ComboBoxEditor, ComponentListener {

	private JTextField tf;

	public CubeComboBoxEditor() {

		this.tf = new JTextField();
		this.tf.setForeground(Color.BLACK);
		this.tf.setBackground(new Color(0x444444));
		this.tf.setBorder(null);
		this.tf.setEditable(false);
		this.tf.addComponentListener(this);
		this.tf.setHorizontalAlignment(JTextField.CENTER);
	}

	public Component getEditorComponent() {

		return tf;
	}

	public void setItem(Object anObject) {

		if (anObject != null) {

			tf.setText(anObject.toString());
		}
	}

	public Object getItem() {

		return tf.getText();
	}

	public void selectAll() {

		tf.selectAll();
	}

	public void addActionListener(ActionListener l) {

		tf.addActionListener(l);
	}

	public void removeActionListener(ActionListener l) {

		tf.removeActionListener(l);
	}

	@Override
	public void componentResized(ComponentEvent e) {

		this.tf.setFont(new Font("Roboto", Font.PLAIN, this.tf.getHeight() / 2));
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}
}