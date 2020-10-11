package me.darrionat.hardcore.repositories;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigRepository {

	private FileRepository fileRepository;
	private FileConfiguration config;

	public ConfigRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
		config = fileRepository.getDataConfig("config");
	}

	public List<String> getNaturalRegenWorlds() {
		return config.getStringList("allowNaturalRegenWorlds");
	}

	public String getDeathWorldName() {
		return config.getString("deathWorld.name");
	}

	@Nullable
	public World getDeathWorld() {
		return Bukkit.getWorld(config.getString("deathWorld.name"));
	}

	@Nullable
	public Location getDeathWorldSpawn() {
		if (getDeathWorld() == null) {
			return null;
		}
		ConfigurationSection section = config.getConfigurationSection("deathWorld.spawn");
		double x = section.getDouble("x");
		double y = section.getDouble("y");
		double z = section.getDouble("z");
		float yaw = (float) section.getDouble("yaw");
		float pitch = (float) section.getDouble("pitch");
		return new Location(getDeathWorld(), x, y, z, yaw, pitch);
	}

	/**
	 * @return Whether it was done successfully or not
	 */
	public boolean setDeathWorldSpawn(Location location) {
		if (getDeathWorld() == null) {
			return false;
		}
		ConfigurationSection section = config.getConfigurationSection("deathWorld.spawn");
		section.set("x", location.getX());
		section.set("y", location.getY());
		section.set("z", location.getZ());
		section.set("yaw", location.getYaw());
		section.set("pitch", location.getPitch());
		fileRepository.saveConfigFile("config", config);
		return true;
	}
}