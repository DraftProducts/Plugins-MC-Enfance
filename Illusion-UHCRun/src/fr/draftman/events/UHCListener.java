package fr.draftman.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UHCListener implements Listener {
	
	@EventHandler
	public void regainHealth(EntityRegainHealthEvent e){
		//SI LENTITE QUI VIEN DE REGEN EST UN JOUEUR
		if(e.getEntity() instanceof Player){
			//ON VERIFIE SI LA CAUSE DE CETTE REGEN EST NATURELLE
			if(e.getRegainReason() == RegainReason.EATING){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void eat(PlayerItemConsumeEvent e){
		if(e.getItem().getType().equals(Material.GOLDEN_APPLE)){
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
		}else{
			return;
		}
	}
	
    @EventHandler
    public void portal(PortalCreateEvent e){
    	e.setCancelled(true);
    }

}
