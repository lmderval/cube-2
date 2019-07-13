package com.torpill.game;

public class App {

	static {

		DEFAULT_GAME = new Game();
	}

	public static void main(String[] args) {

		Thread thr = new Thread(DEFAULT_GAME);
		thr.start();
	}

	public final static Game DEFAULT_GAME;
}
