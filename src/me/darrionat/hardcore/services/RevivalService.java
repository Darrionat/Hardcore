package me.darrionat.hardcore.services;

import org.bukkit.entity.Player;

import me.darrionat.hardcore.repositories.DeadPlayerRepository;

public class RevivalService {

	private DeadPlayerRepository deadPlayerRepository;

	public RevivalService(DeadPlayerRepository deadPlayerRepository) {
		this.deadPlayerRepository = deadPlayerRepository;
	}

	public void revivePlayer(Player p) {
		deadPlayerRepository.removePlayer(p);
		// TODO
	}

}
