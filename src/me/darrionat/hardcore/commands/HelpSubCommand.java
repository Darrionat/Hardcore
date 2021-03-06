package me.darrionat.hardcore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.MessageService;
import me.darrionat.hardcore.utils.Utils;

public class HelpSubCommand {

	private String topMsg;
	private String pageAmtString;
	private List<String> commandList = new ArrayList<>();

	public HelpSubCommand(Hardcore plugin, MessageService messageService) {
		topMsg = "&6&l" + plugin.getName() + " v" + plugin.getDescription().getVersion() + " Commands";

		commandList.add(messageService.getMessage(messageService.helpHelp));
		commandList.add(messageService.getMessage(messageService.helpRevive));
		commandList.add(messageService.getMessage(messageService.helpCreateDeathWorld));
		commandList.add(messageService.getMessage(messageService.helpSetSpawn));
		pageAmtString = String.valueOf((commandList.size() + 5 - 1) / 5);
	}

	public void sendPage(CommandSender sender, String pageString) {
		int pageNo = stringToInt(pageString);
		sender.sendMessage(Utils.chat(topMsg));

		for (int i = pageNo * 5 - 5; i <= (pageNo * 5 - 1); i++) {
			if (i == (commandList.size())) {
				break;
			}
			sender.sendMessage(Utils.chat(" " + commandList.get(i)));
		}

		String helpPages = Utils.chat("&6Page " + pageString + "/" + pageAmtString);
		sender.sendMessage(Utils.chat(helpPages));
	}

	private int stringToInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException exe) {
			return 1;
		}
	}
}