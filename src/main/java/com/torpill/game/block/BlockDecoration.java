package com.torpill.game.block;

import java.awt.Color;
import java.awt.Image;

import com.torpill.game.Game;

public class BlockDecoration extends Block {

	public BlockDecoration(Block block) {

		this.block = block;
	}

	@Override
	public int getId() {

		return this.block.getId() + Game.DECORATION;
	}

	@Override
	public Color getColor() {

		return this.block.getColor();
	}

	@Override
	public boolean isFluid() {

		return this.block.isFluid();
	}

	@Override
	public Image getImage() {

		return this.block.getImage();
	}

	@Override
	public boolean isDecoration() {

		return true;
	}

	private final Block block;
}
