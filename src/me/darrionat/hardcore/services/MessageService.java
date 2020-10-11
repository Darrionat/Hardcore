package me.darrionat.hardcore.services;

import org.bukkit.configuration.file.FileConfiguration;

import me.darrionat.hardcore.repositories.FileRepository;
import me.darrionat.hardcore.utils.Utils;

public class MessageService {

	private FileConfiguration messagesConfig;
	private String prefix = "";

	public MessageService(FileRepository fileRepository) {
		messagesConfig = fileRepository.getDataConfig("messages");

		boolean prefixEnabled = messagesConfig.getBoolean("prefix.enabled");
		if (prefixEnabled)
			prefix = messagesConfig.getString("prefix.string");
	}

	public String deathWorldAlreadyExists = "deathWorldAlreadyExists";
	public String noPermission = "noPermission";
	public String helpRevive = "help.revive";
	public String helpCreateDeathWorld = "help.createDeathWorld";
	public String playerDoesNotExist = "playerDoesNotExist";
	public String playerIsAlive = "playerIsAlive";
	public String revivedPlayer = "revivedPlayer";
	public String noDeathWorld = "noDeathWorld";

	// Returns String in case placeholders are used
	public String getMessage(String messageKey) {
		return Utils.chat(prefix + messagesConfig.getString(messageKey));
	}
}