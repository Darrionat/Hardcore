package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.StatsService;

public class PlayerQuit implements Listener {

	private StatsService statsService;

	public PlayerQuit(Hardcore plugin, StatsService statsService) {
		this.statsService = statsService;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		statsService.setLastLog(p, System.currentTimeMillis());
	}
}