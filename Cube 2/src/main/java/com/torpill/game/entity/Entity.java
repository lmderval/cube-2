package com.torpill.game.entity;

import java.awt.Color;
import java.util.ArrayList;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.level.Level;

public abstract class Entity {

	protected Entity(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public void addTo(Game game) {

		this.game = game;
		this.game.register(this);
	}

	public void addTo(Level level) {

		this.level = level;
		this.level.register(this);
	}

	public void update() {

		ArrayList<Block> blocks = this.game.getBlocks();
		int i1;
		byte b1;
		byte col;

		for (i1 = 0; i1 < Math.abs(this.motionX); i1++) {

			this.x += this.motionX / Math.abs(this.motionX);

			for (Block block : blocks) {

				col = block.collide(this);

				for (b1 = 0; b1 < colsides.length; b1++) {

					colsides[b1] = (col & (byte) (Math.pow(2, b1))) == (byte) (Math.pow(2, b1));

					if (colsides[b1]) {

						this.x -= this.motionX / Math.abs(this.motionX);
						this.motionX = 0;

						break;
					}
				}
			}
		}

		for (i1 = 0; i1 < Math.abs(this.motionY); i1++) {

			this.y += this.motionY / Math.abs(this.motionY);

			for (Block block : blocks) {

				col = block.collide(this);

				for (b1 = 0; b1 < colsides.length; b1++) {

					colsides[b1] = (col & (byte) (Math.pow(2, b1))) == (byte) (Math.pow(2, b1));

					if (colsides[b1]) {

						this.y -= this.motionY / Math.abs(this.motionY);
						this.motionY = 0;

						break;
					}
				}
			}
		}

		if (this.y < this.height - 1) {

			this.y = this.height - 1;
			this.motionY = 0;

			this.onGround = true;

		} else if (this.colsides[0]) {

			this.motionY = 0;

			this.onGround = true;

		} else {

			this.onGround = false;
		}

		this.applyGravity();
	}

	public void applyGravity() {

		if (!this.onGround) {

			this.motionY--;
		}
	}

	public int getX() {

		return this.x;
	}

	public int getY() {

		return this.y;
	}

	public int getWidth() {

		return this.width;
	}

	public int getHeight() {

		return this.height;
	}

	public int getXonScreen() {

		return this.x * this.game.getWindow().getUnit();
	}

	public int getYonScreen() {

		return this.game.getWindow().getGlassPaneHeigth() - this.y * this.game.getWindow().getUnit() - this.game.getWindow().getUnit();
	}

	public Color getColor() {

		return Color.BLACK;
	}

	protected Game game;
	protected Level level;
	protected int x, y, width = 1, height = 1;
	protected int motionX, motionY;
	protected boolean onGround = true;
	public boolean colsides[] = new boolean[4];
}
