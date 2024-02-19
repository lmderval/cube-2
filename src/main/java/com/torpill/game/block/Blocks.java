package com.torpill.game.block;

import java.awt.Color;

import com.torpill.game.Game;
import com.torpill.game.block.damager.LavaBlock;

public class Blocks {

	public static Block air;
	public static Block stone;
	public static Block lava;
	public static Block slime;

	public static void init() {

		air = new Block().setColor(new Color(0x00000000, true));
		stone = new Block().setTextureName("stone").setColor(Color.DARK_GRAY);
		lava = new LavaBlock().setTextureName("lava").setColor(Color.RED);
		slime = new SlimeBlock().setTextureName("slime").setColor(Color.CYAN);
	}

	public static void register() {

		Game.registerBlock(air);
		Game.registerBlock(stone);
		Game.registerBlock(lava);
		Game.registerBlock(slime);
	}
}
