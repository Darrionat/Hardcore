package me.darrionat.hardcore;

import org.bukkit.plugin.java.JavaPlugin;

import me.darrionat.hardcore.statics.Bootstrapper;
import me.darrionat.hardcore.utils.Utils;

public class Hardcore extends JavaPlugin {

	@Override
	public void onEnable() {
		Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		bootstrapper.initialize(this);

		bootstrapper.getNaturalRegenerationService().disableNaturalRegeneration();
	}

	@Override
	public void onDisable() {

	}

	public void log(String s) {
		System.out.println(Utils.chat("[" + getName() + "] " + s));
	}
}