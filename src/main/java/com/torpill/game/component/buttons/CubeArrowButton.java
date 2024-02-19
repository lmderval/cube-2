package com.torpill.game.component.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.plaf.basic.BasicArrowButton;

@SuppressWarnings("serial")
public class CubeArrowButton extends BasicArrowButton implements MouseListener {

	private Color shadow;
	private Color darkShadow;
	private Color pressed;
	private Color highlight;

	private boolean in;

	public CubeArrowButton(int direction, Color background, Color shadow, Color darkShadow, Color highlight, Color pressed) {

		super(direction, background, shadow, darkShadow, pressed);

		this.shadow = shadow;
		this.darkShadow = darkShadow;
		this.highlight = highlight;
		this.pressed = pressed;

		this.in = false;
		this.addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {

		Color origColor;
		boolean isPressed, isEnabled;
		int w, h, size;

		w = this.getSize().width;
		h = this.getSize().height;
		origColor = g.getColor();
		isPressed = this.getModel().isPressed();
		isEnabled = this.isEnabled();

		g.setColor(this.getBackground());

		if (isPressed) {

			g.setColor(this.pressed);

		} else if (this.in) {

			g.setColor(this.highlight);
		}

		g.fillRect(0, 0, w, h);

		if (h < 5 || w < 5) {
			g.setColor(origColor);
			return;
		}

		size = Math.min((h - 4) / 3, (w - 4) / 3);
		size = Math.max(size, 2);
		this.paintTriangle(g, (w - size) / 2 + 1, (h - size) / 2 + 1, size, this.direction, isEnabled);

		g.setColor(origColor);
	}

	@Override
	public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {

		Color oldColor = g.getColor();
		int mid, i, j;

		j = 0;
		size = Math.max(size, 2);
		mid = (size / 2) - 1;

		g.translate(x, y);
		if (isEnabled) g.setColor(this.darkShadow);
		else g.setColor(this.shadow);

		switch (direction) {
		case NORTH:
			for (i = 0; i < size; i++) {
				g.drawLine(mid - i, i, mid + i, i);
			}
			if (!isEnabled) {
				g.setColor(this.pressed);
				g.drawLine(mid - i + 2, i, mid + i, i);
			}
			break;
		case SOUTH:
			if (!isEnabled) {
				g.translate(1, 1);
				g.setColor(this.pressed);
				for (i = size - 1; i >= 0; i--) {
					g.drawLine(mid - i, j, mid + i, j);
					j++;
				}
				g.translate(-1, -1);
				g.setColor(this.shadow);
			}

			j = 0;
			for (i = size - 1; i >= 0; i--) {
				g.drawLine(mid - i, j, mid + i, j);
				j++;
			}
			break;
		case WEST:
			for (i = 0; i < size; i++) {
				g.drawLine(i, mid - i, i, mid + i);
			}
			if (!isEnabled) {
				g.setColor(this.pressed);
				g.drawLine(i, mid - i + 2, i, mid + i);
			}
			break;
		case EAST:
			if (!isEnabled) {
				g.translate(1, 1);
				g.setColor(this.pressed);
				for (i = size - 1; i >= 0; i--) {
					g.drawLine(j, mid - i, j, mid + i);
					j++;
				}
				g.translate(-1, -1);
				g.setColor(this.shadow);
			}

			j = 0;
			for (i = size - 1; i >= 0; i--) {
				g.drawLine(j, mid - i, j, mid + i);
				j++;
			}
			break;
		}
		g.translate(-x, -y);
		g.setColor(oldColor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		this.in = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {

		this.in = false;
	}
}
