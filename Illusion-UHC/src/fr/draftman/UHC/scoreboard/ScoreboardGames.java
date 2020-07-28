/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Statistic
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.scoreboard.DisplaySlot
 *  org.bukkit.scoreboard.Objective
 *  org.bukkit.scoreboard.Score
 *  org.bukkit.scoreboard.Scoreboard
 *  org.bukkit.scoreboard.ScoreboardManager
 */
package fr.draftman.UHC.scoreboard;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.draftman.UHC.timers.PvPTimer;

public class ScoreboardGames
implements Listener {
    public static void scoreBoardLoad(Player p) {
        ScoreboardManager sb = Bukkit.getScoreboardManager();
        Scoreboard board = sb.getNewScoreboard();
        Objective obj = board.registerNewObjective("Points", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("\u00a7EFind The Player II");
        String time = new SimpleDateFormat("mm:ss").format(new Date(PvPTimer.pvptimer * 1000));
        int playercount = Bukkit.getOnlinePlayers().size();
        Score score12 = obj.getScore("\u00a7e\u00a7m-                                  ");
        score12.setScore(12);
        Score score11 = obj.getScore("\u00a7a\u00a7n");
        score11.setScore(11);
        Score score10 = obj.getScore("\u00a76Joueurs: \u00a7a" + playercount + "\u00a77");
        score10.setScore(10);
        Score score9 = obj.getScore("\u00a72");
        score9.setScore(9);
        Score score8 = obj.getScore("\u00a76Kills: \u00a7e" + p.getStatistic(Statistic.PLAYER_KILLS));
        score8.setScore(8);
        Score score7 = obj.getScore("\u00a7l ");
        score7.setScore(7);
        Score score6 = obj.getScore("\u00a76PvP dans: \u00a7e" + time);
        score6.setScore(6);
        Score score5 = obj.getScore("\u00a78 ");
        score5.setScore(5);
        Score score3 = obj.getScore("\u00a75");
        score3.setScore(3);
        Score score2 = obj.getScore("\u00a76\u00c9tape: \u00a77GAMES");
        score2.setScore(2);
        Score score1 = obj.getScore("\u00a77 ");
        score1.setScore(1);
        Score score = obj.getScore("\u00a7e\u00a7m                                   ");
        score.setScore(0);
        p.setScoreboard(board);
    }
}

