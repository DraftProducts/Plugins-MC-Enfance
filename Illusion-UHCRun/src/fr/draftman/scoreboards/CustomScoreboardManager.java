package fr.draftman.scoreboards;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;

import fr.draftman.UHCRun;
import fr.draftman.events.UHCJoin;
import fr.draftman.game.UHCGame;
import fr.draftman.game.UHCPvP;
import fr.draftman.game.UHCState;
import fr.draftman.util.UHCBordures;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class CustomScoreboardManager implements ScoreboardManager{
	
	public Player player;
	public Scoreboard scoreboard;
	public Objective objective;
	public static HashMap<Player, CustomScoreboardManager> sb = new HashMap<>();
	
	private String name = "uhcrun";
	
	public CustomScoreboardManager(Player p){
		
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.player = p;
		
		//SI IL EST DANS LA MAP ON NE FAIT RIEN
		if(sb.containsKey(p)) return;
		
		//SINON SI IL NA PAS DE SCOREBOARD ON LUI EN CREER MAIS CUSTOMISER
		sb.put(p, this);
		
		Random r = new Random();
		
		this.name = "sb."+r.nextInt(1000000);
		
		objective = scoreboard.registerNewObjective(name, "dummy");
		objective = scoreboard.getObjective(name);
		objective.setDisplayName("§eUHCRun");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
	}

	//ON MET UN JOUR UNIQUEMENT LES ELEMENTS DYNAMIQUE
	public void refresh(){
		
		if(UHCState.isState(UHCState.WAIT)){
			
			int players = UHCRun.getInstance().playerInGame.size() - 1;
			int timer = UHCJoin.timer - 1;
			
			objective.getScoreboard().resetScores(""+players);
			objective.getScore(""+UHCRun.getInstance().playerInGame.size()).setScore(9);

			objective.getScoreboard().resetScores("§7Lancement dans: ");
			objective.getScore("§7Lancement dans: ").setScore(7);
			
			objective.getScoreboard().resetScores(""+UHCJoin.timer);
			objective.getScore(""+timer).setScore(6);
			
			
		}else{
			
			int kills = UHCPvP.kills.get(this.player); 
			int lastkills = kills - 1;
			int players = UHCRun.getInstance().playerInGame.size() - 1;
			
			int timer = 1200 - UHCGame.timer;
			int lastTimer = timer + 1;
			String lastS = new SimpleDateFormat("mm:ss").format(lastTimer * 1000);
			String S = new SimpleDateFormat("mm:ss").format(timer * 1000);
			
			double lastD = UHCBordures.getBorder() + 1;
			double D = UHCBordures.getBorder();
			
			objective.getScoreboard().resetScores("§7Lancement dans: ");
			
			objective.getScoreboard().resetScores(""+players);
			objective.getScore(""+UHCRun.getInstance().playerInGame.size()).setScore(9);
			objective.getScoreboard().resetScores(""+lastkills);
			
			objective.getScoreboard().resetScores("§7PvP dans: ");
			objective.getScore("§7PvP dans: ").setScore(7);
			
			objective.getScoreboard().resetScores(lastS);
			objective.getScore(S).setScore(6);
			
			objective.getScoreboard().resetScores("§7Bordures: ");
			objective.getScore("§7Bordures: ").setScore(4);
			
			objective.getScoreboard().resetScores(lastD+"");
			objective.getScore(D+"").setScore(3);


		
		}


	}
	
	//ON PLACE LES ELEMENTS QUI NE VONT PAS BOUGER
	public void getLine(){
		
		objective.getScore("§8 ").setScore(11);
		objective.getScore("§7Joueurs: ").setScore(10);
		objective.getScore("§e ").setScore(8);
		objective.getScore("§a ").setScore(5);
		objective.getScore("§2 ").setScore(2);
		objective.getScore("§eFarugames.fr").setScore(0);
		
	}
	
	//ON RECUP LE SCOREBOARD CUSTOM DU JOUEUR
	public static CustomScoreboardManager getScoreboard(Player p){
		return sb.get(p);
	}
	
	@Override
	public Scoreboard getMainScoreboard() {
		return scoreboard;
	}

	@Override
	public Scoreboard getNewScoreboard() {
		return null;
	}

}