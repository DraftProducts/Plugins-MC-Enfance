package fr.draftman.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.draftman.event.PlayerJoin;

public class ScoreboardLobby implements Listener {
    public static void scoreBoardLoad() {
        ScoreboardManager sb = Bukkit.getScoreboardManager();
        Scoreboard board = sb.getNewScoreboard();
        Objective obj = board.registerNewObjective("Points", "dummy");
        for (Player pls : Bukkit.getOnlinePlayers()) {
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName("§8Illusion");
            Score score9 = obj.getScore("§e ");
            score9.setScore(9);
            Score score8 = obj.getScore("§5Joueurs: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers());
            score8.setScore(8);
            Score score7 = obj.getScore("§9 ");
            score7.setScore(7);
            Score score6 = obj.getScore("§4Team: Interdite");
            score6.setScore(6);
            Score score5 = obj.getScore("§c ");
            score5.setScore(5);
            Score score4 = obj.getScore("Lancement dans : " + PlayerJoin.timer);
            score4.setScore(4);
            Score score3 = obj.getScore("§a ");
            score3.setScore(3);
            Score score2 = obj.getScore("tape /hub pour quitter");
            score2.setScore(2);
            Score score1 = obj.getScore("§f ");
            score1.setScore(1);
            Score score = obj.getScore("§8play.illusion.fr");
            score.setScore(0);
            pls.setScoreboard(board);
        }
    }
}

