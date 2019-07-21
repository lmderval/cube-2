package com.torpill.game.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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

	protected Image structuredImage(Image texture) {

		if (this.width < 1 || this.height < 1) {

			return null;
		}

		int map[][] = new int[this.width * this.height][2];
		int size = 16;

		for (int a = 0; a < this.width * this.height; a++) {

			map[a][0] = size;
			map[a][1] = 0;
		}

		if (this.width == 1 && this.height == 1) {

			map[0][0] = 0;
			map[0][1] = 0;

		} else if (this.width == 1) {

			for (int j = 1; j < this.height; j++) {

				map[j][0] = 0;
				map[j][1] = 2 * size;
			}

			map[0][0] = size;
			map[0][1] = size;
			map[this.height - 1][0] = 0;
			map[this.height - 1][1] = size;

		} else if (this.height == 1) {

			for (int i = 1; i < this.width; i++) {

				map[i][0] = size;
				map[i][1] = 2 * size;
			}

			map[0][0] = 2 * size;
			map[0][1] = size;
			map[this.width - 1][0] = 3 * size;
			map[this.width - 1][1] = size;

		} else {

			for (int i = 0; i < this.width; i++) {

				for (int j = 0; j < this.height; j++) {

					if (i == 0) {

						map[i + j * this.width][0] = 2 * size;
						map[i + j * this.width][1] = 3 * size;

					} else if (i == this.width - 1) {

						map[i + j * this.width][0] = 3 * size;
						map[i + j * this.width][1] = 3 * size;

					} else if (j == 0) {

						map[i + j * this.width][0] = size;
						map[i + j * this.width][1] = 3 * size;

					} else if (j == this.height - 1) {

						map[i + j * this.width][0] = 0;
						map[i + j * this.width][1] = 3 * size;
					}
				}
			}

			map[0][0] = size;
			map[0][1] = 4 * size;
			map[this.width - 1][0] = 3 * size;
			map[this.width - 1][1] = 4 * size;
			map[(this.height - 1) * this.width][0] = 0;
			map[(this.height - 1) * this.width][1] = 4 * size;
			map[this.width * this.height - 1][0] = 2 * size;
			map[this.width * this.height - 1][1] = 4 * size;
		}

		BufferedImage img = new BufferedImage(this.width * size, this.height * size, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();

		for (int j = 0; j < this.height; j++) {

			for (int i = 0; i < this.width; i++) {

				g.drawImage(texture, i * size, j * size, i * size + size, j * size + size, map[i + j * this.width][0], map[i + j * this.width][1], map[i + j * this.width][0] + size, map[i + j * this.width][1] + size, null);
			}
		}

		g.dispose();

		return img;
	}

	public abstract Image getImage();

	public boolean isDecoration() {

		return this.decorative;
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

	public static Block createDecoration(Class<? extends Block> clazz, Object... args) {

		Block block;

		try {

			Constructor<?> constructor = clazz.getConstructors()[0];
			block = (Block) constructor.newInstance(args);

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

			System.err.println(e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());

			block = new VoidBlock();
		}

		block.decorative = true;

		return block;
	}

	protected Game game;
	protected Level level;
	protected int x, y, width = 1, height = 1;
	public static final byte UP = 1, DOWN = 2, RIGHT = 4, LEFT = 8;
	protected boolean decorative = false;
}
