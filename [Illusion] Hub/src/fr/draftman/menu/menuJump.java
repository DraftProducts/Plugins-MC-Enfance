package fr.draftman.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class menuJump {
	public static void menuJumpInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*1, "§8Jump");
		
		inv.clear();
		
		ItemStack JumpSpawn = new ItemStack(Material.INK_SACK, 1, (byte) 14);
		ItemMeta JumpSpawnM = JumpSpawn.getItemMeta();
		JumpSpawnM.setDisplayName("§6Jump Spawn");
		JumpSpawnM.setLore(Arrays.asList(" ",
				"§7Ce spawn se situe a coté du spawn !",
				"§e ",
				"§k!!!§2Clic gauche pour y aller !§k!!!",
				" "));
		JumpSpawn.setItemMeta(JumpSpawnM);
		
		ItemStack JumpCouloir = new ItemStack(Material.INK_SACK, 1, (byte) 14);
		ItemMeta JumpCouloirM = JumpCouloir.getItemMeta();
		JumpCouloirM.setDisplayName("§6Jump Couloir");
		JumpCouloirM.setLore(Arrays.asList(" ",
				"§4Bientôt !",
				"§e ",
				"§k!!!§2Clic gauche pour y aller !§k!!!",
				" "));
		JumpCouloir.setItemMeta(JumpCouloirM);
		
		
		
		inv.setItem(3, JumpSpawn);
		inv.setItem(5, JumpCouloir);
		
		p.openInventory(inv);
	}
}
