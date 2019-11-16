package com.torpill.game.entity.monsters;

import java.awt.Image;

import com.torpill.game.util.TextureManager;

public class EntityVenom extends EntityMonster {

	public EntityVenom(int x, int y) {

		super(x, y);

		this.motionX = 0.2;
	}

	@Override
	public Image getImage() {

		return TextureManager.VENOM;
	}

	@Override
	public String getName() {

		return (this.nametag.equals("") ? "entity.venom.name" : this.nametag);
	}
}
