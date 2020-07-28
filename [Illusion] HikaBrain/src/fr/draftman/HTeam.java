package fr.draftman;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HTeam {
	
	private int points;
	private Location spawn;
	private byte wooldata;
	private String name, color;
	private List<Player> players = new ArrayList<>();
	
	public HTeam(String name, String color, byte data, Location spawn) {
		this.points = 0;
		this.name = name;
		this.color = color;
		this.wooldata = data;
		this.spawn = spawn;
	}
	
	public ItemStack getIcon(){
		ItemStack i = new ItemStack(Material.WOOL,1,wooldata);
		ItemMeta iM = i.getItemMeta();
		iM.setDisplayName("Equipe " + color + name);
		i.setItemMeta(iM);
		return i;
	}
	
	public void addPlayer(Player player){
		this.players.add(player);
	}
	
	public void removePlayer(Player player){
		this.players.remove(player);
	}
	
	public int getSize(){
		return players.size();
	}

	public void addPoint(){
		this.points++;
	}
	
	public int getPoints() {
		return points;
	}

	public Location getSpawn() {
		return spawn;
	}

	public byte getWooldata() {
		return wooldata;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public List<Player> getPlayers() {
		return players;
	}



}
