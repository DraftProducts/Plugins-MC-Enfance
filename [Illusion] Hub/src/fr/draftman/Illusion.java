package fr.draftman;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.draftman.commands.CommandsManager;
import fr.draftman.events.EventsManager;

public class Illusion extends JavaPlugin implements Listener{
	
	public static Illusion instance;
	
	public static Illusion getInstance(){
		
	return instance;
	}
	 
	public void onEnable(){
		System.out.println("Illusion Officiel > ACTIVE");
		
		instance = this;
		
		CommandsManager.registerCommands(this);
		
		EventsManager.registerEvents(this);
	
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
	public void onDisable(){
		
		System.out.println("Illusion Officiel > DESACTIVE");
	}

}
