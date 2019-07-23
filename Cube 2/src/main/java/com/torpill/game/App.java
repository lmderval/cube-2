package com.torpill.game;

import com.torpill.game.discord.RichPresence;
import com.torpill.game.util.TextureManager;

public class App {

	static {

		DEFAULT_GAME = new Game();
	}

	public static void main(String[] args) {

		RichPresence.init();
		TextureManager.init();

		Thread thr = new Thread(DEFAULT_GAME);
		thr.start();
	}

	public final static Game DEFAULT_GAME;
}
