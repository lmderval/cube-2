package com.torpill.game.block.damager;

import java.awt.Color;
import java.awt.Image;

import com.torpill.game.util.TextureManager;

public class LavaBlock extends DamagerBlock {

	public LavaBlock(int x, int y, int width, int height) {

		super(x, y);

		this.width = width;
		this.height = height;
		this.fluid = true;
	}

	public Color getColor() {

		return Color.RED;
	}

	public Image getImage() {

		return (this.decorative ? this.structuredImage(TextureManager.DLAVA) : this.structuredImage(TextureManager.LAVA));
	}
}
