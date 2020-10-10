package me.darrionat.hardcore.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.darrionat.hardcore.services.FileService;

public class DeadPlayerRepository {

	private FileService fileService;

	public DeadPlayerRepository(FileService fileService) {
		this.fileService = fileService;
	}

	/**
	 * 
	 * @return A list of all dead player's UUIDs, empty list if none
	 */
	public List<UUID> getDeadPlayers() {
		List<UUID> deadPlayerList = new ArrayList<>();
		for (String uuid : fileService.deadPlayersConfiguration.getKeys(false)) {
			deadPlayerList.add(UUID.fromString(uuid));
		}
		return deadPlayerList;
	}

	/**
	 * 
	 * @param p Player who is dead
	 * @return Time of player's death in milliseconds
	 */
	public long getDeathTimeMillis(Player p) {
		return fileService.deadPlayersConfiguration.getLong(p.getUniqueId().toString());
	}

	public void removePlayer(Player p) {
		fileService.deadPlayersConfiguration.set(p.getUniqueId().toString(), null);
	}

	public void addPlayer(Player p) {
		fileService.deadPlayersConfiguration.set(p.getUniqueId().toString(), System.currentTimeMillis());
	}
}