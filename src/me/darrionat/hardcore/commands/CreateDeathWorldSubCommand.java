package me.darrionat.hardcore.commands;

import org.bukkit.command.CommandSender;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.MessageService;

public class CreateDeathWorldSubCommand {

	private MessageService messageService;
	private DeathWorldService deathWorldService;

	public CreateDeathWorldSubCommand(Hardcore plugin) {
		this.deathWorldService = plugin.deathWorldService;
		this.messageService = plugin.messageService;
	}

	public void createWorld(CommandSender sender) {
		if (deathWorldService.worldExists()) {
			sender.sendMessage(messageService.getMessage(messageService.deathWorldAlreadyExists));
			return;
		}
		deathWorldService.create();
	}
}