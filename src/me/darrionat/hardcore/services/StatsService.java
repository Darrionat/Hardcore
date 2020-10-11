package me.darrionat.hardcore.services;

import org.bukkit.entity.Player;

import me.darrionat.hardcore.repositories.PlayerStatsRepository;

public class StatsService {

	private PlayerStatsRepository playerStatsRepository;

	public StatsService(PlayerStatsRepository playerStatsRepository) {
		this.playerStatsRepository = playerStatsRepository;
	}

	public boolean hasJoinedBefore(Player p) {
		return playerStatsRepository.playerExists(p);
	}

	public void setupPlayerStats(Player p) {
		playerStatsRepository.createPlayer(p);
		playerStatsRepository.setFirstLog(p, System.currentTimeMillis());
	}

	public void setLastLog(Player p, long lastLog) {
		playerStatsRepository.setLastLog(p, lastLog);
	}
}