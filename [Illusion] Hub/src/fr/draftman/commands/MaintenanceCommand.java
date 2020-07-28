package fr.draftman.commands;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.Illusion;

public class MaintenanceCommand implements CommandExecutor {
	
	PreparedStatement sts;

	public MaintenanceCommand(Illusion pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("maintenance")) {
			
			Player p = (Player) sender;

			if(p.hasPermission("perm.maintenance") || p.getPlayer().isOp()) {
			
				if(sender instanceof Player) {
					
					setMaintenance(p);
					
				} else {
					
					sender.sendMessage("Tu n'es pas un joueur, tu n'as donc pas la possibilité de faire celà !");
				}
			}
		}
		
		return false;
	}

	public void setMaintenance(Player p) {
						
		for(Player pls : Bukkit.getOnlinePlayers()) {
					
			pls.kickPlayer("§cUne maintenance a lieu, veuillez nous excuser pour le désagremment causé.");
		}
		return;
	}
}
