package fr.draftman.scoreboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.draftman.HTask;

public class ScoreboardManager {
	
	public Player player;
	public ScoreboardSign scoreboardSign;
	
	public static Map<Player, ScoreboardSign> scoreboardGame = new HashMap<Player, ScoreboardSign>();
	
	public ScoreboardManager(Player player) {
		this.player = player;
		this.scoreboardSign = new ScoreboardSign(player, player.getName());
		scoreboardGame.put(player, scoreboardSign);
	}
	
	public void loadScoreboard() {
		this.scoreboardSign.setObjectiveName("§3§lIllusion§r§f§o.fr");
		this.scoreboardSign.create();
		scoreboardGame.get(player).setLine(0, "§e ");
		scoreboardGame.get(player).setLine(1, "§7Joueurs : §a" +Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers());
		scoreboardGame.get(player).setLine(2, "§a ");
		scoreboardGame.get(player).setLine(3, ChatColor.GRAY + "Début dans: " + ChatColor.GREEN + new SimpleDateFormat("ss").format(new Date(HTask.timer * 1000)));
		scoreboardGame.get(player).setLine(4, "§d ");
		scoreboardGame.get(player).setLine(5, "§7play.illusion.fr");
		
	}
	
	public static void loadScoreboardGame(Player pls) {
		if(ScoreboardManager.scoreboardGame.containsKey(pls)) {
			ScoreboardManager.scoreboardGame.get(pls).setLine(1, "Points :");
			ScoreboardManager.scoreboardGame.get(pls).setLine(2, " §6Orange : 0");
			ScoreboardManager.scoreboardGame.get(pls).setLine(3, " §aVert : 0");
			
		}
	}
}