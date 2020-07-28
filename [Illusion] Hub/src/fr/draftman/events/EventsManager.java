package fr.draftman.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.draftman.Illusion;
import fr.draftman.commands.BuildCommand;
import fr.draftman.menu.Menu;
import fr.draftman.scoreboard.ScoreboardManager;

public class EventsManager {
	
	public static void registerEvents(Illusion pl) {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new lstWorld(), pl);
		pm.registerEvents(new lstJoin(), pl);
		pm.registerEvents(new Menu(), pl);
		pm.registerEvents(new DoubleJump(), pl);
		pm.registerEvents(new Jump(), pl);
		pm.registerEvents(new MessageJoin(), pl);
		pm.registerEvents(new JumpPad(), pl);
		pm.registerEvents(new BuildCommand(), pl);
		pm.registerEvents(new ScoreboardManager(), pl);
		pm.registerEvents(new Stacker(), pl);
		
		
	}
}
