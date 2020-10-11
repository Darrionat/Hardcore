package me.darrionat.hardcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.MessageService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;
import me.darrionat.hardcore.utils.Utils;

public class HardcoreAdminCommand implements CommandExecutor {

	private Hardcore plugin;
	private String permission = "hardcore.admin";

	private ReviveSubCommand reviveSubCommand;
	private HelpSubCommand helpSubCommand;
	private CreateDeathWorldSubCommand createDeathWorldSubCommand;
	private SetSpawnSubCommand setSpawnSubCommand;

	private MessageService messageService;

	public HardcoreAdminCommand(Hardcore plugin, DeathWorldService deathWorldService, MessageService messageService,
			PlayerStatusService playerStatusService, RevivalService revivalService) {
		this.plugin = plugin;
		this.messageService = messageService;
		this.reviveSubCommand = new ReviveSubCommand(plugin, playerStatusService, revivalService, messageService);
		this.helpSubCommand = new HelpSubCommand(plugin, messageService);
		this.createDeathWorldSubCommand = new CreateDeathWorldSubCommand(plugin, deathWorldService, messageService);
		this.setSpawnSubCommand = new SetSpawnSubCommand(plugin, deathWorldService, messageService);

		plugin.getCommand("hcadmin").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!p.hasPermission(permission)) {
				String noPermMessage = messageService.getMessage(messageService.noPermission);
				p.sendMessage(noPermMessage.replace("%permission%", permission));
				return true;
			}
		}

		if (args.length == 0) {
			sendBaseMessage(sender);
			return true;
		}
		if (!isSubCommand(sender, args)) {
			sendBaseMessage(sender);
		}
		return true;
	}

	public boolean isSubCommand(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("help")) {
			if (args.length == 1) {
				helpSubCommand.sendPage(sender, "1");
			} else {
				helpSubCommand.sendPage(sender, args[1]);
			}
			return true;
		}

		// /hcadmin revive player
		if (args[0].equalsIgnoreCase("revive")) {
			if (args.length < 2) {
				sender.sendMessage(Utils.chat("&cCorrect Usage: /hcadmin revive [player]"));
			} else {
				reviveSubCommand.revivePlayer(sender, args[1]);
			}
			return true;
		}

		// /hcadmin createdeathworld
		if (args[0].equalsIgnoreCase("createdeathworld")) {
			createDeathWorldSubCommand.createWorld(sender);
			return true;
		}

		if (args[0].equalsIgnoreCase("setspawn")) {
			if (sender instanceof Player) {
				setSpawnSubCommand.setSpawn((Player) sender);
			} else {
				sender.sendMessage(messageService.getMessage(messageService.onlyPlayersCommand));
			}
			return true;
		}
		return false;
	}

	public void sendBaseMessage(CommandSender sender) {
		sender.sendMessage(Utils.chat("&6&l" + plugin.getName() + " v" + plugin.getDescription().getVersion()));
		sender.sendMessage(Utils.chat(" &6Author: Darrionat"));
		sender.sendMessage(Utils.chat(" &6Support Discord: https://discord.gg/xNKrH5Z"));
		sender.sendMessage(Utils.chat(" &6/hcadmin help for additional information"));
	}
}