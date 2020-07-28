package fr.draftman;

import org.bukkit.plugin.java.JavaPlugin;

public class JumpParty extends JavaPlugin{
	
public static JumpParty instance;
	
	public static JumpParty getInstance(){
		
	return instance;
	}
	
	@Override
	public void onEnable() {
		
		System.out.println("Jump-Party > ACTIVE");
		
		getCommand("jump").setExecutor(new Commands());
		
		getServer().getPluginManager().registerEvents(new Game(), this);
		
		instance = this;
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	@Override
	public void onDisable() {
		
		System.out.println("Jump-Party > DESACTIVE");
	}

}