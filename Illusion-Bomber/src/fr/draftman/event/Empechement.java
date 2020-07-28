package fr.draftman.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Empechement implements Listener {
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		
		e.setCancelled(true);

	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {

		e.setCancelled(true);

	}
	
}
