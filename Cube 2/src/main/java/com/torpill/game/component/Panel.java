package com.torpill.game.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.entity.Entity;
import com.torpill.game.entity.Player;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	public Panel(Game game, Window window) {

		super();

		this.game = game;
		this.window = window;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int x, y, width, height, unit = this.window.getUnit();

		ArrayList<Entity> entities = this.game.getEntities();
		for (Entity entity : entities) {

			x = entity.getXonScreen();
			y = entity.getYonScreen();
			width = entity.getWidth();
			height = entity.getHeight();

			g.setColor(entity.getColor());
			g.fillRect(x, y, unit * width, unit * height);
		}

		ArrayList<Block> blocks = this.game.getBlocks();
		for (Block block : blocks) {

			x = block.getXonScreen();
			y = block.getYonScreen();
			width = block.getWidth();
			height = block.getHeight();

			g.setColor(block.getColor());
			g.fillRect(x, y, unit * width, unit * height);
		}

		g.setColor(Color.BLUE);
		g.drawString(this.window.getFPS() + " FPS", 10, 20);

		// DEBUG
		boolean debug = true;
		if (debug) {

			g.setColor(Color.RED);
			Player p = this.game.getPlayer();
			int baseX = this.getWidth() - unit * 6;
			if (p.colsides[0]) g.fillRect(baseX + unit * 2, 0, unit * 2, unit * 2);
			if (p.colsides[1]) g.fillRect(baseX + unit * 2, unit * 4, unit * 2, unit * 2);
			if (p.colsides[2]) g.fillRect(baseX, unit * 2, unit * 2, unit * 2);
			if (p.colsides[3]) g.fillRect(baseX + unit * 4, unit * 2, unit * 2, unit * 2);

			Rectangle e = new Rectangle(p.getX() * unit, p.getY() * unit, p.getWidth() * unit, p.getHeight() * unit);
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.BLACK);
			g2d.fill(e);

			ArrayList<Block> bs = this.game.getBlocks();
			Rectangle up, down, right, left;

			for (Block b : bs) {

				up = new Rectangle(b.getX() * unit, b.getY() * unit + b.getHeight() * unit - 1 * unit, b.getWidth() * unit, 1 * unit);
				down = new Rectangle(b.getX() * unit, b.getY() * unit, b.getWidth() * unit, 1 * unit);
				right = new Rectangle(b.getX() * unit, b.getY() * unit, 1 * unit, b.getHeight() * unit);
				left = new Rectangle(b.getX() * unit + b.getWidth() * unit - 1 * unit, b.getY() * unit, 1 * unit, b.getHeight() * unit);

				g.setColor(Color.GREEN);
				g2d.fill(up);
				g.setColor(Color.RED);
				g2d.fill(down);
				g.setColor(Color.BLUE);
				g2d.fill(right);
				g2d.fill(left);
			}
		}
	}

	private Game game;
	private Window window;
}
