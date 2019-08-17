package com.torpill.game.util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.component.PlayPanel;

public class TextureManager {

	public static void init() {

		CUBE = get("/textures/entity/player/cube.png");
	}

	public static Image get(String file) {

		try {

			Image img = ImageIO.read(TextureManager.class.getResource(file));
			System.out.println("Texture found: " + file);
			return img;

		} catch (IOException | IllegalArgumentException e) {

			System.out.println("Texture not found: " + file);

			return null;
		}
	}

	public static Image defaultBlock() {

		try {

			Image img = ImageIO.read(TextureManager.class.getResource("/textures/blocks/none.png"));
			System.out.println(" -> Default texture found: /textures/blocks/none.png");
			return img;

		} catch (IOException | IllegalArgumentException e) {

			System.out.println(" -> Default texture not found: /textures/blocks/none.png");

			return null;
		}
	}

	public static void drawBlock(Graphics g, Block[][] data, int i, int j, PlayPanel pan) {

		if (data[i][j] == Blocks.air) {

			return;
		}

		int block = 0;
		Image img = data[i][j].getImage();
		int bi = 16 + (data[i][j].getId() > 255 ? 16 : 0);
		int unit = pan.getUnit();

		if (i > 0) {

			block += (data[i - 1][j] == data[i][j] ? 8 : 0);
		}

		if (i + 1 < data.length) {

			block += (data[i + 1][j] == data[i][j] ? 16 : 0);
		}

		if (j > 0) {

			block += (data[i][j - 1] == data[i][j] ? 64 : 0);
		}

		if (j + 1 < data[i].length) {

			block += (data[i][j + 1] == data[i][j] ? 2 : 0);
		}

		if ((block & 2) == 2 && (block & 8) == 8) {

			block += (data[i - 1][j + 1] == data[i][j] ? 1 : 0);
		}

		if ((block & 2) == 2 && (block & 16) == 16) {

			block += (data[i + 1][j + 1] == data[i][j] ? 4 : 0);
		}

		if ((block & 64) == 64 && (block & 8) == 8) {

			block += (data[i - 1][j - 1] == data[i][j] ? 32 : 0);
		}

		if ((block & 64) == 64 && (block & 16) == 16) {

			block += (data[i + 1][j - 1] == data[i][j] ? 128 : 0);
		}

		int border = 255 ^ block;
		int x = pan.getXonScreen(i);
		int y = pan.getYonScreen(j);

		g.drawImage(img, x, y, x + unit, y + unit, 0, 0, 16, 16, pan);

		if ((border & 1) == 1) {

			g.drawImage(img, x, y, x + unit * 3 / 16, y + unit * 3 / 16, bi + 0, 0, bi + 2, 2, pan);
		}

		if ((border & 2) == 2) {

			g.drawImage(img, x + unit * 3 / 16, y, x + unit * 14 / 16, y + unit * 3 / 16, bi + 2, 0, bi + 14, 2, pan);
		}

		if ((border & 4) == 4) {

			g.drawImage(img, x + unit * 14 / 16, y, x + unit, y + unit * 3 / 16, bi + 14, 0, bi + 16, 2, pan);
		}

		if ((border & 8) == 8) {

			g.drawImage(img, x, y + unit * 3 / 16, x + unit * 3 / 16, y + unit * 14 / 16, bi + 0, 2, bi + 2, 14, pan);
		}

		if ((border & 16) == 16) {

			g.drawImage(img, x + unit * 14 / 16, y + unit * 3 / 16, x + unit, y + unit * 14 / 16, bi + 14, 2, bi + 16, 14, pan);
		}

		if ((border & 32) == 32) {

			g.drawImage(img, x, y + unit * 14 / 16, x + unit * 3 / 16, y + unit, bi + 0, 14, bi + 2, 16, pan);
		}

		if ((border & 64) == 64) {

			g.drawImage(img, x + unit * 3 / 16, y + unit * 14 / 16, x + unit * 14 / 16, y + unit, bi + 2, 14, bi + 14, 16, pan);
		}

		if ((border & 128) == 128) {

			g.drawImage(img, x + unit * 14 / 16, y + unit * 14 / 16, x + unit, y + unit, bi + 14, 14, bi + 16, 16, pan);
		}
	}

	public static Image CUBE;
}
