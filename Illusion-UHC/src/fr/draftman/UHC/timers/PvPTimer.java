/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package fr.draftman.UHC.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.draftman.UHC.Utils.ChatUtils;
import fr.draftman.UHC.scoreboard.ScoreboardGames;

public class PvPTimer {
    static int task;
    public static int pvptimer;

    static {
        pvptimer = 901;
    }

    public static void pvptimer() {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UhcGames"), new Runnable(){

            @Override
            public void run() {
                if (--PvPTimer.pvptimer <= 900 && PvPTimer.pvptimer != 0) {
                    for (Player pls : Bukkit.getOnlinePlayers()) {
                        ScoreboardGames.scoreBoardLoad(pls);
                    }
                }
                if (PvPTimer.pvptimer == 600) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e10 minutes"));
                }
                if (PvPTimer.pvptimer == 300) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e5 minutes"));
                }
                if (PvPTimer.pvptimer == 240) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e4 minutes"));
                }
                if (PvPTimer.pvptimer == 180) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e3 minutes"));
                }
                if (PvPTimer.pvptimer == 120) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e2 minutes"));
                }
                if (PvPTimer.pvptimer == 60) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e1 minute"));
                }
                if (PvPTimer.pvptimer == 30 || PvPTimer.pvptimer == 15 || PvPTimer.pvptimer == 10 || PvPTimer.pvptimer == 9 || PvPTimer.pvptimer == 8 || PvPTimer.pvptimer == 7 || PvPTimer.pvptimer == 6 || PvPTimer.pvptimer == 5 || PvPTimer.pvptimer == 4 || PvPTimer.pvptimer == 3 || PvPTimer.pvptimer == 2) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e" + PvPTimer.pvptimer + " seconde"));
                }
                if (PvPTimer.pvptimer == 1) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active dans \u00a7e" + PvPTimer.pvptimer + " seconde"));
                }
                if (PvPTimer.pvptimer == 0) {
                    Bukkit.getScheduler().cancelTask(PvPTimer.task);
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76Le PvP s'active"));
                    Bukkit.getWorld((String)"world").setPVP(true);
                }
            }
        }, 20, 20);
    }

}

