package fr.draftman.events;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import fr.draftman.Practice;
import fr.draftman.Utils.ChatUtils;
import fr.draftman.Utils.TitleManager;
import fr.draftman.commands.CommandsKit;
import fr.draftman.scoreboard.ScoreboardManager;

public class PracticeListeners implements Listener {
	
	private Practice main;
	
	public PracticeListeners(Practice main) {
		this.main = main;
	}
		
		
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		player.setGameMode(GameMode.ADVENTURE);
		player.sendMessage(" ");
		player.sendMessage(Practice.getInstance().centerText(ChatColor.GOLD + "---------------------------------------------------"));
		player.sendMessage(Practice.getInstance().centerText(ChatColor.AQUA + "Bienvenue sur le Practice"));
		player.sendMessage(Practice.getInstance().centerText(ChatColor.GOLD + "---------------------------------------------------"));
		player.sendMessage(" ");
		
		GiveItems.ItemsSpawn(player);
	
		double x = main.getConfig().getDouble("Spawn.x");
        double y = main.getConfig().getDouble("Spawn.y");
        double z = main.getConfig().getDouble("Spawn.z");
        String monde = main.getConfig().getString("Spawn.worldName");
        World world = Bukkit.getWorld(monde);
        player.teleport(new Location(world,x,y,z));
		
		TitleManager.sendTitle(player, "§5™£ §8Practice §5™£", "§3Un Mini-jeux Fun en 1.8", 60);
		TitleManager.sendActionBar(player, "§8Bienvenu sur IllusionNetWork");
		new ScoreboardManager(player).loadScoreboard();
		
		event.setJoinMessage(ChatUtils.getGamePrefix() +"§e"+player.getName()+ " §7vient d'arriver ! §e("+Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers()+")");
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Unranked")){
			GUI.UnrankedGUI(p);
		}
		
		if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cQuitter")){
			QueueManager.removeUnrankedBuildUHC(p);
			p.getInventory().clear();
			GiveItems.ItemsSpawn(p);
			p.sendMessage("\n"+ ChatUtils.getGamePrefix() +"§7Tu as quittÃ© la queue du §6Unranked BuildUHC");
		}
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equalsIgnoreCase("§8Unranked")){
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6BuildUHC")){
				
				e.setCancelled(true);
				p.closeInventory();
				
				p.performCommand("unranked join builduhc");
				
			}
			
		}
		if(e.getInventory().getName().equalsIgnoreCase("§8Duel")){
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6BuildUHC")){
				
				p.closeInventory();
				e.setCancelled(true);
				Player t = Practice.duelRequest.get(p);
				if(t == null){
					p.sendMessage("§cLe joueur s'est déconnecté !");
				}
				try {
					CommandsKit.getKit(p, "BuildUHC");
					CommandsKit.getKit(t, "BuildUHC");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				p.sendMessage("\n"+ ChatUtils.getGamePrefix() +"§7Tu est maintenant en duel avec §a"+t.getName()+" §7en §6BuildUHC §7!");
				t.sendMessage("\n"+ ChatUtils.getGamePrefix() +"§7Tu est maintenant en duel avec §a"+p.getName()+" §7en §6BuildUHC §7!");
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		Player pe = (Player) e.getRightClicked();
		if(p.getItemInHand().getType() == Material.BLAZE_ROD){
			if(pe instanceof Player){
				p.performCommand("duel "+pe.getName());
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		if(!Practice.combat.containsKey(p)){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e){
		Player p = e.getPlayer();
		if(!Practice.combat.containsKey(p)){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			if(e.getDamager() instanceof Player){
				Player p = (Player) e.getEntity();
				Player t = (Player) e.getDamager();
				if(!Practice.combat.containsKey(p) && !Practice.combat.containsKey(t)){
					e.setCancelled(true);
				}else{
					e.setCancelled(false);
				}
			}
		}
		
	}
	
	
}
