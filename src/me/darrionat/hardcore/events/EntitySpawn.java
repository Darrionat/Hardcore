package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;

public class EntitySpawn implements Listener {

	private DeathWorldService deathWorldService;

	public EntitySpawn(Hardcore plugin, DeathWorldService deathWorldService) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.deathWorldService = deathWorldService;
	}

	@EventHandler
	public void onDeath(EntitySpawnEvent e) {
		if (!(e.getEntity() instanceof LivingEntity))
			return;

		if (e.getEntity().getWorld().equals(deathWorldService.getWorld()))
			e.setCancelled(true);
	}
}