package fr.draftman.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardGames implements Listener {
    public static void scoreBoardLoad(Player p) {
        ScoreboardManager sb = Bukkit.getScoreboardManager();
        Scoreboard board = sb.getNewScoreboard();
        Objective obj = board.registerNewObjective("Points", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§8Illusion");
        Score score7 = obj.getScore("§a ");
        score7.setScore(7);
        Score score6 = obj.getScore("§5Joueurs :" + ChatColor.AQUA +Bukkit.getOnlinePlayers().size());
        score6.setScore(6);
        Score score5 = obj.getScore("§3 ");
        score5.setScore(5);
        Score score4 = obj.getScore("§2Kills:" + ChatColor.LIGHT_PURPLE +p.getStatistic(Statistic.PLAYER_KILLS));
        score4.setScore(4);
        Score score3 = obj.getScore("§c ");
        score3.setScore(3);
        Score score2 = obj.getScore("§4Team: Interdite");
        score2.setScore(2);
        Score score1 = obj.getScore("§9 ");
        score1.setScore(1);
        Score score = obj.getScore("§8play.illusion.fr");
        score.setScore(0);
        p.setScoreboard(board);
    }
}