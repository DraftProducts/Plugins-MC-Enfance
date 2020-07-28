package fr.draftman.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
	@EventHandler
	public static void onClickItem(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(e.getMaterial() == null) {
				
				return;
			}
			
			Material mat = e.getMaterial();
		
			if(mat == Material.CHEST && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Kits PVP")) {
				
				menuKitInventory(p);
				e.setCancelled(true);
			}
		}
	}
	public static void menuKitInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*5, "§8Kit PVP");
		
		inv.clear();
		
		ItemStack Kit1 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta Kit1M = Kit1.getItemMeta();
		Kit1M.setDisplayName("§6KitPVP");
		Kit1M.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires avec le Kit de base",
				"§e ",
				"§k!!!§2Clic gauche prendre ce kit !§k!!!",
				" "));
		Kit1.setItemMeta(Kit1M);
		
		ItemStack Kit2 = new ItemStack(Material.IRON_SWORD);
		ItemMeta Kit2M = Kit2.getItemMeta();
		Kit2M.setDisplayName("§6Kit Guépard");
		Kit2M.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires en ayant plus de vitesse",
				"§e ",
				"§k!!!§2Clic gauche prendre ce kit !§k!!!",
				" "));
		Kit2.setItemMeta(Kit2M);
		
		ItemStack Kit3 = new ItemStack(Material.STONE_SWORD);
		ItemMeta Kit3M = Kit3.getItemMeta();
		Kit3M.setDisplayName("§6Kit Tortue");
		Kit3M.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires avec le Kit de base",
				"§e ",
				"§k!!!§2Clic gauche prendre ce kit !§k!!!",
				" "));
		Kit3.setItemMeta(Kit3M);
		
		ItemStack Kit4 = new ItemStack(Material.IRON_SWORD);
		ItemMeta Kit4M = Kit4.getItemMeta();
		Kit4M.setDisplayName("§6Kit Kangourou");
		Kit4M.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires avec le Kit de base",
				"§e ",
				"§k!!!§2Clic gauche prendre ce kit !§k!!!",
				" "));
		Kit3.setItemMeta(Kit3M);
		
		inv.setItem(0, Kit1);
		inv.setItem(1, Kit2);
		inv.setItem(1, Kit2);
		
		p.openInventory(inv);
	}
}
