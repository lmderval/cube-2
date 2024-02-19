package com.torpill.game.level;

import com.torpill.game.block.Blocks;
import com.torpill.game.entity.monsters.EntityVenom;
import com.torpill.game.level.structure.StructureBridge;

public class Level0 extends Level {

	public Level0() {

		super(0, 13);
	}

	public void init() {

		super.init();

		this.add(Blocks.lava, 0, 0, 500, 6, false);
		this.add(StructureBridge.create(6, 3), 0, 6);
		this.add(StructureBridge.create(2, 3), 70, 6);
		this.add(StructureBridge.create(3, 10), 95, 6);
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
		this.add(StructureBridge.create(4, 5), 177, 6);
		this.add(Blocks.stone, 217, 12, 2, 2, false);
		this.add(Blocks.slime, 216, 14, 3, 1, false);
		this.add(Blocks.stone, 219, 0, 67, 32, false);
		this.add(Blocks.stone, 219, 46, 67, 16, false);
		
		this.add(new EntityVenom(28, 13));
	}
}
