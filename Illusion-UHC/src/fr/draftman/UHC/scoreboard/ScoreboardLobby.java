/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
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
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.draftman.UHC.event.PlayerJoin;

public class ScoreboardLobby
implements Listener {
    public static void scoreBoardLoad() {
        ScoreboardManager sb = Bukkit.getScoreboardManager();
        Scoreboard board = sb.getNewScoreboard();
        Objective obj = board.registerNewObjective("Points", "dummy");
        for (Player pls : Bukkit.getOnlinePlayers()) {
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName("\u00a7EUHC");
            String time = new SimpleDateFormat("mm:ss").format(new Date(PlayerJoin.timer * 1000));
            int playercount = Bukkit.getOnlinePlayers().size();
            Score score10 = obj.getScore("\u00a7e\u00a7m  -                               ");
            score10.setScore(10);
            Score score9 = obj.getScore("\u00a7a ");
            score9.setScore(9);
            Score score8 = obj.getScore("\u00a76Joueurs: \u00a7a" + playercount + "\u00a77/\u00a7a20");
            score8.setScore(8);
            Score score7 = obj.getScore("\u00a72");
            score7.setScore(7);
            Score score6 = obj.getScore("\u00a76Team: \u00a74Interdite");
            score6.setScore(6);
            Score score5 = obj.getScore("\u00a7l ");
            score5.setScore(5);
            Score score4 = obj.getScore("\u00a76D\u00e9but dans: \u00a7e" + time);
            score4.setScore(4);
            Score score3 = obj.getScore("\u00a78 ");
            score3.setScore(3);
            Score score2 = obj.getScore("\u00a76\u00c9tape: \u00a77Lobby");
            score2.setScore(2);
            Score score1 = obj.getScore("\u00a77 ");
            score1.setScore(1);
            Score score = obj.getScore("\u00a7e\u00a7m                                    ");
            score.setScore(0);
            pls.setScoreboard(board);
        }
    }
}

