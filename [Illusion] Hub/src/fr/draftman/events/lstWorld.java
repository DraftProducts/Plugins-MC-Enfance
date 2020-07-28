package fr.draftman.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class lstWorld implements Listener {
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onMort(PlayerDeathEvent e){
		
		e.setDeathMessage(null);
	}
	
	@EventHandler
	public void cancelFoodChange(FoodLevelChangeEvent e){
		
        e.setCancelled(true);
    }
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		e.setCancelled(true);
	}



}
