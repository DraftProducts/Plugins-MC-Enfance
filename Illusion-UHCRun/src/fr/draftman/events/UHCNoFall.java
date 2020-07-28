package fr.draftman.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.draftman.game.UHCState;

public class UHCNoFall implements Listener {

	@EventHandler
	public void damagebyFall(EntityDamageEvent e){
		
		//SI LES DEGATS SUBIT PROVIENNENT D UNE CHUTE ON LES ANNULE
		if(e.getCause() == DamageCause.FALL) {
			
			//SI LE PVP NEST PAS ON
			if(!UHCState.isState(UHCState.GAMEPVP)){
			e.setCancelled(true);
			}
			
		}
		
	}
	
}