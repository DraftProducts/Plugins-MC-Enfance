/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerQuitEvent
 */
package fr.draftman.event;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.draftman.Bomber;
import fr.draftman.GameState;
import fr.draftman.Utils.ChatUtils;
import fr.draftman.Utils.GameUtils;

public class PlayerQuit
implements Listener {
    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Bomber.players.remove((Object)p);
        int playeronline = Bukkit.getServer().getOnlinePlayers().size();
        int soustraction = 1;
        int playercount = playeronline - soustraction;
        if (GameState.inStates(GameState.LOBBY)) {
            Bomber.players.remove((Object)p);
            e.setQuitMessage(String.valueOf(ChatUtils.getGamePrefix()) + "\u00a74 " + p.getName() + " \u00a7ca quitt\u00e9 la partie \u00a77(\u00a7a" + playercount + "\u00a7e/\u00a7a20\u00a77)");
            if (playercount == 1) {
                Bukkit.getScheduler().cancelTask(PlayerJoin.task);
                Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7cIl y a moinse de \u00a7420 joueurs"));
            }
        } else if (GameState.inStates(GameState.GAME)) {
            Bomber.players.remove((Object)p);
            e.setQuitMessage(String.valueOf(ChatUtils.getGamePrefix()) + "\u00a74 " + p.getName() + " \u00a7cest mort en d\u00e9connectant");
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "kill " + p.getName());
            if (GameUtils.isWinner()) {
                Bomber.end();
            }
            if (GameState.inStates(GameState.FINISH)) {
                e.setQuitMessage(null);
                Bomber.players.remove((Object)p);
            }
        }
    }
}

