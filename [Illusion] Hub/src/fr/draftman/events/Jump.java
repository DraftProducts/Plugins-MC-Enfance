package fr.draftman.events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.draftman.Illusion;

public class Jump implements Listener{
	
public ArrayList<Player> jump = new ArrayList<>();
	
	public HashMap<Player, Location> checkpoint = new HashMap<>();
	public HashMap<Player, Inventory> inventoryPlayer = new HashMap<>();
	
	private Illusion plugin;

	public Jump(Illusion pl){
		plugin = pl;
	}
	
	public Jump() {}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onInteract(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		String Title = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Title.Title"));
		
		String SubTitle = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Title.SubTitle"));
		
		String MaterialBlockStart = plugin.getConfig().getString("Start.Block");
		
		String MaterialPlateStart = plugin.getConfig().getString("Start.Plate");
		
		String MaterialBlockStop = plugin.getConfig().getString("Stop.Block");
		
		String MaterialPlateStop = plugin.getConfig().getString("Stop.Plate");
		
		String MaterialBlockCheckPoint = plugin.getConfig().getString("CheckPoint.Block");
		
		String MaterialPlateCheckPoint = plugin.getConfig().getString("CheckPoint.Plate");
		
		String FelicitationMsg = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("FelicitationMsg").replace("<player>", p.getName()));
		
		if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.getMaterial(MaterialPlateStart)) {
			   
			Location loc = e.getTo().getBlock().getRelative(BlockFace.SELF).getLocation();
			Material b = loc.subtract(0, 1, 0).getBlock().getType();
			   
			if(b == Material.getMaterial(MaterialBlockStart)) {
		    
				jumpStart(p);
			}
		}
		
		if(jump.contains(p)) {
			if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.getMaterial(MaterialPlateCheckPoint)) {
				   
				Location loc = e.getTo().getBlock().getRelative(BlockFace.SELF).getLocation();
				Material b = loc.subtract(0, 1, 0).getBlock().getType();
				   
				if(b == Material.getMaterial(MaterialBlockCheckPoint)) {
			    
					checkPointSet(p);
				}
			}
			
			if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.getMaterial(MaterialPlateStop)) {
				   
				Location loc = e.getTo().getBlock().getRelative(BlockFace.SELF).getLocation();
				Material b = loc.subtract(0, 1, 0).getBlock().getType();
				   
				if(b == Material.getMaterial(MaterialBlockStop)) {

					jumpStop(p);
					PlayerUtils.sendTitle(p, Title, SubTitle);
					Bukkit.broadcastMessage(FelicitationMsg);
				}
			}
			
			if(!checkpoint.containsKey(p)){
				if(p.getLocation().getY() < 77) {
					checkPointTeleport(p);
				}
			}else{
				if(p.getLocation().getY() < 81) {
					checkPointTeleport(p);
				}
			}
		}
	}

	@EventHandler
	public void quitJump(PlayerInteractEvent e) {
		
		
		String MaterialBarrier = plugin.getConfig().getString("Stop.Item");
		
		String QuitJump = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("QuitJump"));
		
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		
			if(e.getMaterial() == Material.getMaterial(MaterialBarrier)) {
			
				jumpStop(p);
				p.sendMessage(QuitJump);
			}
		}
	}
	
	@EventHandler
	public void onQuitServerOnJump(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(jump.contains(p)) {
			
			jumpStop(p);
		}
	}
	
	public void jumpStart(Player p) {
		
		String MaterialBarrier = plugin.getConfig().getString("Stop.Item");
		
		String NameBarrier = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Stop.Name"));
		
		if(!inventoryPlayer.containsKey(p)){
			
	    	inventoryPlayer.put(p, p.getInventory());
	    	
	    }
		
		
		
		if(!jump.contains(p)) {
			
			jump.add(p);
			
			ItemStack barrier = new ItemStack(Material.getMaterial(MaterialBarrier), 1);
			ItemMeta barrierM = barrier.getItemMeta();
			barrierM.setDisplayName(NameBarrier);
			barrier.setItemMeta(barrierM);
			
			p.getInventory().clear();
			p.getInventory().setItem(4, barrier);
			
		}
			
	}
	
	public void jumpStop(Player p) {
		
		if(jump.contains(p)) {
			
			jump.remove(p);
			checkpoint.remove(p);
			
			double x = plugin.getConfig().getDouble("Spawn.x");
            double y = plugin.getConfig().getDouble("Spawn.y");
            double z = plugin.getConfig().getDouble("Spawn.z");
            String monde = plugin.getConfig().getString("Spawn.worldName");
            World world = Bukkit.getWorld(monde);
            p.teleport(new Location(world,x,y,z));

		}
	}
	
	public void checkPointSet(Player p) {
		
		String CheckPointSet = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("CheckPointSet"));
		
		Location bL = p.getLocation();
		
		Location loc = new Location(p.getWorld(), bL.getX(), bL.getY()+1, bL.getZ(), 0, 0);
		
		if(!checkpoint.containsKey(p)) {
			
			
			checkpoint.remove(p);
			checkpoint.put(p, loc);
			
			p.sendMessage(CheckPointSet);
		}
		
	}
	
	public void checkPointTeleport(Player p) {
		
		String NoCheckPoint = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("NoCheckPoint"));
		
		String ReturnCheckPoint = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("ReturnCheckPoint"));
		
		if(checkpoint.get(p) != null) {
			
			p.teleport(checkpoint.get(p));
			
			p.sendMessage(ReturnCheckPoint);
			
		} else {
			
			double x = plugin.getConfig().getDouble("SpawnJump.x");
            double y = plugin.getConfig().getDouble("SpawnJump.y");
            double z = plugin.getConfig().getDouble("SpawnJump.z");
            String monde = plugin.getConfig().getString("SpawnJump.worldName");
            World world = Bukkit.getWorld(monde);
            p.teleport(new Location(world,x,y,z));
			
			p.sendMessage(NoCheckPoint);
		}
	}
}

