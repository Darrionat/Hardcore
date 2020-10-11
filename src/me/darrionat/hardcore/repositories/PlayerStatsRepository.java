package me.darrionat.hardcore.repositories;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.darrionat.hardcore.Hardcore;

public class PlayerStatsRepository {

	private Hardcore plugin;
	private FileConfiguration statsDataConfig;

	public PlayerStatsRepository(Hardcore plugin) {
		this.plugin = plugin;
		statsDataConfig = plugin.fileService.statsDataConfig;
	}

	public void createPlayer(Player p) {
		ConfigurationSection section = statsDataConfig.createSection(p.getUniqueId().toString());
		section.createSection("firstLog");
		section.createSection("lastLog");
		section.createSection("lastLocation");
		plugin.fileService.saveConfigFile("stats", statsDataConfig);
	}

	public long getFirstLog(Player p) {
		return statsDataConfig.getLong(p.getUniqueId().toString() + ".firstLog");
	}

	public void setFirstLog(Player p, long firstLog) {
		statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).set("firstLog", firstLog);
		plugin.fileService.saveConfigFile("stats", statsDataConfig);
	}

	public long getLastLog(Player p) {
		return statsDataConfig.getLong(p.getUniqueId().toString() + ".lastLog");
	}

	public void setLastLog(Player p, long lastLog) {
		statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).set("lastLog", lastLog);
		plugin.fileService.saveConfigFile("stats", statsDataConfig);
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
		plugin.fileService.saveConfigFile("stats", statsDataConfig);
	}

	public List<Long> getRevivals(Player p) {
		return statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).getLongList("revivals");
	}

	public void setRevivals(Player p, List<Long> revivals) {
		statsDataConfig.getConfigurationSection(p.getUniqueId().toString()).set("revivals", revivals);
		plugin.fileService.saveConfigFile("stats", statsDataConfig);
	}
}