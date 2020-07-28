package fr.draftman.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.Illusion;

public class PrankCommand implements CommandExecutor {

	public PrankCommand(Illusion pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("prank")){
			
			if(sender instanceof Player){
				Player p = (Player)sender;
				
				if (p.hasPermission("perm.prank") || p.isOp()) {
				
					if(args.length == 0){
						p.sendMessage("§4---------- §6[§4PRANK§6] §4----------");
						p.sendMessage("§6Cette commande vas te permettre de faire des pranks aux joueurs !");
						p.sendMessage("§e/prank op §f- Te permet d'envoyer un message du type :");
						p.sendMessage("§6You are now Op");
						p.sendMessage("§4-----------------------------------------");

					}
					
					if(args.length == 1){
	
						if(args[0].equalsIgnoreCase("op")){
							if(args.length == 0){
								p.sendMessage("§4Tu n'as pas choisi de joueur !");
								return true;
							}
							Player target = Bukkit.getServer().getPlayer(args[0]);
							if(target == null){
								p.sendMessage("§4Joueur " + args[0] + " §4non trouvé !");
								return true;
							}
							target.sendMessage("§6You are now Op");
							p.sendMessage("§2Success !");
						}
						
					}
				
				}
				
			}
			
		}
			
		return false;
	}

}
