package com.torpill.game.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {

	static {

		keys = new byte[2000];
		consumed = new byte[2000];
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (consumed[e.getKeyCode()] == 0) {

			keys[e.getKeyCode()] = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		keys[e.getKeyCode()] = 0;
		consumed[e.getKeyCode()] = 0;
	}

	public static byte get(int key) {

		return keys[key];
	}

	public static byte consume(int key) {

		if (consumed[key] == 0 && keys[key] == 1) {

			consumed[key] = 1;

			return 1;
		}

		return 0;
	}

	public static boolean pressed(int key) {

		return keys[key] == 1;
	}

	private static byte keys[];
	private static byte consumed[];
}
