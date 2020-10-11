package me.darrionat.hardcore.services;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.darrionat.hardcore.repositories.DeadPlayerRepository;
import me.darrionat.hardcore.repositories.PlayerStatsRepository;

public class RevivalService {

	private DeadPlayerRepository deadPlayerRepository;
	private PlayerStatsRepository playerStatsRepository;

	public RevivalService(DeadPlayerRepository deadPlayerRepository, PlayerStatsRepository playerStatsRepository) {
		this.deadPlayerRepository = deadPlayerRepository;
		this.playerStatsRepository = playerStatsRepository;
	}

	public void revivePlayer(Player p) {
		deadPlayerRepository.removePlayer(p);

		List<Long> revivals = playerStatsRepository.getRevivals(p);
		revivals.add(System.currentTimeMillis());
		playerStatsRepository.setRevivals(p, revivals);

		// Actions will be handled on login
		if (!Bukkit.getOnlinePlayers().contains(p)) {
			return;
		}
		// TODO
		// if player is online, handle teleportation and etc then
	}
}