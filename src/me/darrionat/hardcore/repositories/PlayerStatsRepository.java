package me.darrionat.hardcore.repositories;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlayerStatsRepository {

	private FileConfiguration statsDataConfig;
	private FileRepository fileRepository;

	public PlayerStatsRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
		statsDataConfig = fileRepository.statsDataConfig;
	}

	public void createPlayer(Player p) {
		ConfigurationSection section = statsDataConfig.createSection(p.getUniqueId().toString());
		section.createSection("firstLog");
		section.createSection("lastLog");
		section.createSection("lastLocation");
		fileRepository.saveConfigFile("stats", statsDataConfig);
	}

	public boolean playerExists(Player p) {
		return !(statsDataConfig.getConfigurationSection(p.getUniqueId().toString()) == null);
	}

	public long getFirstLog(Player p) {
		return statsDataConfig.getLong(p.getUniqueId().toString() + ".firstLog");
	}

	public void setFirstLog(Player p, long firstLog) {
		statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).set("firstLog", firstLog);
		fileRepository.saveConfigFile("stats", statsDataConfig);
	}

	public long getLastLog(Player p) {
		return statsDataConfig.getLong(p.getUniqueId().toString() + ".lastLog");
	}

	public void setLastLog(Player p, long lastLog) {
		statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).set("lastLog", lastLog);
		fileRepository.saveConfigFile("stats", statsDataConfig);
	}

	public Location getLastLocation(Player p) {
		ConfigurationSection locationSection = statsDataConfig
				.getConfigurationSection(p.getUniqueId().toString() + ".lastLocation");
		World world = Bukkit.getWorld(locationSection.getString("name"));
		double x = locationSection.getDouble("x");
		double y = locationSection.getDouble("y");
		double z = locationSection.getDouble("z");
		return new Location(world, x, y, z);
	}

	public void setLastLocation(Player p, Location location) {
		ConfigurationSection locationSection = statsDataConfig
				.getConfigurationSection(p.getUniqueId().toString() + ".lastLocation");
		locationSection.set("world", location.getWorld().getName());
		locationSection.set("x", location.getX());
		locationSection.set("y", location.getY());
		locationSection.set("z", location.getZ());
		fileRepository.saveConfigFile("stats", statsDataConfig);
	}

	public List<Long> getRevivals(Player p) {
		return statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).getLongList("revivals");
	}

	public void setRevivals(Player p, List<Long> revivals) {
		statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).set("revivals", revivals);
		fileRepository.saveConfigFile("stats", statsDataConfig);
	}
}