package fr.draftman.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.draftman.Illusion;
import fr.draftman.events.lstJoin;

public class BuildCommand implements CommandExecutor, Listener{

	public BuildCommand(Illusion pl) {}

	public BuildCommand() {}

	public static ArrayList<String> buildmod = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if(sender instanceof Player) {
		
			if(cmd.getName().equalsIgnoreCase("build")) {
				
				if(p.hasPermission("perm.build") || p.getPlayer().isOp()) {
					
					if(buildmod.contains(p.getName())) {
						
						buildmod.remove(p.getName());
						p.setGameMode(GameMode.ADVENTURE);
						p.setAllowFlight(true);
						lstJoin.giveItems(p);
						p.sendMessage(ChatColor.GREEN +"Le Mod Builder est maintenant désactivé !");
					} else {
						
						buildmod.remove(p.getName());
						buildmod.add(p.getName());
						lstJoin.giveItemsBuild(p);
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(ChatColor.GREEN +"Le Mod Builder est maintenant activé !");
					}
				} else {
					
					p.sendMessage("§4Vous devez être OP!");
				}
			}
		} else {
			
			p.sendMessage("Vous devez etre un joueur!");
		}
		
		return false;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		if(buildmod.contains(p.getName())) {
			
			buildmod.remove(p.getName());
			return;
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		
		if(!buildmod.contains(p.getName())) {
			
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		
		if(!buildmod.contains(p.getName())) {
			
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		
		Player p = e.getPlayer();
		
		if(!buildmod.contains(p.getName())) {
			
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		
		Player p = e.getPlayer();
		
		if(!buildmod.contains(p.getName())) {
			
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if(!buildmod.contains(p.getName())) {
			
			e.setCancelled(true);
			return;
		}
	}
	
}
