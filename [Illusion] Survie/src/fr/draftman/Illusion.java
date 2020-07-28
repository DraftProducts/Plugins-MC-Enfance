package fr.draftman;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import fr.draftman.commands.CommandsManager;
import fr.draftman.events.EventsManager;

public class Illusion extends JavaPlugin implements Listener{
	
	public static Illusion instance;
	

	
	public String prefix = "§7[§6Survie§7] ";
	
	private GameState current;
	
	public static Illusion getInstance(){
		
	return instance;
	}
	 
	public void onEnable(){
		System.out.println("Illusion Officiel > ACTIVE");
		
		instance = this;
		
		setState(GameState.WAITING);
		
		CommandsManager.registerCommands(this);
		
		EventsManager.registerEvents(this);
	
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		@SuppressWarnings("deprecation")
		ShapedRecipe mouton = new ShapedRecipe(new ItemStack(Material.MONSTER_EGG, 1, EntityType.SHEEP.getTypeId()));
		mouton.shape("LLL");
		mouton.setIngredient('L', Material.WOOL);
		Bukkit.addRecipe(mouton);
		
		@SuppressWarnings("deprecation")
		ShapedRecipe vache = new ShapedRecipe(new ItemStack(Material.MONSTER_EGG, 1, EntityType.COW.getTypeId()));
		vache.shape("LL");
		vache.setIngredient('L', Material.MILK_BUCKET);
		Bukkit.addRecipe(vache);
		
		@SuppressWarnings("deprecation")
		ShapedRecipe creeper = new ShapedRecipe(new ItemStack(Material.MONSTER_EGG, 1, EntityType.CREEPER.getTypeId()));
		creeper.shape(new String[] {"LLL","LLL","LLL"});
		creeper.setIngredient('L', Material.SULPHUR);
		Bukkit.addRecipe(creeper);
	}
	
	public void setState(GameState state){
		this.current = state;
	}
	
	public boolean isState(GameState state){
		return current == state;
	}
	
	public void onDisable(){
		
		System.out.println("Illusion Officiel > DESACTIVE");
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

    public String getGamePrefix() {
        return prefix;
    }

}
