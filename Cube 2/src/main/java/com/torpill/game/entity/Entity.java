package com.torpill.game.entity;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.block.damager.DamagerBlock;
import com.torpill.game.event.entity.EntityEvent.EntityDamagingEvent;
import com.torpill.game.event.entity.EntityEvent.EntityFallingEvent;
import com.torpill.game.event.entity.EntityEvent.EntityLivingEvent;
import com.torpill.game.level.Level;
import com.torpill.game.util.damage.DamageSource;
import com.torpill.game.util.damage.DamageType;
import com.torpill.game.util.damage.IDamager;

public abstract class Entity implements IDamager {

	protected Entity(int x, int y) {

		this.posX = x;
		this.posY = y;
		this.health = this.startHealth;
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

		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;

		if (this.dead) {

			return;
		}

		Block data[][] = this.game.getData();
		boolean flag = false;
		int i1;

		this.game.addEvent(new EntityLivingEvent(this.game, this));

		for (i1 = 0; i1 < Math.abs(this.motionX); i1++) {

			int dx = this.motionX / Math.abs(this.motionX);

			try {

				Block bl = data[this.posX + dx][this.posY];
				Block br = data[this.posX + this.width + dx - 1][this.posY];
				Block tl = data[this.posX + dx][this.posY + this.height - 1];
				Block tr = data[this.posX + this.width + dx - 1][this.posY + this.height - 1];

				if ((bl.isFluid() || bl == Blocks.air || bl.isDecoration()) && (br.isFluid() || br == Blocks.air || br.isDecoration()) && (tl.isFluid() || tl == Blocks.air || tl.isDecoration()) && (tr.isFluid() || tr == Blocks.air || tr.isDecoration())) {

					this.posX += dx;
				}

			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

		for (i1 = 0; i1 < Math.abs(this.motionY); i1++) {

			int dy = this.motionY / Math.abs(this.motionY);

			try {

				Block bl = data[this.posX][this.posY + dy];
				Block br = data[this.posX + this.width - 1][this.posY + dy];
				Block tl = data[this.posX][this.posY + this.height + dy - 1];
				Block tr = data[this.posX + this.width - 1][this.posY + this.height + dy - 1];

				if ((bl.isFluid() || bl == Blocks.air || bl.isDecoration()) && (br.isFluid() || br == Blocks.air || br.isDecoration()) && (tl.isFluid() || tl == Blocks.air || tl.isDecoration()) && (tr.isFluid() || tr == Blocks.air || tr.isDecoration())) {

					this.posY += dy;
				}

				for (int i = 0; i < this.width; i++) {

					Block t = data[this.posX + i][this.posY + this.height];
					if (t != Blocks.air && !t.isFluid() && !t.isDecoration()) {

						this.motionY = 0;
					}
				}

			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

		if (this.posY > 0) {

			for (int i = 0; i < this.width; i++) {

				Block b = data[this.posX + i][this.posY - 1];
				if (b != Blocks.air && !b.isFluid() && !b.isDecoration()) {

					flag = true;
				}
			}

		} else {

			flag = true;
		}

		ArrayList<DamagerBlock> dbs = new ArrayList<DamagerBlock>();

		for (int i = 0; i < this.width; i++) {

			for (int j = -1; j < this.height + 1; j++) {

				if (this.posY + j < 0 || this.posY + j >= data[this.posX + i].length) {

					continue;
				}

				Block p = data[this.posX + i][this.posY + j];
				if (p.isDamager()) {

					DamagerBlock db = (DamagerBlock) p;
					if (!dbs.contains(db)) {

						dbs.add(db);
					}
				}
			}
		}

		for (DamagerBlock db : dbs) {

			this.game.addEvent(new EntityDamagingEvent(this.game, this, new DamageSource(db)));
		}

		if (flag) {

			if (!this.onGround) {

				for (int i = 0; i < this.width; i++) {

					if (this.posY - 1 >= 0) {

						this.game.addEvent(new EntityFallingEvent(this.game, this, data[this.posX + i][this.posY - 1]));

					} else {

						this.game.addEvent(new EntityFallingEvent(this.game, this, Blocks.stone));
					}
				}
			}

			this.motionY = 0;
			this.onGround = true;

		} else {

			this.onGround = false;
		}

		this.applyGravity();

		if (this.posY > 0) {

			for (int i = 0; i < this.width; i++) {

				Block b = data[this.posX + i][this.posY - 1];

				if (b != Blocks.air && !b.isDecoration()) {

					b.onTop(this, this.posX + i, this.posY - 1, game);
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

		return this.posX;
	}

	public int getY() {

		return this.posY;
	}

	public int getWidth() {

		return this.width;
	}

	public int getHeight() {

		return this.height;
	}

	public int getXonScreen() {

		return this.posX * this.game.getWindow().getUnit();
	}

	public int getYonScreen() {

		return this.game.getWindow().getGlassPaneHeigth() - this.posY * this.game.getWindow().getUnit() - this.game.getWindow().getUnit() * this.height;
	}

	public Color getColor() {

		return Color.BLACK;
	}

	public void setAlive() {

		this.health = this.startHealth;
		this.dead = false;
	}

	public boolean isAlive() {

		return !this.dead;
	}

	public boolean isHidden() {

		return false;
	}

	public abstract Image getImage();

	public void attackEntityFrom(DamageSource source) {

		int l = this.resistance;
		int m = 1;

		switch (source.type) {
		case FALL:
			m = this.lastTickPosY - this.posY - 4;
			if (m < 0) {

				m = 0;
			}
			break;

		case FIRE:
			this.onFire = true;
			break;

		case MAGIC:
			l = 0;
			break;

		default:
			break;
		}

		int d = m * 2 * source.damage - l;
		if (d < 0) {

			d = 0;
		}

		this.health -= d;
		if (health <= 0) {

			this.dead = true;
		}
	}

	@Override
	public int getDamage() {

		return 1;
	}

	@Override
	public DamageType getType() {

		return DamageType.HURT;
	}

	public abstract String getName();

	public int getHealth() {

		return this.health;
	}

	public int getStartHealth() {

		return this.startHealth;
	}

	public int getAbsorption() {

		return this.health - this.startHealth;
	}

	public void regen(int health) {

		this.health += health;
		if (this.health > this.startHealth) {

			this.health = this.startHealth;
		}
	}

	public void absorb(int health) {

		this.health = this.startHealth + health;
	}

	protected Game game;
	protected Level level;
	protected int posX, posY, width = 1, height = 1;
	protected int lastTickPosX, lastTickPosY;
	public int motionX, motionY;
	protected boolean onGround = true;
	public boolean colsides[] = new boolean[4];
	protected boolean dead;
	protected int resistance = 0;
	protected int startHealth = 200, health;
	protected boolean onFire; // TODO Fire
	protected String nametag = "";
}
