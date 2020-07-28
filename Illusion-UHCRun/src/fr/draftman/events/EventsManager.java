package fr.draftman.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.draftman.UHCRun;

public class EventsManager {

	public static void registerEvents(UHCRun pl) {
		//ON CREER UNE VARIABLE PERMETTANT DE REGISTER LES EVENTS
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new UHCJoin(), pl);
		
	}

}
