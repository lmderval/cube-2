package com.torpill.game.block.damager;

import com.torpill.game.block.Block;
import com.torpill.game.util.damage.IDamager;

public abstract class DamagerBlock extends Block implements IDamager {

	@Override
	public boolean isDamager() {

		return true;
	}
}
