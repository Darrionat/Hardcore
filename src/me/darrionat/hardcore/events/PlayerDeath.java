package me.darrionat.hardcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.darrionat.hardcore.Hardcore;

public class PlayerDeath implements Listener {

	private Hardcore plugin;

	public PlayerDeath(Hardcore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		plugin.playerStatusService.setPlayerToDead(p);
	}
}