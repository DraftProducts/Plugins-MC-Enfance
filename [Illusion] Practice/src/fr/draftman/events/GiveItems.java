package fr.draftman.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveItems {
	
	public static void ItemsSpawn(Player p) {
		
		Inventory inv = p.getInventory();
		
		inv.clear();
		
		ItemStack ranked = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta rankedM = ranked.getItemMeta();
		rankedM.setDisplayName("§6Ranked");
		ranked.setItemMeta(rankedM);
		
		ItemStack unranked = new ItemStack(Material.IRON_SWORD);
		ItemMeta unrankedM = unranked.getItemMeta();
		unrankedM.setDisplayName("§6Unranked");
		unranked.setItemMeta(unrankedM);
		
		ItemStack duel = new ItemStack(Material.BLAZE_ROD);
		ItemMeta duelM = duel.getItemMeta();
		duelM.setDisplayName("§6Duel");
		duel.setItemMeta(duelM);
		
		ItemStack edit = new ItemStack(Material.NAME_TAG);
		ItemMeta editM = edit.getItemMeta();
		editM.setDisplayName("§6Kits Edit");
		edit.setItemMeta(editM);
		
		inv.setItem(0, ranked);
		inv.setItem(1, unranked);
		inv.setItem(2, duel);
		inv.setItem(4, edit);
		p.updateInventory();
	}
	
	public static void ItemsQueue(Player p){
		
		Inventory inv = p.getInventory();
		
		ItemStack info = new ItemStack(Material.PAPER);
		ItemMeta infoM = info.getItemMeta();
		if(QueueManager.unrankedBuildUHC.contains(p)){
			infoM.setDisplayName("§6Queue : Unranked BuildUHC");
		}
		info.setItemMeta(infoM);
		
		ItemStack leave = new ItemStack(Material.BARRIER);
		ItemMeta leaveM = leave.getItemMeta();
		if(QueueManager.unrankedBuildUHC.contains(p)){
			leaveM.setDisplayName("§cQuitter §6Unranked BuildUHC");
		}
		leave.setItemMeta(leaveM);
		inv.clear();
		inv.setItem(4, info);
		inv.setItem(8, leave);
	}
}

