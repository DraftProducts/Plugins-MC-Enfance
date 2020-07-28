package fr.draftman.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Stacker implements Listener {
	
	@EventHandler
	public void onStack(PlayerInteractEntityEvent e){
		
		if(e.getPlayer().hasPermission("perm.stack") || e.getPlayer().isOp()) {
		    if(!(e.getRightClicked() instanceof Player))
		    return;
		    e.getPlayer().setPassenger(e.getRightClicked());
		}
	}
}