package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.MessageService;

public class PlayerDeath implements Listener {

	private Hardcore plugin;
	private MessageService messageService;

	public PlayerDeath(Hardcore plugin) {
		this.plugin = plugin;
		this.messageService = plugin.messageService;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (!plugin.playerStatusService.playerIsAlive(p)) {
			return;
		}

		plugin.playerStatusService.setPlayerToDead(p);
		if (!plugin.deathWorldService.movePlayerToHell(p)) {
			plugin.log(messageService.getMessage(messageService.noDeathWorld));
		}
	}
}