package fr.draftman.scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScoreboardManager implements Listener {

public Map<Player, ScoreboardSign> boards = new HashMap<>();

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
	
		Player p = event.getPlayer();
		
		String pn = event.getPlayer().getName();
	
		for(Entry<Player, ScoreboardSign> sign : boards.entrySet()){
			sign.getValue().setLine(1, "" + Bukkit.getOnlinePlayers().size());
		}
	
		ScoreboardSign scoreboard = new ScoreboardSign(p, "§6Illusion");
		scoreboard.create();
		scoreboard.setLine(0, "§a ");
		scoreboard.setLine(1, "§2Connectés :" + Bukkit.getOnlinePlayers().size());
		scoreboard.setLine(2, "§e ");
		scoreboard.setLine(3, " ► " + pn);
		scoreboard.setLine(4, "Illucoins : 500");
		scoreboard.setLine(5, "Points : 0");
		scoreboard.setLine(6, "§f ");
		scoreboard.setLine(7, "§eplay.illusion.fr");
		scoreboard.setLine(8, "§ets.illusion.fr");
		boards.put(p, scoreboard);
	
	
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player p = event.getPlayer();
	
		for(Entry<Player, ScoreboardSign> sign : boards.entrySet()){
			sign.getValue().setLine(4, "" + (Bukkit.getOnlinePlayers().size() - 1));
		}
	
		if(boards.containsKey(p)){
			boards.get(p).destroy();
		}
	
	}

}
