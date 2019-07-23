package com.torpill.game.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureManager {

	public static void init() {

		CUBE = get("/textures/entity/player/cube.png");
		STONE = get("/textures/blocks/stone.png");
		DSTONE = get("/textures/blocks/decorations/stone.png");
		LAVA = get("/textures/blocks/lava.png");
		DLAVA = get("/textures/blocks/decorations/lava.png");
	}

	private static Image get(String file) {

		try {

			Image img = ImageIO.read(TextureManager.class.getResource(file));
			System.out.println("Texture found: " + file);
			return img;

		} catch (IOException | IllegalArgumentException e) {

			System.out.println("Texture not found: " + file);

			BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
			int pixels[] = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

			for (int i = 0; i < pixels.length; i++) {

				pixels[i] = 0xFF000000;
			}

			return img;
		}
	}

	public static Image CUBE;
	public static Image STONE;
	public static Image DSTONE;
	public static Image LAVA;
	public static Image DLAVA;
}
