package com.torpill.game.util.damage;

import com.torpill.game.block.Block;

public class FallingState implements IDamager {

	public FallingState(Block block) {

		this.block = block;
	}
	
	@Override
	public int getDamage() {

		return this.block.getFallDamage();
	}

	@Override
	public DamageType getType() {

		return DamageType.FALL;
	}

	private Block block;
}
