package fr.draftman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fr.draftman.Utils.ChatUtils;

public class HikaBrain extends JavaPlugin implements Listener{
	
	public List<Block> placedblocks = new ArrayList<>();
	public Map<Location, Material> destroyedBlocks = new HashMap<>();
	public List<Player> players = new ArrayList<>();
	
	public List<HTeam> teams = new ArrayList<>();
	public Map<Player, HTeam> team = new HashMap<>();
	
	private Location spawn;
	private HState current;
	
	public Cuboide Zone1;
	public Cuboide Zone2;

	@Override
	public void onEnable() {
		setState(HState.WAITING);
		getServer().getPluginManager().registerEvents(new HListeners(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		saveDefaultConfig();
		
		spawn = getParseLoc(getConfig().getString("spawn"));
		
		ConfigurationSection section = getConfig().getConfigurationSection("teams");
		for(String team : section.getKeys(false)){
			String name = section.getString(team + ".name").replace("&", "§");
			String color = section.getString(team + ".color").replace("&", "§");
			byte data = (byte) section.getInt(team + ".wooldata");
			String spawn = section.getString(team + ".spawn");
			teams.add(new HTeam(name,color,data,getParseLoc(spawn)));
		}
		
		Bukkit.getWorld("world").setTime(18000);
        if(getConfig().getBoolean("location")){
                Zone1 = new Cuboide(loadLocation("zone1.l1"), loadLocation("zone1.l2"));
                Zone2 = new Cuboide(loadLocation("zone2.l1"), loadLocation("zone2.l2"));
        }
		
	}
	
	@Override
	public void onDisable() {
		reset();
	}
	
	private Location getParseLoc(String spawn) {
		String[] str = spawn.split(",");
		double x = Double.valueOf(str[0]);
		double y = Double.valueOf(str[1]);
		double z = Double.valueOf(str[2]);
		float yaw = Float.valueOf(str[3]);
		float pitch = Float.valueOf(str[4]);
		return new Location(Bukkit.getWorld("world"),x,y,z,yaw,pitch);
	}

	public void setState(HState state){
		this.current = state;
	}
	
	public boolean isState(HState state){
		return current == state;
	}

	public void reset() {
		
		Iterator<Block> bs = placedblocks.iterator();
		while(bs.hasNext()){
			bs.next().setType(Material.AIR);
		}
		
		for(Entry<Location, Material> blocks : destroyedBlocks.entrySet()){
			blocks.getKey().getBlock().setType(blocks.getValue());
		}
		
		for(Player pls : players){
			respawn(pls);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void respawn(Player player){
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20D);
		player.teleport(getTeam(player).getSpawn());
		player.getInventory().clear();
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordM = sword.getItemMeta();
		swordM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		swordM.addEnchant(Enchantment.KNOCKBACK, 1, true);
		sword.setItemMeta(swordM);
        player.getInventory().setItem(0, sword);
        
		player.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));
		player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE, 64));
		player.getInventory().addItem(new ItemStack(Material.WOOD, 384, (byte) 0));
		
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(DyeColor.getByWoolData(getTeam(player).getWooldata()).getColor());
        helmet.setItemMeta(meta);
        player.getInventory().setHelmet(helmet);
        
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta meta2 = (LeatherArmorMeta) chestplate.getItemMeta();
        meta2.setColor(DyeColor.getByWoolData(getTeam(player).getWooldata()).getColor());
        chestplate.setItemMeta(meta2);
        player.getInventory().setChestplate(chestplate);
        
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta3 = (LeatherArmorMeta) leggings.getItemMeta();
        meta3.setColor(DyeColor.getByWoolData(getTeam(player).getWooldata()).getColor());
        leggings.setItemMeta(meta3);
        player.getInventory().setLeggings(leggings);
        
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta meta4 = (LeatherArmorMeta) boots.getItemMeta();
        meta4.setColor(DyeColor.getByWoolData(getTeam(player).getWooldata()).getColor());
        boots.setItemMeta(meta3);
        player.getInventory().setBoots(boots);

	}
	
	private HTeam getTeam(Player player) {
		return team.get(player);
	}
	
	public String centerText(String text) {
		int space = (int) Math.round((80.0D - 1.4D * text.length()) / 2.0D);
		return repeat(" ", space) + text;
	}
	
	private String repeat(String text, int times) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			stringBuilder.append(text);
		}
		return stringBuilder.toString();
	}
	
	public void addPlayer(Player player, HTeam team){
		
		if(team.getPlayers().contains(player)){
			player.sendMessage(ChatUtils.getGamePrefix() +" §7Vous êtes déjà dans l'équipe "+team.getColor() + team.getName());
			return;
		}
		
		if(team.getSize() >= getConfig().getInt("maxperteam")){
			player.sendMessage(ChatUtils.getGamePrefix() +" §7L'Equipe est déjà  full !");
			return;
		}
		
		team.addPlayer(player);
		player.setPlayerListName(team.getColor() + player.getName());
		player.sendMessage(ChatUtils.getGamePrefix() +" §7Vous rejoignez l'équipe "+team.getColor() + team.getName());
		
	}
	
	public void loadTeams(){
		for(HTeam myteam : teams){
			for(Player pls : myteam.getPlayers()){
				team.put(pls, myteam);
			}
		}
	}
	
	public void randomTeam(Player player){
		
		if(!team.containsKey(player)){
			for(HTeam team : teams){
				if(team.getSize() < getConfig().getInt("maxperteam")){
					addPlayer(player, team);;
					break;
				}
			}
		}
		
	}

	public List<HTeam> getTeams() {
		return teams;
	}

	public Location getSpawn() {
		return spawn;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
           
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("setzone")){
            if(args.length == 2){
                if((args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2"))){
                        saveLocation(args[0]+".l"+args[1], p.getLocation());
                        getConfig().set("location", true);
                        saveConfig();
                        p.sendMessage("§2La position §c"+args[1]+"§2 pour la zone §c"+args[0]+"§2 a été définis !");
                }
            
            }
        }
        return true;
    }
	
	private void saveLocation(String chemin, Location l){
        getConfig().set(chemin + ".x", l.getX());
        getConfig().set(chemin + ".y", l.getY());
        getConfig().set(chemin + ".z", l.getZ());
        getConfig().set(chemin + ".w", l.getWorld().getName());
        saveConfig();
	}
	
	private Location loadLocation(String chemin){
        double x = getConfig().getDouble(chemin + ".x");
        double y = getConfig().getDouble(chemin + ".y");
        double z = getConfig().getDouble(chemin + ".z");
        World w = Bukkit.getWorld(getConfig().getString(chemin+".w"));
        return new Location(w,x,y,z);
	}
	
}

