package com.torpill.game.block;

import java.awt.Color;
import java.awt.Image;

import com.torpill.game.Game;
import com.torpill.game.entity.Entity;
import com.torpill.game.level.Level;
import com.torpill.game.util.TextureManager;

public class Block {

	protected Block() {

		this.color = Color.BLACK;
	}

	public int getId() {

		return this.id;
	}

	public Block setColor(Color color) {

		this.color = color;

		return this;
	}

	public Color getColor() {

		return this.color;
	}

	public Image getImage() {

		return this.texture;
	}

	public boolean isFluid() {

		return false;
	}

	public void setId(int id) {

		this.id = id;
	}

	public Block setTextureName(String texture) {

		this.texturename = texture;

		return this;
	}

	public void loadTexture() {

		this.texture = TextureManager.get("/textures/blocks/" + this.texturename + ".png");

		if (this.texture == null) {

			this.texture = TextureManager.defaultBlock();
		}
	}

	public void loadDecoration() {

		this.decoration = new BlockDecoration(this);
	}

	public boolean isDecoration() {

		return false;
	}

	public void onTop(Entity entity, int x, int y, Game game) {

	}

	protected Game game;
	protected Level level;
	private int id;
	private String texturename;
	private Image texture;
	public BlockDecoration decoration;
	private Color color;
}
