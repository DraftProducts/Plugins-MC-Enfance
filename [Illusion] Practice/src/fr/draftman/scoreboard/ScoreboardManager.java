package fr.draftman.scoreboard;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
		scoreboardGame.get(player).setLine(3, "§7play.illusion.fr");

		
	}
}