package fr.draftman.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UHCSpeedRecipes implements Listener {
	
	@EventHandler
	public void changeCraft(PrepareItemCraftEvent e){
		
		//LE CRAFT SE PASSE BIEN DANS UNE TABLE DE CRAFT
		if(e.getInventory() instanceof CraftingInventory){
			
			//ON RECUPERE L INVENTAIRE SOUS FORME DUNE VARIABLE
			CraftingInventory inv = (CraftingInventory)e.getInventory();
			
			switch(inv.getResult().getType()){
			
			
			case WOOD_PICKAXE:
					//ON CREER NOTRE PIOCHE EN PIERRE
					ItemStack customResult = new ItemStack(Material.STONE_PICKAXE, 1);
					ItemMeta customM = customResult.getItemMeta();
					customM.setDisplayName("§eMa Pioche en Pierre Rapide");
					customM.addEnchant(Enchantment.DIG_SPEED, 2, true);
					customM.addEnchant(Enchantment.DURABILITY, 3, true);
					customResult.setItemMeta(customM);
					
					inv.setResult(customResult);
				
			break;
		
			
			
			default:
			break;
			
			}
			
		}
		
		
	}

}
