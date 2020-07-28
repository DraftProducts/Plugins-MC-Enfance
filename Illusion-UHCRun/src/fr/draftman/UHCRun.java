package fr.draftman;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import fr.draftman.events.EventsManager;

public class UHCRun extends JavaPlugin{
	
	public static UHCRun instance;
	
	//IL SAGIT DE LA LISTE DES JOUEURS EN JEU
	public ArrayList<UUID> playerInGame = new ArrayList<>();
	
	//ON RECUPERE L INSTANCE
	public static UHCRun getInstance(){
		return instance;
	}
	
	//LORSQUE LE PLUGIN SALLUME
	public void onEnable(){
		//ON INIALIZE L INSTANCE
		instance = this;
		//ON REGISTER TOUT LES LISTENERS
		EventsManager.registerEvents(this);
	}

}
