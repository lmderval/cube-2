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
		Block.create(StoneBlock.class, 60, 10, 3, 9).addTo(this);
		Block.create(StoneBlock.class, 70, 10, 20, 3).addTo(this);
		Block.create(StoneBlock.class, 95, 17, 30, 3).addTo(this);
		Block.create(StoneBlock.class, 133, 17, 5, 4).addTo(this);
		Block.create(StoneBlock.class, 138, 17, 6, 1).addTo(this);
		Block.create(StoneBlock.class, 144, 17, 5, 4).addTo(this);
		Block.create(LavaBlock.class, 138, 18, 6, 2).addTo(this);
		Block.create(LavaBlock.class, 0, 0, 500, 6).addTo(this);

		Block.createDecoration(StoneBlock.class, 137, 17, 8, 1).addTo(this);
	}

	public String getName() {

		return "Didacticiel";
	}
}
