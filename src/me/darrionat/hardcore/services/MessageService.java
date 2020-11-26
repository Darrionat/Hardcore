package me.darrionat.hardcore.services;

import org.bukkit.configuration.file.FileConfiguration;

import me.darrionat.hardcore.repositories.FileRepository;
import me.darrionat.hardcore.utils.Utils;

public class MessageService {

	private FileConfiguration messagesConfig;

	public MessageService(FileRepository fileRepository) {
		messagesConfig = fileRepository.messagesConfiguration;

	}

	private String prefix = "";
	public String deathWorldAlreadyExists = "deathWorldAlreadyExists";
	public String noPermission = "noPermission";
	public String helpHelp = "help.help";
	public String helpRevive = "help.revive";
	public String helpCreateDeathWorld = "help.createDeathWorld";
	public String helpSetSpawn = "help.setSpawn";
	public String playerDoesNotExist = "playerDoesNotExist";
	public String playerIsAlive = "playerIsAlive";
	public String revivedPlayer = "revivedPlayer";
	public String noDeathWorld = "noDeathWorld";
	public String changedDeathSpawn = "changedDeathSpawn";
	public String onlyPlayersCommand = "onlyPlayersCommand";

	// Returns String in case placeholders are used
	public String getMessage(String messageKey) {
		if (messageKey.contains("help.")) {
			return Utils.chat(messagesConfig.getString(messageKey));
		}
		if (messagesConfig.getBoolean("prefix.enabled"))
			prefix = messagesConfig.getString("prefix.string");
		return Utils.chat(prefix + messagesConfig.getString(messageKey));
	}
}