package com.torpill.game.block.damager;

import com.torpill.game.util.damage.DamageType;

public class LavaBlock extends DamagerBlock {

	@Override
	public boolean isFluid() {

		return true;
	}

	@Override
	public int getDamage() {

		return 4;
	}
	
	@Override
	public DamageType getType() {
		
		return DamageType.FIRE;
	}
}
