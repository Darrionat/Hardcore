package me.darrionat.hardcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;
import me.darrionat.hardcore.utils.Utils;

public class ReviveSubCommand {

	private PlayerStatusService playerStatusService;
	private RevivalService revivalService;

	public ReviveSubCommand(Hardcore plugin) {
		this.playerStatusService = plugin.playerStatusService;
		this.revivalService = plugin.revivalService;

	}

	@SuppressWarnings("deprecation")
	public void revivePlayer(CommandSender sender, String targetName) {
		OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(targetName);
		if (oPlayer.getPlayer() == null) {
			sender.sendMessage(Utils.chat("&cThat player does not exist!"));
			return;
		}

		Player targetPlayer = oPlayer.getPlayer();
		if (playerStatusService.playerIsAlive(targetPlayer)) {
			sender.sendMessage(Utils.chat("&cThat player is already alive!"));
			return;
		}
		sender.sendMessage(Utils.chat("Revived " + targetPlayer.getName()));
		revivalService.revivePlayer(targetPlayer);
	}
}