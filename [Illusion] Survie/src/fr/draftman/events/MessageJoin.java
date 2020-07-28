package fr.draftman.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MessageJoin implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.hasPermission("perm.message.join") || p.isOp()) {
		
			e.setJoinMessage("�7[�a+�7] "+p.getName()+" �fa rejoint la Survie �7[�a+�7]");
		} else {
			
			e.setJoinMessage(null);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.hasPermission("perm.message.quit") || p.isOp()) {
			
			e.setQuitMessage("�7[�c-�7] " + p.getName() +" �fa quitt� la Survie �7[�c-�7]");
		} else {
			
			e.setQuitMessage(null);
		}
	}

}
