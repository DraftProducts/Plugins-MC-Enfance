package fr.draftman;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.draftman.Utils.ChatUtils;
import fr.draftman.event.EventManager;
import fr.draftman.event.GoldRunnable;
import fr.draftman.scoreboard.ScoreboardGames;

public class Bomber extends JavaPlugin {
    public static ArrayList<Player> players;
    
    public static Bomber instance;
	
	public static Bomber getInstance(){
		
	return instance;
	}

	public void onEnable() {
        players = new ArrayList<Player>();
        GameState.setState(GameState.LOBBY);
        EventManager.registerEvents((Plugin)this);
        System.out.println("Illusion-Bomber > active");
        Bukkit.getWorld((String)"world").setDifficulty(Difficulty.PEACEFUL);
        instance = this;
    }

    public void onDisable() {
        System.out.println("Illusion-Bomber > Desacive");
    }

    public static void end() {
        for (Player pls : players) {
            pls.setStatistic(Statistic.PLAYER_KILLS, 0);
            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + "\u00a74\u00a7k\u00a7l\u00a7o!!\u00a76\u00a7k\u00a7l\u00a7o!!\u00a77\u00a7k\u00a7l\u00a7o!! \u00a76\u00a7oF\u00e9licitation \u00a74\u00a7k\u00a7l\u00a7o!!\u00a76\u00a7k\u00a7l\u00a7o!!\u00a77\u00a7k\u00a7l\u00a7o!! \u00a7e" + pls.getName() + " \u00a76a gagn\u00e9 la partie de \u00a7eBomber"));
        }
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Bomber"), new Runnable(){

            @Override
            public void run() {
                Bomber.stop();
            }
        }, 200);
    }

    public static void stop() {
        GameState.setState(GameState.FINISH);
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.getInventory().clear();
            pls.getEquipment().clear();
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "kill " + pls.getName());
            pls.kickPlayer(String.valueOf(ChatUtils.getGamePrefix()) + " La partie est terminé");
        }
        Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "restart");
    }

    public static void start() {
        Bukkit.getWorld((String)"world").setDifficulty(Difficulty.PEACEFUL);
        GameState.setState(GameState.GAME);
        for (Player pls : Bukkit.getOnlinePlayers()) {
            ScoreboardGames.scoreBoardLoad(pls);
        }
        new GoldRunnable().start();
        
        ArrayList<Location> locs = new ArrayList<Location>();
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 21.5, 118, 237.5));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 5.5, 118, 237.5));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 5.5, 118, 253.5));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 21.5, 118, 253.5));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 25.5, 118, 257.5));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 25.5, 118, 233.5));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 2.5, 118, 233.5));
        locs.add(new Location((World)Bukkit.getWorlds().get(0), 2.5, 118, 256.5));
        
        Random ran = new Random();
        
        for (Player pls : Bukkit.getOnlinePlayers()) {
            Location randomLoc = (Location)locs.get(ran.nextInt(locs.size() + 1));
            pls.teleport(randomLoc);
            locs.remove((Object)randomLoc);
            pls.setGameMode(GameMode.SURVIVAL);
            pls.getInventory().clear();
            pls.getEquipment().clear();
            
            ItemStack bow = new ItemStack(Material.BOW);
    		ItemMeta bowM = bow.getItemMeta();
    		bowM.setDisplayName("§6CatchBow");
    		bow.setItemMeta(bowM);
    		pls.getInventory().setItem(0, bow);
    		
    		ItemStack slime = new ItemStack(Material.SLIME_BALL);
    		ItemMeta slimeM = slime.getItemMeta();
    		slimeM.setDisplayName("§6Bombe");
    		slime.setItemMeta(slimeM);
    		pls.getInventory().setItem(1, slime);
    		
    		ItemStack shop = new ItemStack(Material.GOLD_NUGGET);
    		ItemMeta shopM = shop.getItemMeta();
    		shopM.setDisplayName("§8Shop");
    		shop.setItemMeta(shopM);
    		pls.getInventory().setItem(8, shop);
    		
    		ItemStack arrow = new ItemStack(Material.ARROW, 5, (byte) 0);
    		ItemMeta arrowM = arrow.getItemMeta();
    		arrowM.setDisplayName("§6Arrow");
    		arrow.setItemMeta(arrowM);
    		pls.getInventory().setItem(9, arrow);
    		
    		pls.setMaxHealth(20);
    		pls.setHealth(20);
    		
        }
    }

}

