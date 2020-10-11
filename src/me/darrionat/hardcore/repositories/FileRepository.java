package me.darrionat.hardcore.repositories;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.utils.Utils;

public class FileRepository {

	private Hardcore plugin;

	public FileRepository(Hardcore plugin) {
		this.plugin = plugin;

		setupFiles();
	}

	// Files and File configurations here
	private FileConfiguration dataConfig;
	private File dataFile;

	public FileConfiguration deadPlayersConfiguration;
	public FileConfiguration statsDataConfig;
	// -------------------------------------

	private void setupFiles() {
		plugin.log("Setting up files");
		Set<String> configFiles = new HashSet<String>();
		configFiles.add("config");
		setupConfigurationFiles(configFiles);

		Set<String> dataFiles = new HashSet<String>();
		dataFiles.add("deadplayers");
		dataFiles.add("stats");
		setupDataFiles(dataFiles);

		deadPlayersConfiguration = getDataConfig("deadplayers");
	}

	private void setupConfigurationFiles(Set<String> configFiles) {
		for (String fileName : configFiles) {
			if (!fileExists(fileName)) {
				plugin.log("Saving " + fileName + ".yml");
				plugin.saveResource(fileName + ".yml", false);
				continue;
			}
			plugin.log("Updating " + fileName + ".yml");
			matchConfig(fileName);
		}
	}

	private void setupDataFiles(Set<String> dataFiles) {
		for (String fileName : dataFiles) {
			if (!fileExists(fileName)) {
				setup(fileName);
			}
		}
	}

	public void setup(String fileName) {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		dataFile = new File(plugin.getDataFolder(), fileName + ".yml");

		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
				dataConfig = YamlConfiguration.loadConfiguration(dataFile);
				String successMessage = "&e[" + plugin.getName() + "] &aCreated the " + fileName + ".yml file";
				Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat(successMessage));
			} catch (IOException exe) {
				String failMessage = "&e[" + plugin.getName() + "] &cFailed to create the " + fileName + ".yml file";
				Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat(failMessage));
				exe.printStackTrace();
			}
		}

	}

	public boolean fileExists(String fileName) {
		dataFile = new File(plugin.getDataFolder(), fileName + ".yml");
		if (dataFile.exists()) {
			return true;
		}
		return false;
	}

	public void deleteFile(String fileName) {
		dataFile = new File(plugin.getDataFolder(), fileName + ".yml");
		dataFile.delete();
		return;
	}

	public FileConfiguration getDataConfig(String fileName) {
		dataFile = new File(plugin.getDataFolder(), fileName + ".yml");
		dataConfig = YamlConfiguration.loadConfiguration(dataFile);
		return dataConfig;
	}

	public File getDataFile(String fileName) {
		dataFile = new File(plugin.getDataFolder(), fileName + ".yml");
		return dataFile;
	}

	public void reloadCustomConfig(String fileName) {
		dataConfig = getDataConfig(fileName);
		// Look for defaults in the jar
		Reader defConfigStream;
		try {
			defConfigStream = new InputStreamReader(plugin.getResource(fileName + ".yml"), "UTF8");
		} catch (Exception exe) {
			exe.printStackTrace();
			return;
		}
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			dataConfig.setDefaults(defConfig);
		}
	}

	public void saveConfigFile(String fileName, FileConfiguration dataConfig) {
		dataFile = new File(plugin.getDataFolder(), fileName + ".yml");
		try {
			dataConfig.save(dataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void matchConfig(String fileName) {
		InputStream is = plugin.getResource(fileName + ".yml");
		FileConfiguration config = getDataConfig(fileName);
		if (is == null) {
			return;
		}
		YamlConfiguration jarConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(is));
		for (String key : jarConfig.getKeys(true))
			if (!config.contains(key)) {
				config.createSection(key);
				config.set(key, jarConfig.get(key));
			}
		for (String key : config.getConfigurationSection("").getKeys(true))
			if (!jarConfig.contains(key))
				config.set(key, null);
		config.set("version", plugin.getDescription().getVersion());
		saveConfigFile(fileName, config);

	}
}