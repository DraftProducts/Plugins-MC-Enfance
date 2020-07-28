package fr.draftman.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.draftman.Bomber;

public class GoldRunnable {

	public void start(){
		Bukkit.getScheduler().runTaskTimer(Bomber.getInstance(), () -> {
	        for(Player p : Bukkit.getOnlinePlayers()) {
	        	
	        	ItemStack shop = new ItemStack(Material.GOLD_NUGGET);
	    		ItemMeta shopM = shop.getItemMeta();
	    		shopM.setDisplayName("§8Shop");
	    		shop.setItemMeta(shopM);
	    		p.getInventory().addItem(shop);
	        }
	    }, 0, 30 * 10);
	}
}
