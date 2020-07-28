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
package fr.draftman.event;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.draftman.Bomber;
import fr.draftman.GameState;
import fr.draftman.Utils.ChatUtils;
import fr.draftman.Utils.TitleManager;
import fr.draftman.scoreboard.ScoreboardLobby;

public class PlayerJoin
implements Listener {
    static HashMap<Player, Integer> kill = new HashMap<Player, Integer>();
    static int task;
    public static int timer;

    static {
        timer = 10;
    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        kill.put(e.getPlayer(), 0);
        Player p = e.getPlayer();
        Bomber.players.add(p);
        p.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getEquipment().clear();
        
        ItemStack hub = new ItemStack(Material.BED);
		ItemMeta hubM = hub.getItemMeta();
		hubM.setDisplayName("§6Hub");
		hubM.setLore(Arrays.asList(" ",
				"§7Retourner au hub du server... !",
				" "));
		hub.setItemMeta(hubM);
		p.getInventory().setItem(8, hub);
		
        if (GameState.inStates(GameState.LOBBY)) {
            ScoreboardLobby.scoreBoardLoad();
            int playercount = Bukkit.getServer().getOnlinePlayers().size();
            e.setJoinMessage(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a72" + p.getName() + " \u00a7aa rejoint la partie \u00a77(\u00a7a" + playercount + "\u00a7e/\u00a7a20\u00a77)");
            if (playercount <= 1 ) {
                task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Bomber"), new Runnable(){

                    @Override
                    public void run() {

                    	timer--;

                        ScoreboardLobby.scoreBoardLoad();

                        if (PlayerJoin.timer == 10 || PlayerJoin.timer == 5 || PlayerJoin.timer == 4 || PlayerJoin.timer == 3 || PlayerJoin.timer == 2) {
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " La partie se lance dans " + PlayerJoin.timer + " secondes !"));
                            TitleManager.sendTitle(p, "", ChatColor.RED + "" + PlayerJoin.timer , 5);
                        }
                        if (PlayerJoin.timer == 1) {
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " La partie se lance dans " + PlayerJoin.timer + " seconde !"));
                            TitleManager.sendTitle(p, "", ChatColor.RED + "1 ", 5);
                        }
                        if (PlayerJoin.timer == 0) {
                        	TitleManager.sendTitle(p, "", ChatColor.RED + "Bon jeux", 5);
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " Lancelment de la partie ! "));
                            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " Bonne Chanche :D"));
                            Bomber.start();
                            Bukkit.getScheduler().cancelTask(task);
                        }

                    }
                }, 20, 20);
            }
        } else if (GameState.inStates(GameState.GAME) || GameState.inStates(GameState.FINISH)) {
            e.setJoinMessage(null);
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage("Vous avez rejoint la partie en tant que SPECTATEUR");
            Bomber.players.remove((Object)p);
        }
    }

}

