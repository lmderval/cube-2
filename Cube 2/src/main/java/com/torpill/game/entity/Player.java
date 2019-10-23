package com.torpill.game.entity;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.torpill.game.util.I18n;
import com.torpill.game.util.KeyboardManager;
import com.torpill.game.util.TextureManager;

public class Player extends Entity {

	public Player(int x, int y) {

		super(x, y);

		this.width = 3;
		this.height = 3;
	}

	public void update() {

		this.motionX = (this.dead ? 0 : ((KeyboardManager.get(KeyEvent.VK_D) * (1 + KeyboardManager.get(KeyEvent.VK_A))) - (KeyboardManager.get(KeyEvent.VK_Q) * (1 + KeyboardManager.get(KeyEvent.VK_A)))) * (this.onGround ? 1 : 2));
		this.motionY += (!this.onGround || this.dead ? 0 : KeyboardManager.consume(KeyEvent.VK_SPACE) * 4);

		super.update();
	}

	public Color getColor() {

		return Color.RED;
	}

	public Image getImage() {

		return TextureManager.CUBE;
	}

	public int generateXOffset() {

		return (this.posX < 30 ? 0 : posX - 30);
	}
	
	@Override
	public String getName() {
	
		return (this.nametag.equals("") ? I18n.format("entity.player.name") : this.nametag);
	}
}
