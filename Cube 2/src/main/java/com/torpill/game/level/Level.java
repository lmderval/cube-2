package com.torpill.game.level;

import java.util.ArrayList;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.discord.RichPresence;
import com.torpill.game.entity.Entity;
import com.torpill.game.entity.Player;
import com.torpill.game.util.I18n;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public abstract class Level {

	protected Level(int x, int y) {

		this.x = x;
		this.y = y;
		this.entities = new ArrayList<Entity>();
		this.data = new Block[this.WIDTH = 500][this.HEIGHT = 100];

		for (int i = 0; i < this.WIDTH; i++) {

			for (int j = 0; j < this.HEIGHT; j++) {

				this.data[i][j] = Blocks.air;
			}
		}
		this.name = this.getClass().getSimpleName().toLowerCase();
	}

	public void reinit() {

		this.entities = new ArrayList<Entity>();
		this.data = new Block[this.WIDTH][this.HEIGHT];

		for (int i = 0; i < this.WIDTH; i++) {

			for (int j = 0; j < this.HEIGHT; j++) {

				this.data[i][j] = Blocks.air;
			}
		}

		this.init();
	}

	public void init() {

		this.player = new Player(this.x, this.y);
		this.player.addTo(this);
	}

	public void load(Game game) {

		game.load(this.player, this.entities, this.data);

		DiscordRPC lib = DiscordRPC.INSTANCE;
		DiscordRichPresence presence = new DiscordRichPresence();
		presence.startTimestamp = RichPresence.startTimestamp;
		presence.details = "En jeu";
		presence.state = this.getName();
		presence.largeImageKey = "cube";
		lib.Discord_UpdatePresence(presence);
	}

	public void add(Entity entity) {

		this.entities.add(entity);
	}

	public void add(Block block, int x, int y, int width, int height, boolean decoration) {

		for (int bx = x; bx < x + width; bx++) {

			if (x > this.WIDTH) {

				continue;
			}

			for (int by = y; by < y + height; by++) {

				if (y > this.HEIGHT) {

					continue;
				}

				if (decoration) {

					this.data[bx][by] = block.decoration;

				} else {

					this.data[bx][by] = block;
				}
			}
		}
	}

	public String getName() {

		return I18n.format("level." + this.name + ".name");
	}

	private Player player;
	protected String name;
	private int x, y;
	private ArrayList<Entity> entities;
	private final int WIDTH, HEIGHT;
	private Block data[][];
}
