package com.torpill.game.level;

import com.torpill.game.block.Block;
import com.torpill.game.block.StoneBlock;

public class Level0 extends Level {

	public Level0() {

		super(0, 15);
	}

	public void init() {

		super.init();

		Block.create(StoneBlock.class, 20, 6, 20, 3).addTo(this);
	}
}
