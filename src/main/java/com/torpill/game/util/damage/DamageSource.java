package com.torpill.game.util.damage;

public class DamageSource {

	public DamageSource(IDamager damager) {

		this.damager = damager;
		this.damage = damager.getDamage();
		this.type = damager.getType();
	}
	
	public final IDamager damager;
	public final int damage;
	public final DamageType type;
}
