package com.torpill.game.block.mobile;

import com.torpill.game.block.Block;

public abstract class MobileBlock extends Block {

	protected MobileBlock(int x, int y) {

		super(x, y);
	}

	public void update() {

		this.x += this.motionX;
		this.y += this.motionY;
	}
	
	protected int motionX, motionY;
}
