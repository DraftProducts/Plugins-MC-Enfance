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
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Main extends JavaPlugin{
	
	private GameState current;
	private Location spawn;
	
	public String prefix = "§7[§6BedWars§7]";

    public String getGamePrefix() {
        return prefix;
    }
    
    public List<Block> placedblocks = new ArrayList<>();
	public Map<Location, Material> destroyedBlocks = new HashMap<>();
	
	public List<TeamManager> teams = new ArrayList<>();
	public Map<Player, TeamManager> team = new HashMap<>();
	
	@Override
	public void onEnable() {
		setState(GameState.WAITING);
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		getServer().getPluginManager().registerEvents(new Menu(), this);
		
		spawn = getParseLoc(getConfig().getString("spawn"));
		
		saveDefaultConfig();
		
		ConfigurationSection section = getConfig().getConfigurationSection("teams");
		for(String team : section.getKeys(false)){
			String name = section.getString(team + ".name").replace("&", "§");
			String color = section.getString(team + ".color").replace("&", "§");
			byte data = (byte) section.getInt(team + ".wooldata");
			String spawn = section.getString(team + ".spawn");
			teams.add(new TeamManager(name,color,data,getParseLoc(spawn)));
		}
		
	}
	
	@Override
	public void onDisable() {
	}
	
	public void setState(GameState state){
		this.current = state;
	}
	
	public boolean isState(GameState state){
		return current == state;
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
	
	public void reset() {
		
		Iterator<Block> bs = placedblocks.iterator();
		while(bs.hasNext()){
			bs.next().setType(Material.AIR);
		}
		
		for(Entry<Location, Material> blocks : destroyedBlocks.entrySet()){
			blocks.getKey().getBlock().setType(blocks.getValue());
		}
		
		for(Player pls : Bukkit.getOnlinePlayers()){
			respawn(pls);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void respawn(Player player){
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20D);
		player.teleport(getTeam(player).getSpawn());
		player.getInventory().clear();
		
		player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordM = sword.getItemMeta();
		swordM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		sword.setItemMeta(swordM);
        player.getInventory().setItem(0, sword);
        
		player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
		player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE, 64));
		player.getInventory().addItem(new ItemStack(Material.SANDSTONE, 384, (byte) 2));
		
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
	
	private TeamManager getTeam(Player player) {
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
	
	public void addPlayer(Player player, TeamManager team){
		
		if(team.getPlayers().contains(player)){
			player.sendMessage(getGamePrefix() +" §7Vous êtes déjà  dans l'équipe "+team.getColor() + team.getName());
			return;
		}
		
		if(team.getSize() >= getConfig().getInt("maxperteam")){
			player.sendMessage(getGamePrefix() +" §7L'Equipe est déjà  full !");
			return;
		}
		
		team.addPlayer(player);
		player.setPlayerListName(team.getColor() + player.getName());
		player.sendMessage(getGamePrefix() +" §7Vous rejoignez l'équipe "+team.getColor() + team.getName());
		
	}
	
	public void loadTeams(){
		for(TeamManager myteam : teams){
			for(Player pls : myteam.getPlayers()){
				team.put(pls, myteam);
			}
		}
	}
	
	public void randomTeam(Player player){
		
		if(!team.containsKey(player)){
			for(TeamManager team : teams){
				if(team.getSize() < getConfig().getInt("maxperteam")){
					addPlayer(player, team);;
					break;
				}
			}
		}
		
	}

	public List<TeamManager> getTeams() {
		return teams;
	}

	public Location getSpawn() {
		return spawn;
	}
	
	public void InventoryWaiting(Player p){
		Inventory inv = p.getInventory();
		inv.clear();
		
		ItemStack bed = new ItemStack(Material.BED);		
		ItemMeta bedM = bed.getItemMeta();	
		bedM.setDisplayName("Hub");
		bed.setItemMeta(bedM);
        inv.setItem(8, bed);
	}
	
	public void InventoryGame(Player p){
		Inventory inv = p.getInventory();
		inv.clear();
		
		ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        inv.setItem(0, sword);
	}
	
	public void InventoryFinish(Player p){
		Inventory inv = p.getInventory();
		inv.clear();
		
		ItemStack bed = new ItemStack(Material.PAPER);
		ItemStack restart = new ItemStack(Material.BED);
		
		ItemMeta bedM = bed.getItemMeta();
		ItemMeta restartM = restart.getItemMeta();
		
		bedM.setDisplayName("Hub");
		restartM.setDisplayName("Relancer");
		
		bed.setItemMeta(bedM);
		restart.setItemMeta(restartM);
		
        inv.setItem(8, bed);
        inv.setItem(0, restart);
	}
	
	public void InventorySpectator(Player p){
		Inventory inv = p.getInventory();
		inv.clear();
		
		ItemStack spectate = new ItemStack(Material.EYE_OF_ENDER);
		ItemStack restart = new ItemStack(Material.BED);
		
		ItemMeta spectateM = spectate.getItemMeta();
		ItemMeta restartM = restart.getItemMeta();
		
		spectateM.setDisplayName("Observer");
		restartM.setDisplayName("Relancer");
		
		spectate.setItemMeta(spectateM);
		restart.setItemMeta(restartM);
		
        inv.setItem(0, spectate);
        inv.setItem(8, restart);
	}

	public void sendTitle(Player player, String msgTitle, String msgSubTitle, int ticks) {
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + msgTitle + "\"}");
		IChatBaseComponent chatSubTitle = ChatSerializer.a("{\"text\": \"" + msgSubTitle + "\"}");
		PacketPlayOutTitle p = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle p2 = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(p2);
		sendTime(player, ticks);
	}

	private void sendTime(Player player, int ticks) {
		PacketPlayOutTitle p = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);
	}

	public void sendActionBar(Player player, String message) {
		IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
	}
	
	

}
