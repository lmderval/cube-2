package com.torpill.game.component.panels;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.torpill.game.Game;
import com.torpill.game.component.Window;

@SuppressWarnings("serial")
public abstract class CubePanel extends JPanel implements ActionListener {

	public CubePanel(Game game, Window window) {

		super();

		this.game = game;
		this.window = window;
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		switch (this.game.state) {
		case INIT:
			break;

		case MAIN:

			this.paintMain(g);
			break;

		case WORLDS:

			this.paintWorlds(g);
			break;

		case LEVELS:

			this.paintLevels(g);
			break;

		case OPTIONS:

			this.paintOptions(g);
			break;

		case CONTROLS:

			this.paintControls(g);
			break;

		case LANGUAGES:

			this.paintLanguages(g);
			break;

		case PLAY:

			this.paintPlay(g);
			break;

		case DEATH:

			this.paintDeath(g);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void setOffset(double x, double y) {

		this.xoffset = (int) (x * this.window.getUnit());
		this.yoffset = (int) (y * this.window.getUnit());
	}

	public int getXonScreen(int x) {

		return x * this.game.getWindow().getUnit() - this.xoffset;
	}

	public int getYonScreen(int y) {

		return this.game.getWindow().getGlassPaneHeigth() - y * this.game.getWindow().getUnit() - this.game.getWindow().getUnit() + this.yoffset;
	}

	public int getUIXonScreen(int x) {

		return x * this.game.getWindow().getUnit();
	}

	public int getUIYonScreen(int y) {

		return y * this.game.getWindow().getUnit();
	}

	public int getUnit() {

		return this.window.getUnit();
	}

	protected void paintMain(Graphics g) {

	}

	protected void paintWorlds(Graphics g) {

	}

	protected void paintLevels(Graphics g) {

	}

	protected void paintOptions(Graphics g) {

	}

	protected void paintControls(Graphics g) {

	}

	protected void paintLanguages(Graphics g) {

	}

	protected void paintPlay(Graphics g) {

	}

	protected void paintDeath(Graphics g) {

	}

	protected Game game;
	protected Window window;
	protected int xoffset = 0, yoffset = 0;
}
