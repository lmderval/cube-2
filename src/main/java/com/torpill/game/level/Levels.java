package com.torpill.game.level;

import com.torpill.game.Game;

public class Levels {

	public static Level level0;
	
	public static void init() {
		
		level0 = new Level0();
		
		level0.init();
	}
	
	public static void register() {
		
		Game.registerLevel(level0);
	}
}
