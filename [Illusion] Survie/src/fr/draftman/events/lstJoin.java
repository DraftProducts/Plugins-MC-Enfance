package fr.draftman.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.draftman.GameState;
import fr.draftman.Illusion;

public class lstJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		String pn = e.getPlayer().getName();
		
		PlayerUtils.sendTitle(p, "§8Survie", "§3Serveur Mini-Jeux");
        PlayerUtils.sendHeaderAndFooter(p, "§6Illusion NetWork\n", "\n§4illusion.fr\nts.illusion.fr\nplay.illusion.fr");
        PlayerUtils.sendActionBar(p, "§9Bienvenu "+ pn +" sur Illusion");
		
		p.setGameMode(GameMode.SURVIVAL);
		
		
		if(Illusion.getInstance().isState(GameState.WAITING)){
			p.teleport(new Location(p.getWorld(), 244.5, 76, 241.5, 0, 0));
		}
		
	}	
	
	@EventHandler
	public void onJoin(PlayerDeathEvent e){
		
		Player p = e.getEntity();
		
		if(p instanceof Player){
			p.teleport(new Location(p.getWorld(), 244.5, 76, 241.5, 0, 0));
		}
        
	}	
	
}
