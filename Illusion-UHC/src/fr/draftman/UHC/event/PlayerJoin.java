/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.inventory.EntityEquipment
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.plugin.Plugin
 */
package fr.draftman.UHC.event;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.draftman.UHC.GameState;
import fr.draftman.UHC.Illusion;
import fr.draftman.UHC.Utils.ChatUtils;
import fr.draftman.UHC.scoreboard.ScoreboardLobby;

public class PlayerJoin
implements Listener {
    static HashMap<Player, Integer> kill = new HashMap<Player, Integer>();
    static int task;
    public static int timer;

    static {
        timer = 121;
    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        kill.put(e.getPlayer(), 0);
        Player p = e.getPlayer();
        Illusion.players.add(p);
        p.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getEquipment().clear();
        if (GameState.inStates(GameState.LOBBY)) {
            ScoreboardLobby.scoreBoardLoad();
            Bukkit.getWorld((String)"world").setPVP(false);
            int playercount = Bukkit.getServer().getOnlinePlayers().size();
            e.setJoinMessage(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a72" + p.getName() + " \u00a7aa rejoint la partie \u00a77(\u00a7a" + playercount + "\u00a7e/\u00a7a20\u00a77)");
            if (playercount == 40) {
                task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UhcGames"), new Runnable(){

                    @Override
                    public void run() {
                        if (--PlayerJoin.timer <= 120 && PlayerJoin.timer != 0) {
                            ScoreboardLobby.scoreBoardLoad();
                        }
                        if (PlayerJoin.timer == 120) {
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7aLa partie se lance dans \u00a7e2 minutes !"));
                        }
                        if (PlayerJoin.timer == 60) {
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7aLa partie se lance dans \u00a7e1 minute !"));
                        }
                        if (PlayerJoin.timer == 30 || PlayerJoin.timer == 15 || PlayerJoin.timer == 10 || PlayerJoin.timer == 9 || PlayerJoin.timer == 8 || PlayerJoin.timer == 7 || PlayerJoin.timer == 6 || PlayerJoin.timer == 5 || PlayerJoin.timer == 4 || PlayerJoin.timer == 3 || PlayerJoin.timer == 2) {
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7aLa partie se lance dans \u00a7e" + PlayerJoin.timer + " secondes !"));
                        }
                        if (PlayerJoin.timer == 1) {
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7aLa partie se lance dans \u00a7e" + PlayerJoin.timer + " seconde !"));
                        }
                        if (PlayerJoin.timer == 0) {
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7aLa partie ce lance ! "));
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7aBon jeux :D "));
                            Illusion.start();
                        }
                    }
                }, 20, 20);
            }
        } else if (GameState.inStates(GameState.PREPVP) || GameState.inStates(GameState.GAME) || GameState.inStates(GameState.FINISH)) {
            e.setJoinMessage(null);
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage(" \u00a7aVous avez rejoint la partie en tant que \u00a77SPECTATEUR");
            Illusion.players.remove((Object)p);
        }
    }

}

