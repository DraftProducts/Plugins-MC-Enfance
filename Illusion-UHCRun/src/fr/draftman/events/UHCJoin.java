package fr.draftman.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.draftman.UHCRun;
import fr.draftman.api.Sounds;
import fr.draftman.api.Title;
import fr.draftman.game.UHCGame;
import fr.draftman.game.UHCPvP;
import fr.draftman.game.UHCState;
import fr.draftman.scoreboards.CustomScoreboardManager;
import fr.draftman.scoreboards.ScoreboardRunnable;

public class UHCJoin implements Listener {
	
	static int task;
	public static int timer = 30;
	
	@EventHandler
	public void join(PlayerJoinEvent e){
		//LORSQUE UN JOUEUR REJOIN LA GAME
		Player p = e.getPlayer();
		UHCPvP.kills.put(p, 0);
		new Sounds(p).playSound(Sound.CLICK);
			
		//SI LE JEU EST EN MODE ATTENTE
		if(UHCState.isState(UHCState.WAIT)){
			//ON RECUPERE LE JOUEUR QUI SOUHAITE REJOINDRE LE JEU

			//ON VERIFIE QUE LE JOUEUR NEST PAS DANS LA LISTE
			if(!UHCRun.getInstance().playerInGame.contains(p.getUniqueId())){
				
				//SI IL NEST PAS DANS LA LISTE ON VA LAJOUTER
				UHCRun.getInstance().playerInGame.add(p.getUniqueId());
							
				new CustomScoreboardManager(p);
				ScoreboardRunnable.sendLine();
				p.setScoreboard(CustomScoreboardManager.getScoreboard(p).getMainScoreboard());
					
				//ON VA CHECK SI LE NOMBRE DE JOUEUR EST EGAL A 1
				if(UHCRun.getInstance().playerInGame.size() == 1){
					//ON START LE CHRONOMETRE

					
					task = Bukkit.getScheduler().scheduleSyncRepeatingTask(UHCRun.getInstance(), new Runnable(){

						@Override
						public void run() {
							
							//ON ENLEVE 1 A CHAQUE SECONDE POUR QUE LE CHRONO PUISSENT BAISSER
							timer--;	
							setLevel(timer);
							
							if(timer == 30 || timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
								
								for(UUID uuid : UHCRun.getInstance().playerInGame){
									Player pl = Bukkit.getPlayer(uuid);
									Title.sendTitle(pl, "UHCRun", "Lancement du Jeu dans "+timer+" s", 20);
									new Sounds(pl).playSound(Sound.NOTE_PLING);
								}
					
								
							}
							
					
							
							if(timer == 0){
								
								for(UUID uuid : UHCRun.getInstance().playerInGame){
									Player pl = Bukkit.getPlayer(uuid);
									Title.sendTitle(pl, "UHCRun", "Lancement du Jeu !", 20);
									new Sounds(pl).playSound(Sound.ENDERDRAGON_DEATH);
								}
								
								//ON ARRETE LE CHRONO
								Bukkit.getScheduler().cancelTask(task);
								//ON LANCE LA GAME
								UHCGame.start();
							}
							
						}
						
					},20,20);
					
				}
				
				
			}
		}else{
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("Le jeu a commencé vous etes desormais un spectateur");
		}
		
	}
	
	//ON VA METTRE DES NIVEAUX DANS LA BAR DXP DES JOUEURS DE LA GAME EN FONCTION DU TIMER
	public void setLevel(int timer){
		//ON RECUP L UUID DES JOUEURS DE LA GAME
		for(UUID uuid : UHCRun.getInstance().playerInGame){
			Player pl = Bukkit.getPlayer(uuid);
			pl.setLevel(timer);
		}
	}

	@EventHandler
	public void quit(PlayerQuitEvent e){
		
		//ON RECUP LE JOUEUR QUI SEST DECO
		Player p = e.getPlayer();
		UHCRun.getInstance().playerInGame.remove(p.getUniqueId());
		
		//ON VIRE LE JOUEUR DE LA MAP CUSTOMSCOREBOARD
		CustomScoreboardManager.sb.remove(p);
	}

}