package fr.draftman.scoreboards;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardRunnable extends BukkitRunnable{

	@Override
	public void run() {
		
		for(Entry<Player, CustomScoreboardManager> scoreboard : CustomScoreboardManager.sb.entrySet()){
			CustomScoreboardManager board = scoreboard.getValue();
			board.refresh();
		}
		
	}

	public static void sendLine() {
		
		for(Entry<Player, CustomScoreboardManager> scoreboard : CustomScoreboardManager.sb.entrySet()){
			CustomScoreboardManager board = scoreboard.getValue();
			board.getLine();
		}
		
		
	}

}