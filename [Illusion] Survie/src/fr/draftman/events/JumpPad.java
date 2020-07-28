package fr.draftman.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class JumpPad implements Listener {

	@EventHandler
		public void onPlayerMove(PlayerMoveEvent e) {
		  
		Player p = e.getPlayer();
		  
		if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.GOLD_PLATE) {
		   
		Location loc = e.getTo().getBlock().getRelative(BlockFace.SELF).getLocation();
		Material b = loc.subtract(0, 1, 0).getBlock().getType();
		   
			if(b == Material.SANDSTONE) {
		    
		    p.setVelocity(p.getLocation().getDirection().multiply(2).setY(1));
			}
		}
	}
}
