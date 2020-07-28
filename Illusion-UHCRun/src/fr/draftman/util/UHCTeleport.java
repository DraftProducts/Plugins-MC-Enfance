package fr.draftman.util;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class UHCTeleport {

	public static void tpRandom(Player p) {
		
		Random r = new Random();
		
		//ON CREER LES POINTS ALEATOIRES EN X EN Y ET EN Z ET ON MET LE MONDE
		int x = r.nextInt(1000);
		int y = 128;
		int z = - r.nextInt(1000);
		World world = p.getWorld();
		
		//ON CREER LA LOCATION EN FONCTION DES POINTS PRECEDENTS
		Location randomLoc = new Location(world, x,y,z);
		
		p.teleport(randomLoc);
		
	}

}