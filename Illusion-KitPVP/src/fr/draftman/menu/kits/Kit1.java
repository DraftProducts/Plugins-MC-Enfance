package fr.draftman.menu.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit1 {
	public static void Kitn1(Player p) {
		
		ItemStack epee = new ItemStack(Material.IRON_SWORD);
		ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 35, (byte) 1);
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setBoots(boots);
		p.getInventory().setItem(0, epee);
		p.getInventory().addItem(soup);
	}
}