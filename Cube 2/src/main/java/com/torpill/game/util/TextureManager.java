package com.torpill.game.util;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureManager {

	public static void init() {

		CUBE = get("/textures/cube.png");
	}

	private static Image get(String file) {

		try {

			Image img = ImageIO.read(TextureManager.class.getResource(file));
			System.out.println("Textture found: " + file);
			return img;

		} catch (IOException | IllegalArgumentException e) {

			System.out.println("Textture not found: " + file);
			return null;
		}
	}

	public static Image CUBE;
}
