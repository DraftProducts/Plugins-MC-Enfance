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

public class GodMode {
    static int task;
    static int godtimer;

    static {
        godtimer = 121;
    }

    public static void godmode() {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 120));
        }
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UhcGames"), new Runnable(){

            @Override
            public void run() {
                if (--GodMode.godtimer == 120) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a77Vous perdrez des d\u00e9gats dans \u00a7e2 minutes"));
                }
                if (GodMode.godtimer == 60) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a77Vous perdrez des d\u00e9gats dans \u00a7e1 minute"));
                }
                if (GodMode.godtimer == 30 || GodMode.godtimer == 15 || GodMode.godtimer == 10 || GodMode.godtimer == 9 || GodMode.godtimer == 8 || GodMode.godtimer == 7 || GodMode.godtimer == 6 || GodMode.godtimer == 5 || GodMode.godtimer == 4 || GodMode.godtimer == 3 || GodMode.godtimer == 2) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a77Vous perdrez des d\u00e9gats dans \u00a7e" + GodMode.godtimer + " secondes"));
                }
                if (GodMode.godtimer == 1) {
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a77Vous perdrez des d\u00e9gats dans \u00a7e" + GodMode.godtimer + " seconde"));
                }
                if (GodMode.godtimer == 0) {
                    Bukkit.getScheduler().cancelTask(GodMode.task);
                    Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a77Vous perdez des d\u00e9gats maintenant"));
                    for (Player pls : Bukkit.getOnlinePlayers()) {
                        pls.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    }
                }
            }
        }, 20, 20);
    }

}

