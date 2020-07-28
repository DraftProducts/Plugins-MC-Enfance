package fr.draftman.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UHCSkullRegen implements Listener {
	
	//ON CREER LA METHODE POUR DONNER LE CRANE DE LA VICTIME AU KILLER
	public static void dropSkull(Player victim){
		
		//ON CREER LE CRANE EN FONCTION DE LAPPARENCE DE LA VICTIM
		ItemStack skullVictim = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		//ON RECUP LA META DU CRANE POUR LE MODIFIER
		SkullMeta skullM = (SkullMeta) skullVictim.getItemMeta();
		skullM.setOwner(victim.getName());
		skullVictim.setItemMeta(skullM);
		
		//ON DROP LE CRANE A LENDROIT OU LE JOUEUR VICTIM VA MOURRIR
		victim.getWorld().dropItemNaturally(victim.getLocation(), skullVictim);
		
	}
	
	@EventHandler
	public void skullClick(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		Material mat = e.getMaterial();
		
		//ON VERIFIE SI LE JOUEUR CLICK BIEN AVEC L ITEM SUR LE SOL
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			//SI LITEM AVEC LEQUEL IL A CLIQUER NEST PAS NULL
			if(mat != null){
				
				//ON VERIFIE QUE CEST UN CRANE
				if(mat == Material.SKULL_ITEM){
					//ON SUPPRIME LE CRANE DE LINV DU JOUEUR
					p.getInventory().remove(e.getItem());
					//ON UPDATE LINV
					p.updateInventory();
					//LE JOUEUR GAGNERA EN ECHANGE DU CRANE UN BOOST DE REGEN
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
				}
				
			}
			
		}
		
	}

}
