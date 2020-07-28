package fr.draftman.events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class QueueManager {
	
	public static ArrayList<Player> unrankedBuildUHC = new ArrayList<Player>();
	public static HashMap<Player, Player> fightingplayer = new HashMap<Player, Player>();
	
	public static Integer getUnrankedBuildUHCSize(){
		return unrankedBuildUHC.size();
	}
	
	public static void addUnrankedBuildUHC(Player p){
		unrankedBuildUHC.add(p);
	}
	
	public static void removeUnrankedBuildUHC(Player p){
		unrankedBuildUHC.remove(p);
	}
	
	public static Player getFightingPlayer(Player p){
		return fightingplayer.get(p);
	}
	
	public static void addFightingPlayer(Player p, Player t){
		fightingplayer.put(p, t);
		fightingplayer.put(t, p);
	}
	
	public static void removeFightingPlayer(Player p, Player t){
		fightingplayer.remove(p, t);
		fightingplayer.remove(t, p);
	}
	
}
