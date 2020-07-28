package fr.draftman;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		
		Player p = (Player) sender; 
		
		return false;
	}
	
	public void inventory(Player p){
		Inventory inv = Bukkit.createInventory(null, 9*5, "§8Jeux");
	}
	
	void ServeursListByType(Player p, String type) {
		
		Inventory inv = Bukkit.createInventory(null, 54, "§cServeurs: §3"+type);
		
		for(String s : Main.getServeursByType(type)) {
			inv.addItem(new Ite(Material.STAINED_CLAY, Integer.parseInt(s.substring(s.length() - 2)), 0, "§f"+type+s, null));
		}
		
		p.openInventory(inv);
	}

}
