package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;
import me.darrionat.hardcore.services.StatsService;

public class PlayerJoin implements Listener {

	private StatsService statsService;
	private PlayerStatusService playerStatusService;
	private DeathWorldService deathWorldService;
	private RevivalService revivalService;

	public PlayerJoin(Hardcore plugin, StatsService statsService, PlayerStatusService playerStatusService,
			DeathWorldService deathWorldService, RevivalService revivalService) {
		this.statsService = statsService;
		this.playerStatusService = playerStatusService;
		this.deathWorldService = deathWorldService;
		this.revivalService = revivalService;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (!statsService.hasJoinedBefore(p)) {
			statsService.setupPlayerStats(p);
			return;
		}
		// If the person was revived while offline, properly respawn them
		World deathWorld = deathWorldService.getWorld();
		if (playerStatusService.playerIsAlive(p) && p.getWorld().equals(deathWorld))
			revivalService.respawnPlayer(p);
	}
}