package fr.draftman.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.draftman.Illusion;

public class EventsManager {
	
	public static void registerEvents(Illusion pl) {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new lstJoin(), pl);
		pm.registerEvents(new MessageJoin(), pl);
		pm.registerEvents(new JumpPad(), pl);
		pm.registerEvents(new Stacker(), pl);
		pm.registerEvents(new Spawner(), pl);
		pm.registerEvents(new Diamond(), pl);
		pm.registerEvents(new GameGenerate(), pl);
		
	}
}
