package me.darrionat.hardcore.commands;

import org.bukkit.entity.Player;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.MessageService;

public class SetSpawnSubCommand {

	private MessageService messageService;
	private DeathWorldService deathWorldService;

	public SetSpawnSubCommand(Hardcore plugin, DeathWorldService deathWorldService, MessageService messageService) {
		this.deathWorldService = deathWorldService;
	}

	public void setSpawn(Player p) {
		if (deathWorldService.setSpawn(p.getLocation())) {
			p.sendMessage(messageService.getMessage(messageService.changedDeathSpawn));
		} else {
			p.sendMessage(messageService.getMessage(messageService.noDeathWorld));
		}
	}
}