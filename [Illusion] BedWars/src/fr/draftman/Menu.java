package fr.draftman;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements Listener {
	
	@EventHandler
	public static void onInventoryClicItem(InventoryClickEvent e){
		
		Material mat = e.getCurrentItem().getType();
		Player p = (Player) e.getWhoClicked();
		
		if(mat == Material.IRON_SWORD && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Combat")){
			menuCombat(p);
			e.setCancelled(true);
		}
		
		if(mat == Material.SANDSTONE && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Blocks")){
			p.closeInventory();
		}
		
		if(mat == Material.IRON_CHESTPLATE && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Armure")){
			p.closeInventory();
		}
		
		if(mat == Material.IRON_PICKAXE && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Outils")){
			p.closeInventory();
		}
		
		if(mat == Material.NETHER_STAR && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Spécials")){
			p.closeInventory();
		}
	}

	public static void menuItems(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, "§8Items");
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordM = sword.getItemMeta();
		swordM.setDisplayName("§6Combat");
		swordM.setLore(Arrays.asList(" ",
				" §7- Stone Sword",
				" §7- Iron Sword",
				" §7- Diamond Sword",
				"§f ",
				" §7- Arc",
				" §7- Arc (Puissance I)",
				" §7- Flèches",
				"§e ",
				"§k!!§2Clic pour ouvrir !§k!!",
				" "));
		sword.setItemMeta(swordM);
		
		ItemStack blocks = new ItemStack(Material.SANDSTONE,1,(byte)2);
		ItemMeta blocksM = sword.getItemMeta();
		blocksM.setDisplayName("§6Blocks");
		blocksM.setLore(Arrays.asList(" ",
				" §7- Verre",
				" §7- Laine",
				" §7- Bois",
				" §7- Echelle",
				" §7- Grès Poli",
				" §7- Obsidienne",
				" §7- Saut d'eau",
				"§e ",
				"§k!!§2Clic pour ouvrir !§k!!",
				" "));
		blocks.setItemMeta(blocksM);
		
		ItemStack armure = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta armureM = sword.getItemMeta();
		armureM.setDisplayName("§6Armure");
		armureM.setLore(Arrays.asList(" ",
				" §7- Armure Or",
				" §7- Armure Fer",
				" §7- Armure Diamant",
				"§e ",
				"§k!!§2Clic pour ouvrir !§k!!",
				" "));
		armure.setItemMeta(armureM);
		
		ItemStack tool = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta toolM = tool.getItemMeta();
		toolM.setDisplayName("§6Outils");
		toolM.setLore(Arrays.asList(" ",
				" §7- Cisailles",
				" §7- Pioche bois",
				" §7- Pioche fer",
				" §7- Pioche diamant",
				" §7- Hach diamant",
				"§e ",
				"§k!!§2Clic pour ouvrir !§k!!",
				" "));
		tool.setItemMeta(toolM);
		
		ItemStack space = new ItemStack(Material.NETHER_STAR);
		ItemMeta spaceM = space.getItemMeta();
		spaceM.setDisplayName("§6Spécials");
		spaceM.setLore(Arrays.asList(" ",
				" §7- TNT",
				" §7- Boutes de Feu",
				" §7- Boules de neige",
				" §7- Golem de Fer",
				" §7- Golem de Neige",
				"§e ",
				"§k!!§2Clic pour ouvrir !§k!!",
				" "));
		space.setItemMeta(spaceM);
		
		inv.setItem(10, sword);
		inv.setItem(11, blocks);
		inv.setItem(12, armure);
		inv.setItem(13, tool);
		inv.setItem(14, space);
	}
	
	public static void menuCombat(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, "§8Combat");
		
		ItemStack stonesword = new ItemStack(Material.STONE_SWORD);
		ItemMeta stoneswordM = stonesword.getItemMeta();
		stoneswordM.setDisplayName("§6Stone Sword");
		stonesword.setItemMeta(stoneswordM);
		
		ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
		ItemMeta ironswordM = ironsword.getItemMeta();
		ironswordM.setDisplayName("§6Iron Sword");
		ironsword.setItemMeta(ironswordM);
		
		ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta diamondswordM = diamondsword.getItemMeta();
		diamondswordM.setDisplayName("§6Diamond Sword");
		diamondsword.setItemMeta(diamondswordM);
		
		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta bowM = bow.getItemMeta();
		bowM.setDisplayName("§6Arc");
		bow.setItemMeta(bowM);
		
		ItemStack bowp = new ItemStack(Material.BOW);
		ItemMeta bowpM = bowp.getItemMeta();
		((ItemStack) bowpM).addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		bowpM.setDisplayName("§6Arc (Power I)");
		bowp.setItemMeta(bowpM);
		
		inv.setItem(10, stonesword);
		inv.setItem(11, ironsword);
		inv.setItem(12, diamondsword);
		inv.setItem(13, bow);
		inv.setItem(14, bowp);
	}
}
