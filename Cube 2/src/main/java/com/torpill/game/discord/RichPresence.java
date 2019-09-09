package com.torpill.game.discord;

import com.torpill.game.util.I18n;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class RichPresence {

	public static void init() {

		DiscordRPC lib = DiscordRPC.INSTANCE;
		String applicationId = "602167575736090624";
		String steamId = "";
		DiscordEventHandlers handlers = new DiscordEventHandlers();
		handlers.ready = (user) -> System.out.println("Ready!");
		lib.Discord_Initialize(applicationId, handlers, true, steamId);
		DiscordRichPresence presence = new DiscordRichPresence();
		presence.startTimestamp = startTimestamp;
		presence.details = I18n.format("discord.mainmenu");
		presence.largeImageKey = "cube";
		lib.Discord_UpdatePresence(presence);

		new Thread(() -> {

			while (!Thread.currentThread().isInterrupted()) {

				lib.Discord_RunCallbacks();
				try {

					Thread.sleep(2000);

				} catch (InterruptedException ignored) {}
			}
		}, "RPC-Callback-Handler").start();
	}

	public static final long startTimestamp = System.currentTimeMillis() / 1000;
}