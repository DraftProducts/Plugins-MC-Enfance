package fr.draftman.UHC;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.draftman.UHC.Utils.ChatUtils;
import fr.draftman.UHC.event.EventManager;
import fr.draftman.UHC.scoreboard.ScoreboardGames;
import fr.draftman.UHC.timers.GodMode;
import fr.draftman.UHC.timers.PvPTimer;

public class Illusion
extends JavaPlugin {
    public static ArrayList<Player> players;

    public void onEnable() {
    	players = new ArrayList<Player>();
        GameState.setState(GameState.LOBBY);
        EventManager.registerEvents((Plugin)this);
        System.out.println("Illusion-Bomber > active");
        Bukkit.getWorld((String)"world").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld((String)"world").setGameRuleValue("naturalRegeneration", "false");
        Bukkit.getWorld((String)"world").setGameRuleValue("doDaylightCycle", "false");
    }

    public void onDisable() {
    	System.out.println("Illusion-Bomber > Desacive");
    }

    public static void end() {
        for (Player pls : players) {
            pls.setStatistic(Statistic.PLAYER_KILLS, 0);
            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + "\u00a74\u00a7k\u00a7l\u00a7o!!\u00a76\u00a7k\u00a7l\u00a7o!!\u00a77\u00a7k\u00a7l\u00a7o!! \u00a76\u00a7oF\u00e9licitation \u00a74\u00a7k\u00a7l\u00a7o!!\u00a76\u00a7k\u00a7l\u00a7o!!\u00a77\u00a7k\u00a7l\u00a7o!! \u00a7e" + pls.getName() + " \u00a76a gagn\u00e9 la partie de \u00a7eFind The Player II "));
        }
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("UhcGames"), new Runnable(){

            @Override
            public void run() {
                Illusion.stop();
            }
        }, 200);
    }

    public static void stop() {
        GameState.setState(GameState.FINISH);
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.getInventory().clear();
            pls.getEquipment().clear();
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "kill " + pls.getName());
            pls.kickPlayer(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7cLa partie est termin\u00e9");
        }
        Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "restart");
    }

    public static void start() {
        Bukkit.getWorld((String)"world").setDifficulty(Difficulty.EASY);
        GameState.setState(GameState.GAME);
        for (Player pls : Bukkit.getOnlinePlayers()) {
            ScoreboardGames.scoreBoardLoad(pls);
        }
        PvPTimer.pvptimer();
        GodMode.godmode();
        Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7cAttention: \u00a77t\u00e9l\u00e9portation risque de lag !"));
        Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " \u00a7cAttention: \u00a77Merci de patienter ..."));
        ArrayList<Location> locs = new ArrayList<Location>();
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 4800.0, 1000.0, 4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 4500.0, 1000.0, 4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 3500.0, 1000.0, 3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 2500.0, 1000.0, 2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1500.0, 1000.0, 1500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -4800.0, 1000.0, -4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -4500.0, 1000.0, -4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -3500.0, 1000.0, -3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -2500.0, 1000.0, -2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1500.0, 1000.0, -1500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 4800.0, 1000.0, -4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 4500.0, 1000.0, -4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 3500.0, 1000.0, -3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 2500.0, 1000.0, -2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1500.0, 1000.0, -1500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -4800.0, 1000.0, 4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -4500.0, 1000.0, 4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -3500.0, 1000.0, 3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -2500.0, 1000.0, 2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1500.0, 1000.0, 1500.0));
        
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, 4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, 4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, 3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, 2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 500.0, 1000.0, 500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, -4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, -4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, -3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, -2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -500.0, 1000.0, -500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, -4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, -4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, -3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 1200.0, 1000.0, -2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 500.0, 1000.0, -500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, 4800.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, 4500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, 3500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -1200.0, 1000.0, 2500.0));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), -500.0, 1000.0, 500.0));
        Random ran = new Random();
        for (Player pls2 : Bukkit.getOnlinePlayers()) {
            Location randomLoc = (Location)locs.get(ran.nextInt(locs.size() + 1));
            pls2.teleport(randomLoc);
            locs.remove((Object)randomLoc);
            pls2.setGameMode(GameMode.SURVIVAL);
            pls2.getInventory().clear();
            pls2.getEquipment().clear();
        }
    }

}


