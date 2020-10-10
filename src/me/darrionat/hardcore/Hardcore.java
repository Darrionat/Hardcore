package me.darrionat.hardcore;

import org.bukkit.plugin.java.JavaPlugin;

import me.darrionat.hardcore.events.PlayerDeath;
import me.darrionat.hardcore.events.PlayerJoin;
import me.darrionat.hardcore.services.FileService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.statics.Bootstrapper;
import me.darrionat.hardcore.utils.Utils;

public class Hardcore extends JavaPlugin {

	public FileService fileService;
	public PlayerStatusService playerStatusService;

	@Override
	public void onEnable() {
		Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		bootstrapper.initialize(this);

		this.fileService = bootstrapper.getFileService();
		this.playerStatusService = bootstrapper.getPlayerStatusService();

		initializeListeners();

	}

	@Override
	public void onDisable() {

	}

	private void initializeListeners() {
		new PlayerDeath(this);
		new PlayerJoin(this);
	}

	public void log(String s) {
		System.out.println(Utils.chat("[" + getName() + "] " + s));
	}
}