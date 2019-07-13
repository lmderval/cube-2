package com.torpill.game.block;

import java.awt.Color;

public class StoneBlock extends Block {

	public StoneBlock(int x, int y, int width, int height) {

		super(x, y);
		
		this.width = width;
		this.height = height;
	}

	public Color getColor() {
		
		return Color.DARK_GRAY;
	}
}
