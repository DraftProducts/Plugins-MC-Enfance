package fr.draftman.menu;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Menu implements Listener {
	
	public ArrayList<Player> staff = new ArrayList<>();
	
	@EventHandler
	public static void onClickItem(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(e.getMaterial() == null) {
				
				return;
			}
			
			Material mat = e.getMaterial();
		
			if(mat == Material.COMPASS && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Jeux")) {
				
				menuPrincipal.menuPrincipalInventory(p);
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public static void onInventoryClicItem(InventoryClickEvent e){
		
		Material mat = e.getCurrentItem().getType();
		Player p = (Player) e.getWhoClicked();
		
		if(mat == Material.BED && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Spawn")){
			p.teleport(new Location(p.getWorld(), 308.5, 78, 5068.5, 0, 0));
			p.closeInventory();
		}
		
		if(mat == Material.GRASS && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Survie")){
			ProxiedPlayer player = (ProxiedPlayer) e.getWhoClicked();	
			player.connect(ProxyServer.getInstance().getServerInfo("Survie"));
			p.closeInventory();
		}
		
		if(mat == Material.FIREBALL && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Staff")){
			p.performCommand("staff");
			p.closeInventory();
		}
		
		if(mat == Material.WOOD_AXE && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Build")){
			p.performCommand("build");
			p.closeInventory();
		}
		
		if(mat == Material.FEATHER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Jump")){
			menuJump.menuJumpInventory(p);
		}
		
		if(mat == Material.INK_SACK && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Jump Spawn")){
			p.teleport(new Location(p.getWorld(), 312.5, 78, 5072.5, -45, 20));
			p.closeInventory();
		}
		
		if(mat == Material.INK_SACK && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Jump Couloir")){
			return;
		}
	}
}