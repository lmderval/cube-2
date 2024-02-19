package com.torpill.game.util.damage;


public class MashedUpState implements IDamager {

	@Override
	public int getDamage() {

		return 1;
	}

	@Override
	public DamageType getType() {

		return DamageType.MASH;
	}
}
