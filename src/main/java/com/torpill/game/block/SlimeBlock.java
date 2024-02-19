package com.torpill.game.block;

import com.torpill.game.Game;
import com.torpill.game.entity.Entity;

public class SlimeBlock extends Block {

	public SlimeBlock() {

		super();
	}

	@Override
	public void onTop(Entity entity, int x, int y, Game game) {

		entity.motionY = 2;
	}
	
	@Override
	public int getFallDamage() {
		
		return 0;
	}
}
