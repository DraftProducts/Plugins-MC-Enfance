package fr.draftman;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Listeners implements Listener {
	
	private Main main;

	public Listeners(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GOLD + "---------------------------------------------------");
		player.sendMessage(main.centerText(ChatColor.AQUA + "Bienvenue sur le BedWars"));
		player.sendMessage(ChatColor.GOLD + "---------------------------------------------------");
		player.sendMessage(" ");
		
		main.sendTitle(player, "§8BedWars", " ", 60);
		main.sendActionBar(player, "§8Bienvenu sur le BedWars !");
		new ScoreboardManager(player).loadScoreboard();
		
		if (main.isState(GameState.WAITING)) {
			event.setJoinMessage(main.getGamePrefix() +" §e"+player.getName()+ " §7vient d'arriver ! §e("+Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers()+")");
			ScoreboardManager.scoreboardGame.get(player).setLine(1, "§7Joueurs : §a" +Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers());
			player.setGameMode(GameMode.ADVENTURE);
		}else{
			event.setJoinMessage(null);
			player.sendMessage(main.getGamePrefix() +" §7Vous êtes spectateur !");
			player.setGameMode(GameMode.SPECTATOR);
		}
		
		player.getInventory().clear();
		ItemStack leave = new ItemStack(Material.BED);
		ItemMeta leaveM = leave.getItemMeta();
		leaveM.setDisplayName("§cRetourner au Hub");
		leave.setItemMeta(leaveM);
		player.getInventory().addItem(leave);
		
		for (TeamManager team : main.getTeams()) {
			player.getInventory().addItem(team.getIcon());
		}
		

		main.randomTeam(player);

		if (Bukkit.getOnlinePlayers().size() == main.getConfig().getInt("autostart") && main.isState(GameState.WAITING)) {
			Task task = new Task(main);
			task.runTaskTimer(main, 20, 20);
			main.setState(GameState.STARTING); 
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(main.getGamePrefix() +" §e"+player.getName()+ " §7 est parti ! §e("+(Bukkit.getOnlinePlayers().size() - 1)+"/"+Bukkit.getMaxPlayers()+")");
		ScoreboardManager.scoreboardGame.get(player).setLine(1, "§7Joueurs : §a" +(Bukkit.getOnlinePlayers().size() - 1)+"/"+Bukkit.getMaxPlayers());
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		event.setFoodLevel(19);
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent event){
		event.setCancelled(true);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		ItemStack it = event.getItem();
		Player player = event.getPlayer();

		if (it != null && it.getType() == Material.WOOL) {

			for (TeamManager team : main.getTeams()) {

				if (team.getWooldata() == it.getData().getData()) {
					main.addPlayer(player, team);
					continue;
				}

				if (team.getPlayers().contains(player)) {
					team.getPlayers().remove(player);
				}

			}

		}

	}
	
	@EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		if(e.getEntity() instanceof Player){
			if(e.getCause() == DamageCause.FALL){
				if (p.getHealth() <= e.getDamage()) {
					e.setDamage(0);
					main.respawn(p);
					Bukkit.broadcastMessage(main.getGamePrefix() + " " + main.team.get(p).getColor()+p.getName()+" §rs'est écrasé au sol !");
				}
			}
		}
    }
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location l = player.getLocation();
		if (main.isState(GameState.WAITING)) {
			if (l.getY() < 40) {
				player.teleport(main.getSpawn());
			}
		}else if (player.getGameMode() != GameMode.SPECTATOR && main.isState(GameState.GAME)) {
			if (l.getY() < 0) {
				Bukkit.broadcastMessage(main.getGamePrefix() + " " + main.team.get(player).getColor()+player.getName()+" §rest tombé dans le vide !");
				main.respawn(player);
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {

		Block b = event.getBlock();
		Block bp = event.getBlockPlaced();
		double X = bp.getLocation().getX() + 0.5;
		double Y = bp.getLocation().getY();
		double Z = bp.getLocation().getZ() + 0.5;
		Location loc = new Location(bp.getWorld(), X, Y, Z);
		b.setMetadata("placed", new FixedMetadataValue(main, event.getPlayer().getName()));
		
		if (event.getBlockPlaced().getType().equals(Material.TNT)) {
			event.setCancelled(true);
			loc.getWorld().spawnEntity(loc , EntityType.PRIMED_TNT);
		}
		
		if(!main.isState(GameState.GAME)){
			event.setCancelled(true);
			return;
		}
		
		b.getDrops().remove(b);

		if (b.getLocation().getY() >= main.getConfig().getInt("maxblocks")) {
			event.setCancelled(true);
			return;
		}

		if (!main.placedblocks.contains(b)) {
			main.placedblocks.add(b);
		}

	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		Block b = event.getBlock();
		
		Player p = event.getPlayer();
		
		
		if(!main.isState(GameState.GAME)){
			event.setCancelled(true);
			return;
		}

		if (b.getType() != null && b.getType() == Material.WOOL || b.getType() == Material.OBSIDIAN || b.getType() == Material.STAINED_GLASS || b.getType() == Material.BED_BLOCK) {
			event.setCancelled(true);
			return;
		}

		if (!b.hasMetadata("placed") && !main.destroyedBlocks.containsKey(b)) {
			main.destroyedBlocks.put(b.getLocation(), b.getType());
		}

		if (!main.placedblocks.contains(b) && main.destroyedBlocks.containsKey(b)) {
			main.destroyedBlocks.put(b.getLocation(), b.getType());
		}
		
		if (!main.placedblocks.contains(b)){
			event.setCancelled(true);
			p.sendMessage("§cVous ne pouvez pas casser un block qui n'a pas été posé par un joueur !");
			
		}

	}
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if(!main.isState(GameState.GAME)){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {

		if (!main.isState(GameState.GAME)) {
			event.setCancelled(true);
			return;
		}

		if (event.getCause() == DamageCause.FALL) {
			event.setDamage(0.5);
		}

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (player.getHealth() <= event.getDamage()) {
				event.setDamage(0);
				main.respawn(player);
			}
		}

	}
	
	@EventHandler
	public void onPvP(EntityDamageByEntityEvent event) {
		if (!main.isState(GameState.WAITING)) {
		
			if (event.getEntity() instanceof Player) {
				Player player = (Player) event.getEntity();
	
				if (event.getDamager() instanceof Player) {
					Player damager = (Player) event.getDamager();
					if (main.team.get(damager).equals(main.team.get(player))) {
						event.setCancelled(true);
						return;
					}
				}
	
				if (player.getHealth() <= event.getDamage()) {
					Player p = (Player) event.getDamager();
					event.setDamage(0);
					main.respawn(player);
					Bukkit.broadcastMessage(main.getGamePrefix() + " " + main.team.get(player).getColor() + player.getName() + "§r a été tué par §n"+ main.team.get(p).getColor() +p.getName());
				}
			}
		}
	}
	
	//
	/*====================================================
	 *                         NPC
	 ====================================================*/
	
	@EventHandler
    public void stopNPCDamage(EntityDamageEvent e){
       
        Entity ent = e.getEntity();
            
        if(ent instanceof Villager){
            
            Villager npc = (Villager) ent;
           
            if(npc.isCustomNameVisible() && npc.getCustomName() != null){
                e.setCancelled(true);
            }
           
        }
        
    }
       
       
	
	@EventHandler
    public void interactwithNPC(PlayerInteractEntityEvent e){
       
        Entity ent = e.getRightClicked();
        Entity npc = (Villager) ent;
        Player p = e.getPlayer();
       
        if(ent instanceof Villager){
            
            e.setCancelled(true);
            if(npc.getCustomName() == "§cItems"){
            	Menu.menuItems(p);
            }else if(npc.getCustomName() == "§cUpgrades"){
            	p.sendMessage("§cError : En développement !");
            }
           
        }
       
    }
    
}
