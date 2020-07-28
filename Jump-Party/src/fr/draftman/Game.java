package fr.draftman;

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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Game implements Listener{
	public ArrayList<Player> jump = new ArrayList<>();
	
	public HashMap<Player, Location> checkpoint = new HashMap<>();
	
	public HashMap<Player, ItemStack[]> inv = new HashMap<>();
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onInteract(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		String MaterialBlockStart = JumpParty.getInstance().getConfig().getString("Start.Block");
		
		String MaterialPlateStart = JumpParty.getInstance().getConfig().getString("Start.Plate");
		
		String MaterialBlockStop = JumpParty.getInstance().getConfig().getString("Stop.Block");
		
		String MaterialPlateStop = JumpParty.getInstance().getConfig().getString("Stop.Plate");
		
		int MinY = JumpParty.getInstance().getConfig().getInt("MinY");
		
		String MaterialBlockCheckPoint = JumpParty.getInstance().getConfig().getString("CheckPoint.Block");
		
		String MaterialPlateCheckPoint = JumpParty.getInstance().getConfig().getString("CheckPoint.Plate");
		
		String FelicitationMsg = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("FelicitationMsg").replace("<player>", p.getName()));
		
		String Title = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("Title.Title"));
		
		String SubTitle = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("Title.SubTitle"));
		
		Location loc = e.getTo().getBlock().getRelative(BlockFace.SELF).getLocation();
		
		Material b = loc.subtract(0, 1, 0).getBlock().getType();
		
		if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.getMaterial(MaterialPlateStart)) {
			   
			if(b == Material.getMaterial(MaterialBlockStart)) {
		    
				jumpStart(p);
			}
		}
		
		if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.getMaterial(MaterialPlateStop)) {
			
			if(b == Material.getMaterial(MaterialBlockStop)) {
				
				
				PlayerUtils.sendTitle(p, Title, SubTitle);
				Bukkit.broadcastMessage(FelicitationMsg);
				jumpStop(p);
			}
		}
		
		if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.getMaterial(MaterialPlateCheckPoint)) {
			   
			if(b == Material.getMaterial(MaterialBlockCheckPoint)) {
		    
				checkPointSet(p);
			}
		}
		
		if(p.getLocation().getY() < MinY){
			checkPointTeleport(p);
		}
	}

	@EventHandler
	public void quitJump(PlayerInteractEvent e) {
		
		
		String MaterialBarrier = JumpParty.getInstance().getConfig().getString("Stop.Item");
		
		String QuitJump = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("QuitJump"));
		
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		
			if(e.getMaterial() == Material.getMaterial(MaterialBarrier)) {
			
				jumpStop(p);
				p.sendMessage(QuitJump);
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(jump.contains(p)) {
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
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
		
		String MaterialBarrier = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("Stop.Item"));
		
		String NameBarrier = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("Stop.Name"));
		
		if(!jump.contains(p)) {
			
			jump.add(p);
			
			ItemStack barrier = new ItemStack(Material.getMaterial(MaterialBarrier), 1);
			ItemMeta barrierM = barrier.getItemMeta();
			barrierM.setDisplayName(NameBarrier);
			barrier.setItemMeta(barrierM);
			
			inv.put(p, p.getInventory().getContents());
			p.getInventory().clear();
			p.getInventory().setItem(4, barrier);
			
		}
			
	}
	
	public void jumpStop(Player p) {
		
		if(jump.contains(p)) {
			
			jump.remove(p);
			checkpoint.remove(p);
			
			double x = JumpParty.getInstance().getConfig().getDouble("Spawn.x");
            double y = JumpParty.getInstance().getConfig().getDouble("Spawn.y");
            double z = JumpParty.getInstance().getConfig().getDouble("Spawn.z");
            String monde = JumpParty.getInstance().getConfig().getString("Spawn.worldName");
            World world = Bukkit.getWorld(monde);
            p.teleport(new Location(world,x,y,z));
            p.getInventory().clear();
            p.getInventory().setContents(inv.get(p));
		}
	}
	
	public void checkPointSet(Player p) {
		
		String CheckPointSet = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("CheckPointSet"));
		
		Location bL = p.getLocation();
		
		Location loc = new Location(p.getWorld(), bL.getX(), bL.getY()+1, bL.getZ(), 0, 0);
		
		if(!checkpoint.containsKey(p)) {
			
			
			checkpoint.remove(p);
			checkpoint.put(p, loc);
			
			p.sendMessage(CheckPointSet);
		}
		
	}
	
	public void checkPointTeleport(Player p) {
		
		String NoCheckPoint = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("NoCheckPoint"));
		
		String ReturnCheckPoint = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("ReturnCheckPoint"));
		
		if(checkpoint.get(p) != null) {
			
			p.teleport(checkpoint.get(p));
			
			p.sendMessage(ReturnCheckPoint);
			
		} else {
			
			double x = JumpParty.getInstance().getConfig().getDouble("SpawnJump.x");
            double y = JumpParty.getInstance().getConfig().getDouble("SpawnJump.y");
            double z = JumpParty.getInstance().getConfig().getDouble("SpawnJump.z");
            String monde = JumpParty.getInstance().getConfig().getString("SpawnJump.worldName");
            World world = Bukkit.getWorld(monde);
            p.teleport(new Location(world,x,y,z));
			
			p.sendMessage(NoCheckPoint);
		}
	}
}
