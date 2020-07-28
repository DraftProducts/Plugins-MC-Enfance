package fr.draftman.event;

import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class soup {
	
	public void OnSoup(PlayerInteractEvent e){
		Player p = e.getPlayer();
		double vie =((Damageable) p).getHealth();
		        if(e.getAction() == Action.RIGHT_CLICK_AIR ||e.getAction() == Action.RIGHT_CLICK_BLOCK){
		                if(e.getMaterial() == Material.MUSHROOM_SOUP){
		                        if(vie<=13){
		                        p.setHealth(vie+7);    
		                        }
		                        else{
		                        p.setHealth(20);       
		                        }
		                        if(vie!=20){
		                        p.getItemInHand().setType(Material.BOWL);
		                        }
		                }
		        }
		}

}