package com.torpill.game.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.entity.Entity;
import com.torpill.game.util.TextureManager;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	public Panel(Game game, Window window) {

		super();

		this.game = game;
		this.window = window;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		switch (this.game.state) {
		case INIT:
			break;

		case LEVELS:
			break;

		case PLAY:

			g.setColor(Color.BLUE);
			g.drawString(this.window.getFPS() + " FPS", 10, 20);

			g2d.translate(-this.xoffset, -this.yoffset);

			int x, y, width, height, unit = this.window.getUnit();

			ArrayList<Entity> entities = this.game.getEntities();
			for (Entity entity : entities) {

				if (entity.isHidden()) {

					continue;
				}

				x = entity.getXonScreen();
				y = entity.getYonScreen();
				width = entity.getWidth();
				height = entity.getHeight();
				Image img = entity.getImage();

				if (img != null) {

					g.drawImage(img, x, y, unit * width, unit * height, this);

				} else {

					g.setColor(entity.getColor());
					g.fillRect(x, y, unit * width, unit * height);
				}
			}

			Block data[][] = this.game.getData();

			for (int i = 0; i < data.length; i++) {

				for (int j = 0; j < data[i].length; j++) {

					if (data[i][j] != Blocks.air) {

						TextureManager.drawBlock(g, data, i, j, this);
					}
				}
			}

			g2d.translate(this.xoffset, this.yoffset);
			break;

		case DEATH:
			break;
		}
	}

	public void setOffset(int x, int y) {

		this.xoffset = x * this.window.getUnit();
		this.yoffset = y * this.window.getUnit();
	}

	public int getXonScreen(int x) {

		return x * this.game.getWindow().getUnit();
	}

	public int getYonScreen(int y) {

		return this.game.getWindow().getGlassPaneHeigth() - y * this.game.getWindow().getUnit() - this.game.getWindow().getUnit();
	}

	public int getUnit() {

		return this.window.getUnit();
	}

	private Game game;
	private Window window;
	private int xoffset = 0, yoffset = 0;
}
