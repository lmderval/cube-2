package com.torpill.game.level;

import java.util.ArrayList;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.entity.Entity;
import com.torpill.game.entity.Player;

public abstract class Level {

	public Level(int x, int y) {

		this.x = x;
		this.y = y;
		this.entities = new ArrayList<Entity>();
		this.blocks = new ArrayList<Block>();
	}

	public void init() {

		this.player = new Player(this.x, this.y);
		this.player.addTo(this);
	}

	public void load(Game game) {

		game.load(this.player, this.entities, this.blocks);
	}

	public void register(Entity entity) {

		this.entities.add(entity);
	}

	public void register(Block block) {

		this.blocks.add(block);
	}

	private Player player;
	private int x, y;
	private ArrayList<Entity> entities;
	private ArrayList<Block> blocks;
}
