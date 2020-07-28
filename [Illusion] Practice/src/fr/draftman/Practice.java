package fr.draftman;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.draftman.commands.CommandsManager;
import fr.draftman.events.PracticeListeners;

public class Practice extends JavaPlugin{
	
public static Practice instance;
	
	public static Practice getInstance(){ return instance; }
	public static HashMap<Player, Player> combat = new HashMap<Player, Player>();
	public static HashMap<Player, Player> duelRequest = new HashMap<Player, Player>();
	
	public void onEnable(){
		System.out.println("Practice Officiel > ACTIVE");
		
		instance = this;
		
		CommandsManager.registerCommands(this);
		getServer().getPluginManager().registerEvents(new PracticeListeners(this), this);
		
		registerConfig();

	}
	
	@Override
	public void onDisable() {
		System.out.println("Practice Officiel > DESACTIVE");
	}
	
	public void registerConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public String centerText(String text) {
		int space = (int) Math.round((80.0D - 1.4D * text.length()) / 2.0D);
		return repeat(" ", space) + text;
	}
	
	private String repeat(String text, int times) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			stringBuilder.append(text);
		}
		return stringBuilder.toString();
	}
}


