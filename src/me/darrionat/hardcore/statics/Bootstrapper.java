package me.darrionat.hardcore.statics;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.repositories.DeadPlayerRepository;
import me.darrionat.hardcore.services.FileService;
import me.darrionat.hardcore.services.PlayerStatusService;

public class Bootstrapper {

	private static Bootstrapper instance;

	// Repositories
	private DeadPlayerRepository deadPlayerRepository;
	// Services
	private FileService fileService;
	private PlayerStatusService playerStatusService;

	private Bootstrapper() {
	}

	public void initialize(Hardcore plugin) {
		// Files setup first
		fileService = new FileService(plugin);
		// Repositories
		deadPlayerRepository = new DeadPlayerRepository(fileService);
		// Services
		playerStatusService = new PlayerStatusService(deadPlayerRepository);
	}

	public static Bootstrapper getBootstrapper() {
		if (instance == null)
			instance = new Bootstrapper();
		return instance;
	}

	public FileService getFileService() {
		return fileService;
	}

	public PlayerStatusService getPlayerStatusService() {
		return playerStatusService;
	}
}