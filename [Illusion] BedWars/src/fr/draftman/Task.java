package fr.draftman;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Task  extends BukkitRunnable {
	
	private Main main;
	public static int timer = 10;

	public Task(Main main) { this.main = main; }
	
	@Override
	public void run() {
		sendXP();
		for(Player pls : Bukkit.getOnlinePlayers()){

			if(Bukkit.getOnlinePlayers().size() < main.getConfig().getInt("autostart")){
				sendXP();
	        	main.sendTitle(pls, "", "§cPas assez de joueurs", 10);
	        	cancel();
	    		main.setState(GameState.WAITING);
	        }
	        if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
	            main.sendTitle(pls, "", "§c"+timer+"s", 20);
	        }
	        if (timer == 0) {
	            main.sendTitle(pls, "§aLancement du jeu !", "§6Bonne Chance :D", 10);
	            ScoreboardManager.loadScoreboardGame(pls);
			}
	        
		}
		
		if(Bukkit.getOnlinePlayers().size() < main.getConfig().getInt("autostart")){
    		cancel();
    		sendXP();
    		main.setState(GameState.WAITING);
    		Bukkit.broadcastMessage(main.getGamePrefix() +" §cPas assez de joueurs");
    		
    	}
		
        if (timer == 5 || timer == 4 || timer == 3 || timer == 2|| timer == 1) {
            Bukkit.broadcastMessage((String)(String.valueOf(main.getGamePrefix()) + " Lancement dans " + timer + getSecond()+" !"));
        }
        if (timer == 0) {
        	cancel();
            Bukkit.broadcastMessage((String)(String.valueOf(main.getGamePrefix()) + " Lancement du jeu !"));
            Bukkit.broadcastMessage((String)(String.valueOf(main.getGamePrefix()) + " Bonne Chance :D"));
            main.loadTeams();
			main.setState(GameState.GAME);
		}
        timer--;
		
	}
	public void sendXP() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setLevel(timer);
			if(ScoreboardManager.scoreboardGame.containsKey(player)) {
                ScoreboardManager.scoreboardGame.get(player).setLine(3, ChatColor.GRAY + "Début dans: " + ChatColor.GREEN + new SimpleDateFormat("ss").format(new Date(Task.timer * 1000)));
            }
		}
	}
	private String getSecond(){
        return timer == 1 ? " seconde" : " secondes";
    }
}
