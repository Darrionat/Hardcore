package me.darrionat.hardcore.statics;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.repositories.ConfigRepository;
import me.darrionat.hardcore.repositories.DeadPlayerRepository;
import me.darrionat.hardcore.repositories.FileRepository;
import me.darrionat.hardcore.repositories.PlayerStatsRepository;
import me.darrionat.hardcore.services.DeathService;
import me.darrionat.hardcore.services.NaturalRegenerationService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;
import me.darrionat.hardcore.services.StatsService;

public class Bootstrapper {

	private static Bootstrapper instance;

	// Repositories
	private DeadPlayerRepository deadPlayerRepository;
	private PlayerStatsRepository playerStatsRepository;
	private ConfigRepository configRepository;
	private FileRepository fileRepository;
	// Services
	private DeathService deathService;
	private PlayerStatusService playerStatusService;
	private NaturalRegenerationService naturalRegenerationService;
	private RevivalService revivalService;
	private StatsService statsService;

	private Bootstrapper() {
	}

	public void initialize(Hardcore plugin) {
		// Files setup first
		fileRepository = new FileRepository(plugin);
		// Repositories
		configRepository = new ConfigRepository(plugin);
		deadPlayerRepository = new DeadPlayerRepository(fileRepository);
		playerStatsRepository = new PlayerStatsRepository(fileRepository);
		// Services
		deathService = new DeathService(configRepository);
		naturalRegenerationService = new NaturalRegenerationService(configRepository);
		playerStatusService = new PlayerStatusService(deadPlayerRepository);
		revivalService = new RevivalService(deadPlayerRepository, playerStatsRepository);
		statsService = new StatsService(playerStatsRepository);
	}

	public static Bootstrapper getBootstrapper() {
		if (instance == null)
			instance = new Bootstrapper();
		return instance;
	}

	public DeathService getDeathService() {
		return deathService;
	}

	public PlayerStatusService getPlayerStatusService() {
		return playerStatusService;
	}

	public NaturalRegenerationService getNaturalRegenerationService() {
		return naturalRegenerationService;
	}

	public RevivalService getRevivalService() {
		return revivalService;
	}

	public StatsService getStatsService() {
		return statsService;
	}
}