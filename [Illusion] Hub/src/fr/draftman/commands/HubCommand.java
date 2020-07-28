package fr.draftman.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.draftman.Illusion;

public class HubCommand implements CommandExecutor {
	
	private FileConfiguration config;
	private Illusion pl;
	
	public HubCommand(Illusion main) {
		this.pl = main;
		this.config = pl.getConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("spawn") || label.equalsIgnoreCase("hub")){
			
			if(sender instanceof Player){
				Player p = (Player)sender;
				
				if (p.hasPermission("perm.spawn") || p.isOp()) {
					if(args.length == 1){
						
						if(args[0].equalsIgnoreCase("help")){
							p.sendMessage("§4---------- §6[§4GM§6] �8Help §4----------");
							p.sendMessage("§e/spawn §f- Te permet de te téléporter au Spawn");
							p.sendMessage("§e/spawn §3<Rush> §f- Te permet de te téléporter au Spawn Rush");
							p.sendMessage("§e/spawn §3<Tower> §f- Te permet de te téléporter au Spawn Tower");
							p.sendMessage("§4-----------------------------------------");
						}
						if(args[0].equalsIgnoreCase("set")){
							p.sendMessage("§eVous venez de changer le point de spawn");
							
							config.set("locations.spawn.x", p.getLocation().getX());
							config.set("locations.spawn.y", p.getLocation().getY());
							config.set("locations.spawn.z", p.getLocation().getZ());
							config.set("locations.spawn.worldName", p.getWorld().getName());
							pl.saveConfig();
						}
						if(args[0].equalsIgnoreCase("rush")){
							p.teleport(new Location(p.getWorld(), 4.500, 192, -128.500));
							p.sendMessage("§6[§4SPAWN§6] §fTu es maintenant au spawn Rush");
						}
						if(args[0].equalsIgnoreCase("tower")){
							p.teleport(new Location(p.getWorld(), 4.500, 192, -128.500));
							p.sendMessage("§6[§4SPAWN§6] §fTu es maintenant au spawn Tower");
						}
						
					}
					
					
				
				}
				
			}
			//p.teleport(new Location(p.getWorld(), 308.5, 78, 5068.5, 0, 0));
			//p.sendMessage("§6[§4SPAWN§6] §fTu es maintenant au spawn");
		}
			
		return false;
	}

}
