package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;
import me.darrionat.hardcore.services.StatsService;
import me.darrionat.hardcore.utils.Utils;

public class PlayerLogin implements Listener {

	private StatsService statsService;
	private PlayerStatusService playerStatusService;
	private DeathWorldService deathWorldService;
	private RevivalService revivalService;

	public PlayerLogin(Hardcore plugin, StatsService statsService, PlayerStatusService playerStatusService,
			DeathWorldService deathWorldService, RevivalService revivalService) {
		this.statsService = statsService;
		this.playerStatusService = playerStatusService;
		this.deathWorldService = deathWorldService;
		this.revivalService = revivalService;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerLoginEvent e) {
		Player p = e.getPlayer();

		if (!statsService.hasJoinedBefore(p)) {
			statsService.setupPlayerStats(p);
			return;
		}
		if (playerStatusService.playerIsAlive(p))
			return;
		if (playerStatusService.timeExpired(p)) {
			revivalService.respawnPlayer(p);
			return;
		}
		// Player is still dead
		e.setKickMessage(Utils.chat("You are currently dead! You will be able to respawn 48 hours after your death."));
		e.setResult(Result.KICK_BANNED);

		// If the person was revived while offline, properly respawn them
		/*
		 * World deathWorld = deathWorldService.getWorld(); if
		 * (playerStatusService.playerIsAlive(p) && p.getWorld().equals(deathWorld))
		 * revivalService.respawnPlayer(p);
		 */
	}
}