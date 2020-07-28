package fr.draftman.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.draftman.game.UHCState;

public class UHCDamage implements Listener {

	@EventHandler
	public void damagebyFall(EntityDamageEvent e){
		
		//SI LE JEU EST AVANT LA PREPARATION // 30 PREMIERES SECONDES
		if(UHCState.isState(UHCState.WAIT) || UHCState.isState(UHCState.PREGAME)){
			//ON NERF LES DEGATS
			e.setCancelled(true);
		}
		
	}
	
}
