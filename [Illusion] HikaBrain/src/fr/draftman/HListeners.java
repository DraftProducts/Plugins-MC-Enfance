package fr.draftman;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import fr.draftman.Utils.ChatUtils;
import fr.draftman.Utils.TitleManager;
import fr.draftman.scoreboard.ScoreboardManager;
import fr.neutronstars.message.BukkitMessageBuilder;

public class HListeners implements Listener {

	private HikaBrain main;

	public HListeners(HikaBrain main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GOLD + "---------------------------------------------------");
		player.sendMessage(main.centerText(ChatColor.AQUA + "Bienvenue sur le HikaBrain"));
		new BukkitMessageBuilder(main.centerText("Ce Jeux est inspiré par la Map de ")).next("§n§6Hika").setHover("Aller vers sa chaine !").click(net.md_5.bungee.api.chat.ClickEvent.Action.OPEN_URL, "https://www.youtube.com/channel/UC5SYlO-7_Kw2uUPYuPmcyLg").send(player);
		player.sendMessage(ChatColor.GOLD + "---------------------------------------------------");
		player.sendMessage(" ");
		
		player.teleport(main.getSpawn());
		TitleManager.sendTitle(player, "§5 §8Hikabrain §5", "§3Un Mini-jeux Fun en 1.8", 60);
		TitleManager.sendActionBar(player, "§8Bienvenu sur IllusionNetWork");
		new ScoreboardManager(player).loadScoreboard();
		
		player.getInventory().clear();
		
		if (main.isState(HState.WAITING)) {
			event.setJoinMessage(ChatUtils.getGamePrefix() +" §e"+player.getName()+ " §7vient d'arriver ! §e("+Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers()+")");
			ScoreboardManager.scoreboardGame.get(player).setLine(1, "§7Joueurs : §a" +Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers());
			player.setGameMode(GameMode.ADVENTURE);
			main.players.add(player);
			for (HTeam team : main.getTeams()) {
				player.getInventory().addItem(team.getIcon());
			}
		}else{
			event.setJoinMessage(null);
			player.sendMessage(ChatUtils.getGamePrefix() +" §7Vous êtes spectateur !");
			player.setGameMode(GameMode.SPECTATOR);
			ItemStack hub = new ItemStack(Material.BED);
			ItemMeta hubM = hub.getItemMeta();
			hubM.setDisplayName("§6Hub");
			hubM.setLore(Arrays.asList(" ",
					"§7Cet item te permettra de retourner",
					"§7au Hub pour pouvoir choisir un autre",
					"§7jeu car celui-ci est en développement",
					" "));
			hub.setItemMeta(hubM);
			player.getInventory().setItem(4, hub);
		}
		

		main.randomTeam(player);

		if (Bukkit.getOnlinePlayers().size() == main.getConfig().getInt("autostart") && main.isState(HState.WAITING)) {
			HTask task = new HTask(main);
			task.runTaskTimer(main, 20, 20);
			main.setState(HState.STARTING);
		}
	}
	
	@EventHandler
	public void onClickItem(PlayerInteractEvent e) {
		
		
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			if(e.getMaterial() == null) {
				
				return;
			}
			
			Material mat = e.getMaterial();
		
			if(mat == Material.BED && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Hub")) {
				p.kickPlayer(ChatUtils.getGamePrefix() +" Vous avez bien quitté le Hikabrain");
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(ChatUtils.getGamePrefix() +" §e"+player.getName()+ " §7 est parti ! §e("+(Bukkit.getOnlinePlayers().size() - 1)+"/"+Bukkit.getMaxPlayers()+")");
		ScoreboardManager.scoreboardGame.get(player).setLine(1, "§7Joueurs : §a" +(Bukkit.getOnlinePlayers().size() - 1)+"/"+Bukkit.getMaxPlayers());
		if(main.players.contains(player)){
			main.players.remove(player);
		}
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		event.setFoodLevel(20);
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

			for (HTeam team : main.getTeams()) {

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
					Bukkit.broadcastMessage(ChatUtils.getGamePrefix() + " " + main.team.get(p).getColor()+p.getName()+" §rs'est écrasé au sol !");
				}
			}
		}
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location l = player.getLocation();
		if (l.getY() < 82) {
			if (player.getGameMode() != GameMode.SPECTATOR && main.isState(HState.GAME)) {
				Bukkit.broadcastMessage(ChatUtils.getGamePrefix() + " " + main.team.get(player).getColor()+player.getName()+" §rest tombé dans le vide !");
				main.respawn(player);
			}
			if (main.isState(HState.WAITING)) {
				player.teleport(main.getSpawn());
			}
		}
		if (l.add(0, -0.5, 0).getBlock().getType() == Material.BED_BLOCK) {

			Block wool = l.add(0, -0.7, 0).getBlock();
			if (wool.getType() == Material.WOOL) {

				HTeam team = main.team.get(player);
				byte data = wool.getData();

				if (team.getWooldata() != data) {

					team.addPoint();
					main.reset();

					Bukkit.broadcastMessage(ChatUtils.getGamePrefix() +" §rL'Equipe "+ team.getColor() + team.getName() +" §ra gagné 1 point ! §e("+ team.getPoints() +"/"+ main.getConfig().getInt("pointsforwin")+")");
					for (Player pls : Bukkit.getOnlinePlayers()){
						TitleManager.sendTitle(pls, "", team.getColor()+"+1 point", 5);
						ScoreboardManager.scoreboardGame.get(pls).setLine(2, " §6Orange : "+main.teams.get(0).getPoints());
						ScoreboardManager.scoreboardGame.get(pls).setLine(3, " §aVert : "+main.teams.get(1).getPoints());
						if(team.getPoints() < 5){
							pls.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
						}
					}

					if (team.getPoints() >= main.getConfig().getInt("pointsforwin") || Bukkit.getOnlinePlayers().size() == 1){

						Bukkit.broadcastMessage(ChatUtils.getGamePrefix() +" §rL'Equipe "+team.getColor() + team.getName()+" §ra gagné la partie !");
						main.setState(HState.FINISH);

						for (Player pls : Bukkit.getOnlinePlayers()) {
							TitleManager.sendTitle(pls, "", team.getColor()+"Victoire des "+ team.getName()+"s", 20);
							pls.setGameMode(GameMode.SPECTATOR);
							pls.playSound(player.getLocation(), Sound.FIREWORK_TWINKLE, 10, 1);
						}

						Bukkit.getScheduler().runTaskLater(main, new Runnable() {

							@Override
							public void run() {
								for (Player pls : Bukkit.getOnlinePlayers()) {
									pls.kickPlayer(ChatUtils.getGamePrefix() +" Restart...");
								}

								Bukkit.reload();
							}

						}, 20 * 5);

						return;
					}

				}

			}

		}

	}

	// blocks

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		
		Player p = event.getPlayer();
		Block b = event.getBlock();
		b.setMetadata("placed", new FixedMetadataValue(main, event.getPlayer().getName()));
		
		if(!main.isState(HState.GAME)){
			event.setCancelled(true);
			return;
		}

		if (b.getLocation().getY() >= main.getConfig().getInt("maxblocks")) {
			event.setCancelled(true);
			return;
		}
		
		if(main.Zone1.isInCube(b)){
            p.sendMessage("§4Ne joue pas Cancer !");
	    }else if(main.Zone2.isInCube(b)){
	    	p.sendMessage("§4Ne joue pas Cancer !");
	    }

		if (!main.placedblocks.contains(b)) {
			main.placedblocks.add(b);
		}
		
		if (event.getBlock().getRelative(BlockFace.EAST).getType() == Material.BARRIER || 
				event.getBlock().getRelative(BlockFace.NORTH).getType() == Material.BARRIER || 
				event.getBlock().getRelative(BlockFace.WEST).getType() == Material.BARRIER || 
				event.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.BARRIER || 
				event.getBlock().getRelative(BlockFace.DOWN).getType() == Material.BED_BLOCK || 
				event.getBlock().getRelative(BlockFace.EAST).getType() == Material.BED_BLOCK || 
				event.getBlock().getRelative(BlockFace.UP).getType() == Material.BED_BLOCK || 
				event.getBlock().getRelative(BlockFace.NORTH).getType() == Material.BED_BLOCK || 
				event.getBlock().getRelative(BlockFace.WEST).getType() == Material.BED_BLOCK || 
				event.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.BED_BLOCK){
			event.setCancelled(true);
		}

	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		Block b = event.getBlock();
		
		if(!main.isState(HState.GAME)){
			event.setCancelled(true);
			return;
		}
		
		if (b.getType() != null && b.getType() == Material.BED || b.getType() == Material.WOOD_STAIRS || b.getType() == Material.STONE_SLAB2 || b.getType() == Material.QUARTZ || b.getType() == Material.STAINED_GLASS){
			event.setCancelled(true);
		}
		
		if (!b.hasMetadata("placed") && !main.destroyedBlocks.containsKey(b)) {
			main.destroyedBlocks.put(b.getLocation(), b.getType());
		}

		if (!main.placedblocks.contains(b) && main.destroyedBlocks.containsKey(b)) {
			main.destroyedBlocks.put(b.getLocation(), b.getType());
		}
		
	}
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {

		if (!main.isState(HState.GAME)) {
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
		if (!main.isState(HState.WAITING)) {
		
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
					Bukkit.broadcastMessage(ChatUtils.getGamePrefix() + " " + main.team.get(player).getColor() + player.getName() + "§r a été tué par §n"+ main.team.get(p).getColor() +p.getName());
				}
			}
		}
	}

}
