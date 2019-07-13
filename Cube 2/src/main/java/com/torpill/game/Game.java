package com.torpill.game;

import java.util.ArrayList;

import com.torpill.game.block.Block;
import com.torpill.game.component.Window;
import com.torpill.game.entity.Entity;
import com.torpill.game.entity.Player;
import com.torpill.game.level.Level;
import com.torpill.game.level.Level0;

public class Game implements Runnable {

	public Game() {

		this.tps = 20;
		this.entities = new ArrayList<Entity>();
		this.blocks = new ArrayList<Block>();
		this.window = new Window(this);
	}

	@Override
	public void run() {

		this.alive = true;
		Level level = new Level0();
		level.init();
		level.load(this);

		Thread thr = new Thread(this.window);
		thr.start();

		while (this.alive) {

			long nano = System.nanoTime();
			long delay = 1000000000 / this.tps; // 1000000000 nanos = 1 second

			while (nano + delay > System.nanoTime());

			this.tick();
		}
	}

	public Window getWindow() {

		return this.window;
	}

	private void tick() {

		for (Entity entity : this.entities) {

			entity.update();
		}

		for (Block block : this.blocks) {

			block.update();
		}

		this.tick++;
	}

	public long getTick() {

		return this.tick;
	}

	public boolean isAlive() {

		return this.alive;
	}

	public void kill() {

		this.alive = false;
	}

	public void restart() {

		this.tick = 0;
	}

	public void register(Entity entity) {

		this.entities.add(entity);
	}

	public void register(Block block) {

		this.blocks.add(block);
	}

	public ArrayList<Entity> getEntities() {

		return this.entities;
	}

	public ArrayList<Block> getBlocks() {

		return this.blocks;
	}

	public void load(Player player, ArrayList<Entity> entities, ArrayList<Block> blocks) {

		this.entities.clear();
		this.blocks.clear();

		this.player = player;

		for (Entity entity : entities) {

			entity.addTo(this);
		}

		for (Block block : blocks) {

			block.addTo(this);
		}

		this.restart();
	}

	public Player getPlayer() {

		return this.player;
	}

	private Window window;
	private boolean alive;
	private long tick;
	private final long tps;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<Block> blocks;
}
