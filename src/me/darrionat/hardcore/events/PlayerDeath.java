package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.MessageService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.utils.Utils;

public class PlayerDeath implements Listener {

	private Hardcore plugin;
	private MessageService messageService;
	private PlayerStatusService playerStatusService;
	private DeathWorldService deathWorldService;

	public PlayerDeath(Hardcore plugin, MessageService messageService, PlayerStatusService playerStatusService,
			DeathWorldService deathWorldService) {
		this.plugin = plugin;
		this.messageService = messageService;
		this.playerStatusService = playerStatusService;
		this.deathWorldService = deathWorldService;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (!playerStatusService.playerIsAlive(p)) {
			return;
		}

		playerStatusService.setPlayerToDead(p);
		p.kickPlayer(Utils.chat("&cYou died! You can join again 48 hours after your death!"));
		/*
		 * p.spigot().respawn(); if (!deathWorldService.movePlayerToHell(p)) {
		 * plugin.log(messageService.getMessage(messageService.noDeathWorld)); }
		 */
	}
}