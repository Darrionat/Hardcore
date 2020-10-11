package me.darrionat.hardcore.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.utils.Utils;

public class HelpSubCommand {

	private String topMsg;
	private String pageAmtString;
	private List<String> commandList;

	public HelpSubCommand(Hardcore plugin) {
		topMsg = "&6&l" + plugin.getName() + " v" + plugin.getDescription().getVersion() + " Commands";
		pageAmtString = String.valueOf((commandList.size() + 5 - 1) / 5);
		commandList.add("&6/hcadmin revive [player] &7- Force revive a dead player");
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
