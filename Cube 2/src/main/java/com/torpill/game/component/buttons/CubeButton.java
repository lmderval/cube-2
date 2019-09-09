package com.torpill.game.component.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.torpill.game.util.I18n;

@SuppressWarnings("serial")
public class CubeButton extends JPanel implements MouseListener {

	public CubeButton(String name) {

		super();

		this.in = false;
		this.pressed = false;

		this.text = "button." + name + ".name";
		this.setForeground(Color.BLACK);

		this.addMouseListener(this);
		this.setActionCommand(name);
		this.updateText();
	}

	public void updateText() {

// this.setText(I18n.format(this.text));
	}

	public void setActionCommand(String ac) {

		this.ac = ac;
	}

	public String getActionCommand() {

		return this.ac;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		this.pressed = true;
		this.in = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (this.in) {

			this.fireActionPerformed(e, this.ac);
			this.in = false;
		}

		this.pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		this.in = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {

		this.in = false;
	}

	protected void fireActionPerformed(MouseEvent event, String ac) {

		Object[] listeners = listenerList.getListenerList();
		ActionEvent e = null;

		for (int i = listeners.length - 2; i >= 0; i -= 2) {

			if (listeners[i] == ActionListener.class) {

				if (e == null) {

					String actionCommand = ac;
					if (actionCommand == null) {

						actionCommand = getActionCommand();
					}

					e = new ActionEvent(CubeButton.this, ActionEvent.ACTION_PERFORMED, actionCommand, event.getWhen(), event.getModifiers());
				}

				((ActionListener) listeners[i + 1]).actionPerformed(e);
			}
		}
	}

	public void addActionListener(ActionListener l) {

		listenerList.add(ActionListener.class, l);
	}

	public void removeActionListener(ActionListener l) {

		listenerList.remove(ActionListener.class, l);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (this.in) {

			if (this.pressed) {

				this.setBackground(new Color(0xBBBBBB));

			} else {

				this.setBackground(new Color(0x999999));
			}

		} else {

			this.setBackground(new Color(0x444444));
		}

		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		String text = I18n.format(this.text);
		Font font = new Font("Roboto", Font.PLAIN, this.getHeight() / 2);
		FontMetrics fm = this.getFontMetrics(font);
		int px = fm.stringWidth(text);

		g.setColor(this.getForeground());
		g.setFont(font);
		int x = this.getWidth() / 2 - px / 2;
		int y = this.getHeight() / 2 + font.getSize() / 3;
		g.drawString(text, x, y);
	}

	private boolean in, pressed;
	private String text;
	private String ac;
}
