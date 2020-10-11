package me.darrionat.hardcore.services;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.darrionat.hardcore.repositories.ConfigRepository;

public class DeathService {

	private Location deathWorldSpawn;

	public DeathService(ConfigRepository configRepository) {
		deathWorldSpawn = configRepository.getDeathWorldSpawn();
	}

	public void movePlayerToHell(Player p) {
		p.teleport(deathWorldSpawn);
	}
}