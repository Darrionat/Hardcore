package me.darrionat.hardcore.services;

import org.bukkit.entity.Player;

import me.darrionat.hardcore.repositories.DeadPlayerRepository;

public class PlayerStatusService {

	private DeadPlayerRepository deadPlayerRepository;

	public PlayerStatusService(DeadPlayerRepository deadPlayerRepository) {
		this.deadPlayerRepository = deadPlayerRepository;
	}

	public boolean timeExpired(Player p) {
		long deathTime = deadPlayerRepository.getDeathTimeMillis(p);
		long banEndTime = deathTime + 48 * 3600 * 1000;
		return banEndTime < System.currentTimeMillis();
	}

	public boolean playerIsAlive(Player p) {
		return !deadPlayerRepository.getDeadPlayers().contains(p.getUniqueId());
	}

	public void setPlayerToDead(Player p) {
		deadPlayerRepository.addPlayer(p);
	}
}