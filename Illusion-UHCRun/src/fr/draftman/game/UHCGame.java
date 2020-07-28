package fr.draftman.game;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.draftman.UHCRun;
import fr.draftman.util.UHCBordures;
import fr.draftman.util.UHCTeleport;

public class UHCGame {
	
	public static int timer = 1800;
	static int task;

	public static void start() {
		//ON SOCCUPE DU LANCEMENT DU JEU
		
		UHCState.setState(UHCState.PREGAME);
		
		for(UUID uuid : UHCRun.getInstance().playerInGame){
			Player pl = Bukkit.getPlayer(uuid);
			//ON DONNE UN BOOST DE VITESSE AUX JOUEURS DE LA GAME PENDANT QUELQUES SECONDES
			pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1));
			
			//KITS
			
			//ON TP LES JOUEURS ALEATOIREMENT SUR LA MAP
			UHCTeleport.tpRandom(pl);
		}
		
		//ON LANCE LA BOUCLE PRINCIPAL DU JEU
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(UHCRun.getInstance(), new Runnable(){

			@Override
			public void run() {
				timer--;
				
				if(timer == 1770){ //APRES 30 SECONDES LES DEGATS SONT ACTIVES
					UHCState.setState(UHCState.GAME);
				}	
				
				if(timer == 1200){ //APRES 10 MIN DE JEU LE PVP EST ON
					
					//ON REACTIVE LE PVP DANS LE MONDE 'WORLD'
					Bukkit.getWorld("world").setPVP(true);
					
					//ON SET LE JEU EN MODE GAMEPVP
					UHCState.setState(UHCState.GAMEPVP);
					
					//ON PEUT METTRE UN BROADCAST
					
				}
				
				if(timer <= 1200){ //APRES 10 MIN LES BORDURES RETRECISSENT
					
					if(timer > 0){
					
					UHCBordures b = new UHCBordures();
					//ON REDUIT LA BORDURE A CHAQUE SECONDE
					b.decreaseBorder();
					
					}
					
				}
				
				if(timer == 0){

					//ON TP EN 0 0
					for(UUID uuid : UHCRun.getInstance().playerInGame){
						Player pl = Bukkit.getPlayer(uuid);
						pl.teleport(new Location(Bukkit.getWorld("world"), 0,0,0));
					}
					
					//ON MET LA BORDURE A 50 * 50
					UHCBordures b = new UHCBordures();
					b.setBorder(50.0);
					
				}

				
				
			}
			
		},20,20);
		
	}

}
