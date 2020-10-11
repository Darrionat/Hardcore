package me.darrionat.hardcore.services;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import me.darrionat.hardcore.repositories.ConfigRepository;

public class DeathWorldService {

	private ConfigRepository configRepository;

	public DeathWorldService(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}

	public boolean worldExists() {
		return Bukkit.getWorld("name") != null;
	}

	public String getName() {
		return configRepository.getDeathWorldName();
	}

	public void create() {
		WorldCreator worldCreator = new WorldCreator(getName());
		worldCreator.environment(Environment.NETHER);
		Bukkit.getServer().createWorld(worldCreator);
	}

	@Nullable
	public World getWorld() {
		return Bukkit.getWorld(getName());
	}

	public boolean movePlayerToHell(Player p) {
		Location spawn = configRepository.getDeathWorldSpawn();
		if (spawn == null) {
			return false;
		}
		return p.teleport(spawn);
	}
}