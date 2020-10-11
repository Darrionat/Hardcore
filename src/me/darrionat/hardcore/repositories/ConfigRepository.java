package me.darrionat.hardcore.repositories;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import me.darrionat.hardcore.Hardcore;

public class ConfigRepository {

	private Hardcore plugin;
	private FileConfiguration config;

	public ConfigRepository(Hardcore plugin) {
		this.plugin = plugin;
		config = plugin.getConfig();
	}

	public List<String> getNaturalRegenWorlds() {
		return config.getStringList("allowNaturalRegenWorlds");
	}

	public String getDeathWorldName() {
		return plugin.getConfig().getString("deathWorld.name");
	}

	@Nullable
	public World getDeathWorld() {
		return Bukkit.getWorld(plugin.getConfig().getString("deathWorld.name"));
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
}