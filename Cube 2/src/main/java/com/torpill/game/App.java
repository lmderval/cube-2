package com.torpill.game;

import com.torpill.game.discord.RichPresence;

public class App {

	static {

		DEFAULT_GAME = new Game();
	}

	public static void main(String[] args) {

		Thread thr = new Thread(DEFAULT_GAME);
		thr.start();

		RichPresence.init();
	}

	public final static Game DEFAULT_GAME;
}
