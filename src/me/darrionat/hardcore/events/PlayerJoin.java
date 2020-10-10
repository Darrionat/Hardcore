package me.darrionat.hardcore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.darrionat.hardcore.Hardcore;

public class PlayerJoin implements Listener {

	private Hardcore plugin;

	public PlayerJoin(Hardcore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(PlayerJoinEvent e) {
		
	}
}