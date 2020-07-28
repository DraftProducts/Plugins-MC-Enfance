package fr.draftman.shop;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.draftman.Utils.ChatUtils;


public class Menu  implements Listener {
	
	@EventHandler
	public static void onClickItem(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(e.getMaterial() == null) {
				
				return;
			}
		
			if(e.getItem().getType() == Material.GOLD_NUGGET){
				
				Inventory shop = Bukkit.createInventory(null, 9*1, "Shop");
				
				ItemStack Bombe_Atomic = new ItemStack(Material.DRAGON_EGG);
				ItemMeta Bombe_AtomicM = Bombe_Atomic.getItemMeta();
				Bombe_AtomicM.setDisplayName("§6Bombe Atomic §7(§415 $§7)");
				Bombe_AtomicM.setLore(Arrays.asList(" ",
						"§7Cette Bombe Atomic fera des dégats",
						"§7considérables et laissera un effet",
						"§7de poison pendant 15 secondes !",
						"§e ",
						"§k!!!§2Clic gauche pour l'acheter !§k!!!",
						" "));
				Bombe_Atomic.setItemMeta(Bombe_AtomicM);
				shop.setItem(0, Bombe_Atomic);
				
				ItemStack Bombe = new ItemStack(Material.SLIME_BALL);
				ItemMeta BombeM = Bombe.getItemMeta();
				BombeM.setDisplayName("§6Bombe §7(§45 $§7)");
				BombeM.setLore(Arrays.asList(" ",
						"§7Une petite bombe qui aura une petite",
						"§7déflagration sur les autres joueurs ",
						"§7de poison pendant 5 secondes !",
						"§e ",
						"§k!!!§2Clic gauche pour l'acheter !§k!!!",
						" "));
				Bombe.setItemMeta(BombeM);
				shop.setItem(1, Bombe);
				
				ItemStack Fleches = new ItemStack(Material.ARROW);
				ItemMeta FlechesM = Fleches.getItemMeta();
				FlechesM.setDisplayName("§6Flèches §7(§42 $§7)");
				FlechesM.setLore(Arrays.asList(" ",
						"§7Des fleches pour continuer a tirer !",
						"§e ",
						"§k!!!§2Clic gauche pour l'acheter !§k!!!",
						" "));
				Fleches.setItemMeta(FlechesM);
				shop.setItem(2, Fleches);

				p.openInventory(shop);
			}
		}
	}
	
	@EventHandler
	public void onInventoryClickItem(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getTitle().equalsIgnoreCase("Shop")) {	
			
			if(e.getCurrentItem().getType() == Material.DRAGON_EGG) {
				if(p.getInventory().contains(Material.GOLD_NUGGET, 15)){
				
					ItemStack Bombe_Atomic = new ItemStack(Material.DRAGON_EGG);
					ItemMeta Bombe_AtomicM = Bombe_Atomic.getItemMeta();
					Bombe_AtomicM.setDisplayName("§6Bombe Atomic");
					Bombe_AtomicM.setLore(Arrays.asList(" ",
							"§7Cette Bombe Atomic fera des dégats",
							"§7considérables et laissera un effet",
							"§7de poison pendant 15 segondes !",
							" "));
					Bombe_Atomic.setItemMeta(Bombe_AtomicM);
					
					p.getInventory().addItem(Bombe_Atomic);
					for(int i = 0; i < p.getInventory().getContents().length; i++) {
			            ItemStack item = p.getInventory().getContents()[i];
			            if(item == null) 
			            	continue;
			            if(item.getType() != Material.GOLD_NUGGET)
			                continue;
			            if(item.getAmount() < 15)
			                continue;

			            item.setAmount(item.getAmount() - 15);
			            p.getInventory().setItem(i, item);
			            break;
			        }
					
					e.setCancelled(true);
				}else{
					p.sendMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + ChatColor.RED +" Tu n'as pas assez d'argent pour acheter ça !"));
					p.closeInventory();
				}
			}
			
			if(e.getCurrentItem().getType() == Material.SLIME_BALL) {
				if(p.getInventory().contains(Material.GOLD_NUGGET, 5)){
				
					ItemStack Bombe = new ItemStack(Material.SLIME_BALL);
					ItemMeta BombeM = Bombe.getItemMeta();
					BombeM.setDisplayName("§6Bombe");
					BombeM.setLore(Arrays.asList(" ",
							"§7Une ^petite bombe qui aura une petite",
							"§7déflagration sur les autres joueurs ",
							"§7de poison pendant 5 segondes !",
							" "));
					Bombe.setItemMeta(BombeM);
					
					p.getInventory().addItem(Bombe);
					for(int i = 0; i < p.getInventory().getContents().length; i++) {
			            ItemStack item = p.getInventory().getContents()[i];
			            if(item == null) 
			            	continue;
			            if(item.getType() != Material.GOLD_NUGGET)
			                continue;
			            if(item.getAmount() < 5)
			                continue;

			            item.setAmount(item.getAmount() - 5);
			            p.getInventory().setItem(i, item);
			            break;
			        }
					
					e.setCancelled(true);
				}else{
					p.sendMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + ChatColor.RED +" Tu n'as pas assez d'argent pour acheter ça !"));
					p.closeInventory();
				}
			}
			
			if(e.getCurrentItem().getType() == Material.ARROW) {
				if(p.getInventory().contains(Material.GOLD_NUGGET, 2)){
							
					ItemStack Fleches = new ItemStack(Material.ARROW, 3, (byte) 1);
					ItemMeta FlechesM = Fleches.getItemMeta();
					FlechesM.setDisplayName("§6Flèches");
					FlechesM.setLore(Arrays.asList(" ",
							"§7Des fleches pour continuer a tirer !",
							" "));
					Fleches.setItemMeta(FlechesM);
							
					p.getInventory().addItem(Fleches);
					for(int i = 0; i < p.getInventory().getContents().length; i++) {
			            ItemStack item = p.getInventory().getContents()[i];
			            if(item == null) 
			            	continue;
			            if(item.getType() != Material.GOLD_NUGGET)
			                continue;
			            if(item.getAmount() < 2)
			                continue;

			            item.setAmount(item.getAmount() - 2);
			            p.getInventory().setItem(i, item);
			            break;
			        }
					
					e.setCancelled(true);
				}else{
					p.sendMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + ChatColor.RED +" Tu n'as pas assez d'argent pour acheter ça !"));
					p.closeInventory();
				}
			}
		}
		e.setCancelled(true);
	}

}





























