package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.darrionat.hardcore.Hardcore;

public class PlayerRespawn implements Listener {

	private Hardcore plugin;

	public PlayerRespawn(Hardcore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {

	}
}