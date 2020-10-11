package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.darrionat.hardcore.Hardcore;

public class PlayerQuit implements Listener {

	private Hardcore plugin;

	public PlayerQuit(Hardcore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		plugin.statsService.setLastLog(p, System.currentTimeMillis());
	}
}