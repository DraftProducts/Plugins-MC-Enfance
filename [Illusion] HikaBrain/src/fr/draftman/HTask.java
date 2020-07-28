package fr.draftman;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.draftman.Utils.ChatUtils;
import fr.draftman.Utils.TitleManager;
import fr.draftman.scoreboard.ScoreboardManager;

public class HTask extends BukkitRunnable {
	
	private HikaBrain main;
	public static int timer = 10;

	public HTask(HikaBrain main) { this.main = main; }
	
	@Override
	public void run() {
		sendXP();
		for(Player pls : Bukkit.getOnlinePlayers()){

			if(Bukkit.getOnlinePlayers().size() < main.getConfig().getInt("autostart")){
				sendXP();
	        	TitleManager.sendTitle(pls, "", "§cPas assez de joueurs", 10);
	        	cancel();
	    		main.setState(HState.WAITING);
	        }
	        if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
	            TitleManager.sendTitle(pls, "", "§c"+timer+"s", 20);
	        }
	        if (timer == 0) {
	            TitleManager.sendTitle(pls, "§aLancement du jeu !", "§6Bonne Chance :D", 10);
	            ScoreboardManager.loadScoreboardGame(pls);
			}
	        
		}
		
		if(Bukkit.getOnlinePlayers().size() < main.getConfig().getInt("autostart")){
    		cancel();
    		sendXP();
    		main.setState(HState.WAITING);
    		Bukkit.broadcastMessage(ChatUtils.getGamePrefix() +" §cPas assez de joueurs");
    		
    	}
		
        if (timer == 5 || timer == 4 || timer == 3 || timer == 2|| timer == 1) {
            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " Lancement dans " + timer + getSecond()+" !"));
        }
        if (timer == 0) {
        	cancel();
            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " Lancement du jeu !"));
            Bukkit.broadcastMessage((String)(String.valueOf(ChatUtils.getGamePrefix()) + " Bonne Chance :D"));
            main.loadTeams();
			main.reset();
			main.setState(HState.GAME);
		}
        timer--;
		
	}
	public void sendXP() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setLevel(timer);
			if(ScoreboardManager.scoreboardGame.containsKey(player)) {
                ScoreboardManager.scoreboardGame.get(player).setLine(3, ChatColor.GRAY + "Début dans: " + ChatColor.GREEN + new SimpleDateFormat("ss").format(new Date(HTask.timer * 1000)));
            }
		}
	}
	private String getSecond(){
        return timer == 1 ? " seconde" : " secondes";
    }
}
