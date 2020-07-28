package fr.draftman.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.draftman.GameState;
import fr.draftman.Illusion;

public class GameGenerate implements Listener {
	
	@EventHandler
	public void onPvp(EntityDamageByEntityEvent e){
		if(Illusion.getInstance().isState(GameState.WAITING)){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPlaceEvent(BlockPlaceEvent e){
		if(Illusion.getInstance().isState(GameState.WAITING)){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onBreakEvent(BlockBreakEvent e){
		if(Illusion.getInstance().isState(GameState.WAITING)){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onBreakEvent(FoodLevelChangeEvent e){
		if(Illusion.getInstance().isState(GameState.WAITING)){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onBreakEvent(PlayerInteractEvent e){
		if(Illusion.getInstance().isState(GameState.WAITING)){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
}
