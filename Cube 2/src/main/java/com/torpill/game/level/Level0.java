package com.torpill.game.level;

import com.torpill.game.block.Block;
import com.torpill.game.block.StoneBlock;
import com.torpill.game.block.damager.LavaBlock;

public class Level0 extends Level {

	public Level0() {

		super(0, 15);
	}

	public void init() {

		super.init();

		Block.create(StoneBlock.class, 0, 10, 60, 3).addTo(this);
		Block.create(StoneBlock.class, 70, 10, 20, 3).addTo(this);
		Block.create(LavaBlock.class, 0, 0, 500, 6).addTo(this);
	}

	public String getName() {

		return "Didacticiel";
	}
}
