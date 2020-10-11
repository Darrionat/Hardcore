package me.darrionat.hardcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.MessageService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;

public class ReviveSubCommand {

	private PlayerStatusService playerStatusService;
	private RevivalService revivalService;
	private MessageService messageService;

	public ReviveSubCommand(Hardcore plugin) {
		this.playerStatusService = plugin.playerStatusService;
		this.revivalService = plugin.revivalService;
		this.messageService = plugin.messageService;
	}

	@SuppressWarnings("deprecation")
	public void revivePlayer(CommandSender sender, String targetName) {
		OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(targetName);
		if (oPlayer.getPlayer() == null) {
			sender.sendMessage(messageService.getMessage(messageService.playerDoesNotExist));
			return;
		}

		Player targetPlayer = oPlayer.getPlayer();
		if (playerStatusService.playerIsAlive(targetPlayer)) {
			sender.sendMessage(messageService.getMessage(messageService.playerIsAlive));
			return;
		}
		String revivalMessage = messageService.getMessage(messageService.revivedPlayer);
		sender.sendMessage(revivalMessage.replace("%player%", targetName));
		revivalService.revivePlayer(targetPlayer);
	}
}