package com.torpill.game.block;

import java.awt.Color;
import java.awt.Rectangle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.torpill.game.Game;
import com.torpill.game.block.mobile.MobileBlock;
import com.torpill.game.entity.Entity;
import com.torpill.game.level.Level;

public abstract class Block {

	protected Block(int x, int y) {

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

	public boolean isStatic() {

		return !(this instanceof MobileBlock);
	}

	public byte collide(Entity entity) {

		byte res = 0;

		Rectangle e = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
		Rectangle up = new Rectangle(this.x, this.y + this.height - 1, this.width, 1);
		Rectangle down = new Rectangle(this.x, this.y, this.width, 1);
		Rectangle right = new Rectangle(this.x, this.y, 1, this.height);
		Rectangle left = new Rectangle(this.x + this.width - 1, this.y, 1, this.height);

		res = (byte) ((e.intersects(up) ? UP : 0) + (e.intersects(down) ? DOWN : 0) + (e.intersects(right) ? RIGHT : 0) + (e.intersects(left) ? LEFT : 0));

		return res;
	}

	public byte collideX(Entity entity) {

		byte res = 0;

		Rectangle e = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
		Rectangle right = new Rectangle(this.x - 1, this.y, 1, this.height);
		Rectangle left = new Rectangle(this.x + this.width, this.y, 1, this.height);

		res = (byte) ((e.intersects(right) ? RIGHT : 0) + (e.intersects(left) ? LEFT : 0));

		return res;
	}
	
	public byte collideY(Entity entity) {

		byte res = 0;

		Rectangle e = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
		Rectangle down = new Rectangle(this.x, this.y - 1, this.width, 1);

		res = e.intersects(down) ? DOWN : 0;

		return res;
	}

	public Color getColor() {

		return Color.BLACK;
	}

	public static Block create(Class<? extends Block> clazz, Object... args) {

		Block block;

		try {

			Constructor<?> constructor = clazz.getConstructors()[0];
			block = (Block) constructor.newInstance(args);

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

			System.err.println(e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());

			block = new VoidBlock();
		}

		return block;
	}

	protected Game game;
	protected Level level;
	protected int x, y, width = 1, height = 1;
	public static final byte UP = 1, DOWN = 2, RIGHT = 4, LEFT = 8;
}
