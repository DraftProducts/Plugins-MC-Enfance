package fr.draftman.game;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.draftman.UHCRun;

public class UHCEndGame {

	public static void checkWin(){
		
		int online = UHCRun.getInstance().playerInGame.size();
		
		if(online == 0){
			//fin du jeu
			stopGame(10);
		}
		
		if(online == 1){
			//fin du jeu
			for(UUID pl : UHCRun.getInstance().playerInGame){
				Player winner = Bukkit.getPlayer(pl);
				Bukkit.broadcastMessage("Le joueur : "+winner.getName()+" a gagné le jeu ! Bravo à lui !");
				stopGame(6);
			}
		}
		
	}
	
	public static void stopGame(int timeUntilStop){
		
		Bukkit.getScheduler().runTaskLater(UHCRun.getInstance(), new Runnable(){

			@Override
			public void run() {
				for(Player pl : Bukkit.getOnlinePlayers()){
					pl.kickPlayer("Game End !");
				}
				Bukkit.shutdown();
			}
			
		},timeUntilStop * 20);
		
	}
	
}
