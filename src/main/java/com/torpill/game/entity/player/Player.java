package com.torpill.game.entity.player;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.torpill.game.entity.Entity;
import com.torpill.game.util.KeyboardManager;
import com.torpill.game.util.TextureManager;

public class Player extends Entity {

	public Player(int x, int y) {

		super(x, y);
	}

	public void update() {

		this.motionX = (this.dead ? 0 : ((KeyboardManager.get(KeyEvent.VK_D) * (1.0 + KeyboardManager.get(KeyEvent.VK_A) * 0.4)) - (KeyboardManager.get(KeyEvent.VK_Q) * (1.0 + KeyboardManager.get(KeyEvent.VK_A) * 0.4))) * (this.onGround ? 1.0 : 1.3));
		this.motionY += (!this.onGround || this.dead ? 0 : KeyboardManager.consume(KeyEvent.VK_SPACE) * 4);

		super.update();
	}

	public Color getColor() {

		return Color.RED;
	}

	public Image getImage() {

		return TextureManager.CUBE;
	}

	public double generateXOffset() {

		return this.posX < 30 ? 0 : posX - 30;
	}

	@Override
	public String getName() {

		return (this.nametag.equals("") ? "entity.player.name" : this.nametag);
	}
}
