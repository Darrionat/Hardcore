package me.darrionat.hardcore.services;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;

import me.darrionat.hardcore.Hardcore;

public class NaturalRegenerationService {

	private Hardcore plugin;

	public NaturalRegenerationService(Hardcore plugin) {
		this.plugin = plugin;
	}

	public void disableNaturalRegeneration() {
		for (World w : Bukkit.getWorlds()) {
			if (plugin.getConfig().getStringList("allowNaturalRegenerationWorlds").contains(w.getName()))
				continue;
			GameRule<Boolean> rule = GameRule.NATURAL_REGENERATION;
			w.setGameRule(rule, false);
		}
	}
}
