package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.darrionat.hardcore.Hardcore;

public class PlayerJoin implements Listener {

	private Hardcore plugin;

	public PlayerJoin(Hardcore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	// If the person was revived while offline, properly respawn them
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (plugin.playerStatusService.playerIsAlive(p))
			return;
		
	}
}