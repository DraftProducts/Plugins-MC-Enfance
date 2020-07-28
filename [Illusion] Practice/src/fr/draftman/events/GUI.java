package fr.draftman.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {
	public static void UnrankedGUI(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§8Unranked");
		
		ItemStack BuildUHC = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta BuildUHCM = BuildUHC.getItemMeta();
		BuildUHCM.setDisplayName("§6BuildUHC");
		BuildUHC.setItemMeta(BuildUHCM);
		
		inv.setItem(0, BuildUHC);
		
		p.openInventory(inv);
	}
	
	public static void DuelGUI(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§8Duel");
		
		ItemStack BuildUHC = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta BuildUHCM = BuildUHC.getItemMeta();
		BuildUHCM.setDisplayName("§6BuildUHC");
		BuildUHC.setItemMeta(BuildUHCM);
		
		inv.setItem(0, BuildUHC);
		
		p.openInventory(inv);
	}
}
