package fr.draftman.util;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class UHCBlockDrops implements Listener {
	
	@EventHandler
	public void breakBlock(BlockBreakEvent e){
		
		//CREER UNE VARIABLE LOCATION
		Location breakLoc = e.getBlock().getLocation();
		
		switch(e.getBlock().getType()){
		
		case IRON_ORE:
		e.setCancelled(true);
		e.getBlock().setType(Material.AIR);
		//CA VA DROP 2 LINGOTS DE FER
		breakLoc.getWorld().dropItemNaturally(breakLoc, new ItemStack(Material.IRON_INGOT, 2));
		break;
		
		case GRAVEL:
		e.setCancelled(true);
		e.getBlock().setType(Material.AIR);
		//CA VA DROP 2 FLECHES
		breakLoc.getWorld().dropItemNaturally(breakLoc, new ItemStack(Material.ARROW, 2));
		break;
		
		
		
		default:
		break;
		
		}
	}

}
