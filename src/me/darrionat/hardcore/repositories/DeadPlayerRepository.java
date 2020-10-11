package me.darrionat.hardcore.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class DeadPlayerRepository {

	private FileRepository fileRepository;

	public DeadPlayerRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	/**
	 * 
	 * @return A list of all dead player's UUIDs, empty list if none
	 */
	public List<UUID> getDeadPlayers() {
		List<UUID> deadPlayerList = new ArrayList<>();
		for (String uuid : fileRepository.deadPlayersConfiguration.getKeys(false)) {
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
		return fileRepository.deadPlayersConfiguration.getLong(p.getUniqueId().toString());
	}

	public void addPlayer(Player p) {
		fileRepository.deadPlayersConfiguration.set(p.getUniqueId().toString(), System.currentTimeMillis());
		fileRepository.saveConfigFile("deadplayers", fileRepository.deadPlayersConfiguration);
	}

	public void removePlayer(Player p) {
		fileRepository.deadPlayersConfiguration.set(p.getUniqueId().toString(), null);
	}
}