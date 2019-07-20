package com.torpill.game.discord;

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
		presence = new DiscordRichPresence();
		presence.startTimestamp = System.currentTimeMillis() / 1000;
		presence.details = "Testing RPC";
		presence.largeImageKey = "cube";
		lib.Discord_UpdatePresence(presence);
		// in a worker thread
		new Thread(() -> {

			while (!Thread.currentThread().isInterrupted()) {

				lib.Discord_RunCallbacks();
				try {

					Thread.sleep(2000);

				} catch (InterruptedException ignored) {}
			}
		}, "RPC-Callback-Handler").start();
	}

	private static DiscordRichPresence presence;
}