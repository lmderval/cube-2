package com.torpill.game.component.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.component.Window;
import com.torpill.game.entity.Entity;
import com.torpill.game.util.TextureManager;

@SuppressWarnings("serial")
public class PlayPanel extends CubePanel {

	public PlayPanel(Game game, Window window) {

		super(game, window);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
	}

	@Override
	protected void paintPlay(Graphics g) {

		super.paintPlay(g);
		Graphics2D g2d = (Graphics2D) g;

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
	}
}
