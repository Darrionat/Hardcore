package me.darrionat.hardcore;

import org.bukkit.plugin.java.JavaPlugin;

import me.darrionat.hardcore.events.PlayerDeath;
import me.darrionat.hardcore.events.PlayerJoin;
import me.darrionat.hardcore.events.PlayerRespawn;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.MessageService;
import me.darrionat.hardcore.services.NaturalRegenerationService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;
import me.darrionat.hardcore.services.StatsService;
import me.darrionat.hardcore.statics.Bootstrapper;
import me.darrionat.hardcore.utils.Utils;

public class Hardcore extends JavaPlugin {

	public DeathWorldService deathWorldService;
	public PlayerStatusService playerStatusService;
	public NaturalRegenerationService naturalRegenerationService;
	public RevivalService revivalService;
	public StatsService statsService;
	public MessageService messageService;

	@Override
	public void onEnable() {
		Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		bootstrapper.initialize(this);

		this.deathWorldService = bootstrapper.getDeathService();
		this.playerStatusService = bootstrapper.getPlayerStatusService();
		this.naturalRegenerationService = bootstrapper.getNaturalRegenerationService();
		this.revivalService = bootstrapper.getRevivalService();
		this.statsService = bootstrapper.getStatsService();
		this.messageService = bootstrapper.getMessageService();

		naturalRegenerationService.disableNaturalRegeneration();
		initializeListeners();
	}

	@Override
	public void onDisable() {

	}

	private void initializeListeners() {
		new PlayerDeath(this);
		new PlayerJoin(this);
		new PlayerRespawn(this);
	}

	public void log(String s) {
		System.out.println(Utils.chat("[" + getName() + "] " + s));
	}
}