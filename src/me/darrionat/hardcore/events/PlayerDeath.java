package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.darrionat.hardcore.Hardcore;

public class PlayerDeath implements Listener {

	private Hardcore plugin;

	public PlayerDeath(Hardcore plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (plugin.playerStatusService.playerIsAlive(p)) {
			plugin.playerStatusService.setPlayerToDead(p);
			plugin.deathService.movePlayerToHell(p);
		}
	}
}