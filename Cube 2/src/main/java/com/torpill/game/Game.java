package com.torpill.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.component.Window;
import com.torpill.game.discord.RichPresence;
import com.torpill.game.entity.Entity;
import com.torpill.game.entity.Player;
import com.torpill.game.event.CubeEvent;
import com.torpill.game.event.Result;
import com.torpill.game.level.Level;
import com.torpill.game.level.Levels;
import com.torpill.game.util.I18n;
import com.torpill.game.util.TextureManager;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Game implements Runnable {

	static {

		bid = 0;
		lid = 0;
		DECORATION = 1024;
		blocksid = new LinkedHashMap<Integer, Block>();
		levelsid = new LinkedHashMap<Integer, Level>();
	}

	public Game() {

		this.state = GameState.INIT;

		this.tps = 20;
		this.entities = new ArrayList<Entity>();
		this.events = new ArrayList<CubeEvent>();
	}

	@Override
	public void run() {

		this.alive = true;

		while (this.alive) {

			this.laststate = this.state;

			long nano = System.nanoTime();
			long delay = 1000000000 / this.tps;

			while (nano + delay > System.nanoTime());

			this.tick();
			this.events();

			if (this.state != this.laststate && this.state != GameState.INIT) {

				this.window.updatePanel();
			}
		}
	}

	public Window getWindow() {

		return this.window;
	}

	private void tick() {

		switch (this.state) {
		case INIT:

			I18n.init();
			TextureManager.init();

			Blocks.init();
			Blocks.register();

			Levels.init();
			Levels.register();

			RichPresence.init();

			this.window = new Window(this);

			Thread thr = new Thread(this.window);
			thr.start();

			this.state = GameState.MAIN;

			break;

		case MAIN:
			break;

		case WORLDS:

			this.state = GameState.LEVELS;
			break;

		case LEVELS:

			this.level = Levels.level0;
			this.level.load(this);

			this.state = GameState.PLAY;

			break;

		case OPTIONS:
			break;

		case CONTROLS:
			break;

		case LANGUAGES:
			break;

		case PLAY:

			if (this.player.isAlive()) {

				for (Entity entity : this.entities) {

					entity.update();
				}

				this.window.setOffset(this.player.generateXOffset(), 0);

				this.tick++;

			} else {

				this.state = GameState.DEATH;
			}
			break;

		case DEATH:

			this.reload();

			break;
		}
	}
	
	private void events() {
		
		for (CubeEvent evt : this.events) {
			
			if (!evt.isCanceled()) {
				
				evt.execute();
			}
			
			Result result = evt.getResult();
			switch (result) {
			case CANCELED:
				break;
				
			case DEFAULT:
				break;
				
			case ERROR:
				break;
			}
		}
		
		this.events.clear();
	}
	
	public void addEvent(CubeEvent evt) {
		
		this.events.add(evt);
	}

	public long getTick() {

		return this.tick;
	}

	public boolean isAlive() {

		return this.alive;
	}

	public void main() {

		this.state = GameState.MAIN;
	}

	public void worlds() {

		this.state = GameState.WORLDS;
	}

	public void options() {

		this.state = GameState.OPTIONS;
	}

	public void languages() {

		this.state = GameState.LANGUAGES;
	}

	public void kill() {

		this.alive = false;

		System.exit(0);
	}

	public void restart() {

		this.tick = 0;
	}

	public void add(Entity entity) {

		this.entities.add(entity);
	}

	public ArrayList<Entity> getEntities() {

		return this.entities;
	}

	public Block[][] getData() {

		return this.data;
	}

	public void load(Player player, ArrayList<Entity> entities, Block data[][]) {

		player.setAlive();

		this.entities.clear();

		this.player = player;

		for (Entity entity : entities) {

			entity.addTo(this);
		}

		this.data = data;

		this.restart();
	}

	public Player getPlayer() {

		return this.player;
	}

	public static void registerBlock(Block block) {

		if (bid < DECORATION) {

			if (block != Blocks.air) {

				block.loadTexture();

			}

			block.setId(bid);
			block.loadDecoration();

			blocksid.put(bid, block);
			blocksid.put(bid + DECORATION, block.decoration);

			bid++;
		}
	}

	public static Block getBlockFromId(int id) {

		return blocksid.get(id);
	}

	public static void registerLevel(Level level) {

		levelsid.put(lid++, level);
	}

	public void reload() {

		this.entities.clear();

		this.level.reinit();
		this.level.load(this);

		this.state = GameState.PLAY;
	}

	public void discord() {

		DiscordRPC lib = DiscordRPC.INSTANCE;
		DiscordRichPresence presence = new DiscordRichPresence();
		presence.startTimestamp = RichPresence.startTimestamp;
		presence.details = I18n.format("discord.mainmenu");
		presence.largeImageKey = "cube";
		lib.Discord_UpdatePresence(presence);
	}

	public static enum GameState {

		INIT, MAIN, WORLDS, LEVELS, OPTIONS, CONTROLS, LANGUAGES, PLAY, DEATH
	}

	private Window window;
	private boolean alive;
	private long tick;
	private final long tps;
	private Player player;
	private ArrayList<Entity> entities;
	private Block data[][];
	private Level level;
	private static int bid, lid;
	public static final int DECORATION;
	private static LinkedHashMap<Integer, Block> blocksid;
	private static LinkedHashMap<Integer, Level> levelsid;
	public GameState state;
	private GameState laststate;
	private ArrayList<CubeEvent> events;
}
