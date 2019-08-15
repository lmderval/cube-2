package com.torpill.game.level;

import com.torpill.game.block.Blocks;

public class Level0 extends Level {

	public Level0() {

		super(0, 13);
	}

	public void init() {

		super.init();

		this.add(Blocks.lava, 0, 0, 500, 6, false);
		this.add(Blocks.stone, 0, 10, 60, 3, false);
		this.add(Blocks.stone, 3, 9, 4, 1, false);
		this.add(Blocks.stone, 4, 6, 2, 3, false);
		this.add(Blocks.stone, 13, 9, 4, 1, false);
		this.add(Blocks.stone, 14, 6, 2, 3, false);
		this.add(Blocks.stone, 23, 9, 4, 1, false);
		this.add(Blocks.stone, 24, 6, 2, 3, false);
		this.add(Blocks.stone, 33, 9, 4, 1, false);
		this.add(Blocks.stone, 34, 6, 2, 3, false);
		this.add(Blocks.stone, 43, 9, 4, 1, false);
		this.add(Blocks.stone, 44, 6, 2, 3, false);
		this.add(Blocks.stone, 53, 9, 4, 1, false);
		this.add(Blocks.stone, 54, 6, 2, 3, false);
		this.add(Blocks.stone, 70, 10, 20, 3, false);
		this.add(Blocks.stone, 73, 9, 4, 1, false);
		this.add(Blocks.stone, 74, 6, 2, 3, false);
		this.add(Blocks.stone, 83, 9, 4, 1, false);
		this.add(Blocks.stone, 84, 6, 2, 3, false);
		this.add(Blocks.stone, 95, 17, 30, 3, false);
		this.add(Blocks.stone, 98, 16, 4, 1, false);
		this.add(Blocks.stone, 99, 6, 2, 10, false);
		this.add(Blocks.stone, 108, 16, 4, 1, false);
		this.add(Blocks.stone, 109, 6, 2, 10, false);
		this.add(Blocks.stone, 118, 16, 4, 1, false);
		this.add(Blocks.stone, 119, 6, 2, 10, false);
		this.add(Blocks.stone, 133, 17, 14, 4, false);
		this.add(Blocks.stone, 138, 16, 4, 1, false);
		this.add(Blocks.stone, 139, 6, 2, 10, false);
		this.add(Blocks.stone, 147, 17, 3, 1, false);
		this.add(Blocks.stone, 148, 16, 2, 1, false);
		this.add(Blocks.stone, 149, 6, 1, 10, false);
		this.add(Blocks.stone, 152, 17, 3, 1, false);
		this.add(Blocks.stone, 152, 16, 2, 1, false);
		this.add(Blocks.stone, 152, 6, 1, 10, false);
		this.add(Blocks.lava, 147, 18, 8, 2, false);
		this.add(Blocks.lava, 150, 6, 2, 12, false);
		this.add(Blocks.stone, 155, 17, 14, 4, false);
		this.add(Blocks.stone, 160, 16, 4, 1, false);
		this.add(Blocks.stone, 161, 6, 2, 10, false);
		this.add(Blocks.stone, 177, 12, 42, 3, false);
		this.add(Blocks.stone, 180, 11, 4, 1, false);
		this.add(Blocks.stone, 181, 6, 2, 5, false);
		this.add(Blocks.stone, 190, 11, 4, 1, false);
		this.add(Blocks.stone, 191, 6, 2, 5, false);
		this.add(Blocks.stone, 200, 11, 4, 1, false);
		this.add(Blocks.stone, 201, 6, 2, 5, false);
		this.add(Blocks.stone, 210, 11, 4, 1, false);
		this.add(Blocks.stone, 211, 6, 2, 5, false);
		this.add(Blocks.slime, 216, 14, 3, 1, false);
		this.add(Blocks.stone, 219, 0, 67, 32, false);
		this.add(Blocks.stone, 219, 46, 67, 16, false);
	}

	public String getName() {

		return "Didacticiel";
	}
}
