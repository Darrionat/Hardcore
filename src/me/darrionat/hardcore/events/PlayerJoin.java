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

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (!plugin.statsService.hasJoinedBefore(p))
			plugin.statsService.setupPlayerStats(p);

		// TODO: Determine what to do here and what will need to be done

		// If the person was revived while offline, properly respawn them
		// if (plugin.playerStatusService.playerIsAlive(p))
		// return;
	}
}