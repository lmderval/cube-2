package com.torpill.game.entity;

import java.awt.Color;
import java.awt.Image;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.block.damager.DamagerBlock;
import com.torpill.game.level.Level;

public abstract class Entity {

	protected Entity(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public void addTo(Game game) {

		this.game = game;
		this.game.add(this);
	}

	public void addTo(Level level) {

		this.level = level;
		this.level.add(this);
	}

	public void update() {

		if (this.dead) {

			return;
		}

		Block data[][] = this.game.getData();
		boolean flag = false;
		int i1;

		for (i1 = 0; i1 < Math.abs(this.motionX); i1++) {

			int dx = this.motionX / Math.abs(this.motionX);

			try {

				Block bl = data[this.x + dx][this.y];
				Block br = data[this.x + this.width + dx - 1][this.y];
				Block tl = data[this.x + dx][this.y + this.height - 1];
				Block tr = data[this.x + this.width + dx - 1][this.y + this.height - 1];

				if ((bl.isFluid() || bl == Blocks.air || bl.isDecoration()) && (br.isFluid() || br == Blocks.air || br.isDecoration()) && (tl.isFluid() || tl == Blocks.air || tl.isDecoration()) && (tr.isFluid() || tr == Blocks.air || tr.isDecoration())) {

					this.x += dx;
				}

				for (int i = 0; i < this.width; i++) {

					for (int j = 0; j < this.height; j++) {

						Block p = data[this.x + i][this.y + j];
						if (p instanceof DamagerBlock) {

							this.dead = true;
						}
					}
				}

			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

		for (i1 = 0; i1 < Math.abs(this.motionY); i1++) {

			int dy = this.motionY / Math.abs(this.motionY);

			try {

				Block bl = data[this.x][this.y + dy];
				Block br = data[this.x + this.width - 1][this.y + dy];
				Block tl = data[this.x][this.y + this.height + dy - 1];
				Block tr = data[this.x + this.width - 1][this.y + this.height + dy - 1];

				if ((bl.isFluid() || bl == Blocks.air || bl.isDecoration()) && (br.isFluid() || br == Blocks.air || br.isDecoration()) && (tl.isFluid() || tl == Blocks.air || tl.isDecoration()) && (tr.isFluid() || tr == Blocks.air || tr.isDecoration())) {

					this.y += dy;
				}

				for (int i = 0; i < this.width; i++) {

					Block t = data[this.x + i][this.y + this.height];
					if (t != Blocks.air && !t.isFluid() && !t.isDecoration()) {

						this.motionY = 0;
					}

					for (int j = 0; j < this.height; j++) {

						Block p = data[this.x + i][this.y + j];
						if (p instanceof DamagerBlock) {

							this.dead = true;
						}
					}
				}

			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

		if (this.y > 0) {

			for (int i = 0; i < this.width; i++) {

				Block b = data[this.x + i][this.y - 1];
				if (b != Blocks.air && !b.isFluid() && !b.isDecoration()) {

					flag = true;
				}
			}

		} else {

			flag = true;
		}

		if (flag) {

			this.motionY = 0;
			this.onGround = true;

		} else {

			this.onGround = false;
		}

		this.applyGravity();

		if (this.y > 0) {

			for (int i = 0; i < this.width; i++) {

				Block b = data[this.x + i][this.y - 1];

				if (b != Blocks.air && !b.isDecoration()) {

					b.onTop(this, this.x + i, this.y - 1, game);
				}
			}
		}
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

		return this.game.getWindow().getGlassPaneHeigth() - this.y * this.game.getWindow().getUnit() - this.game.getWindow().getUnit() * this.height;
	}

	public Color getColor() {

		return Color.BLACK;
	}

	public void setAlive() {

		this.dead = false;
	}

	public boolean isAlive() {

		return !this.dead;
	}

	public boolean isHidden() {

		return false;
	}

	public abstract Image getImage();

	protected Game game;
	protected Level level;
	protected int x, y, width = 1, height = 1;
	public int motionX, motionY;
	protected boolean onGround = true;
	public boolean colsides[] = new boolean[4];
	protected boolean dead;
}
