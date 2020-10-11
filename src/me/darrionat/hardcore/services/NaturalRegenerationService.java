package me.darrionat.hardcore.services;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;

import me.darrionat.hardcore.repositories.ConfigRepository;

public class NaturalRegenerationService {

	private ConfigRepository configRepository;

	public NaturalRegenerationService(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}

	public void disableNaturalRegeneration() {
		for (World w : Bukkit.getWorlds()) {
			if (configRepository.getNaturalRegenWorlds().contains(w.getName()))
				continue;
			GameRule<Boolean> rule = GameRule.NATURAL_REGENERATION;
			w.setGameRule(rule, false);
		}
	}
}
