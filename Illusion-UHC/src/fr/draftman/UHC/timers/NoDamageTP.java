/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package fr.draftman.UHC.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.draftman.UHC.Utils.ChatUtils;

public class NoDamageTP {
    static int task;
    static int nodamagetimer;

    static {
        nodamagetimer = 61;
    }

    public static void nodamagetp() {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 120));
            Bukkit.getWorld((String)"world").setPVP(false);
        }
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UhcGames"), new Runnable(){

            @Override
            public void run() {
                if (--NoDamageTP.nodamagetimer == 60) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76PvP s'active dans \u00a7e1 minute !"));
                }
                if (NoDamageTP.nodamagetimer == 30 || NoDamageTP.nodamagetimer == 15 || NoDamageTP.nodamagetimer == 10 || NoDamageTP.nodamagetimer == 9 || NoDamageTP.nodamagetimer == 8 || NoDamageTP.nodamagetimer == 7 || NoDamageTP.nodamagetimer == 6 || NoDamageTP.nodamagetimer == 5 || NoDamageTP.nodamagetimer == 4 || NoDamageTP.nodamagetimer == 3 || NoDamageTP.nodamagetimer == 2) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76PvP s'active dans \u00a7e" + NoDamageTP.nodamagetimer + " secondes"));
                }
                if (NoDamageTP.nodamagetimer == 1) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76PvP s'active dans \u00a7e" + NoDamageTP.nodamagetimer + " seconde"));
                }
                if (NoDamageTP.nodamagetimer == 0) {
                    Bukkit.getScheduler().cancelTask(NoDamageTP.task);
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a76PvP activ\u00e9!"));
                    for (Player pls : Bukkit.getOnlinePlayers()) {
                        Bukkit.getWorld((String)"world").setPVP(true);
                        pls.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    }
                }
            }
        }, 20, 20);
    }

}

