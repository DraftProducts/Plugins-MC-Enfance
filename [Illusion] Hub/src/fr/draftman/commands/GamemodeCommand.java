package fr.draftman.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.Illusion;

public class GamemodeCommand implements CommandExecutor {

	public GamemodeCommand(Illusion pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("gm")){
			
			if(sender instanceof Player){
				Player p = (Player)sender;
				
				if (p.hasPermission("perm.gm") || p.isOp()) {
				
					if(args.length == 1){
	
						if(args[0].equalsIgnoreCase("help")){
							p.sendMessage("§4----------------- §6[§4GM§6] §8Help §4----------------");
							p.sendMessage("§e/gm §3<Survie | 0> §f- GameMode Survie");
							p.sendMessage("§e/gm §3<Creatif | 1> §f- GameMode Créatif");
							p.sendMessage("§e/gm §3<Aventure | 2> §f- GameMode Aventure");
							p.sendMessage("§e/gm §3<Spectateur | 3> §f- GameMode Spectateur");
							p.sendMessage("§4-----------------------------------------");
							
						}
						if(args[0].equalsIgnoreCase("0")){
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Survie");
						}
						if(args[0].equalsIgnoreCase("1")){
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Créatif");
						}
						if(args[0].equalsIgnoreCase("2")){
							p.setGameMode(GameMode.ADVENTURE);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Aventure");
						}
						if(args[0].equalsIgnoreCase("3")){
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Spectateur");
						}
						if(args[0].equalsIgnoreCase("SURVIE")){
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Survie");
						}
						if(args[0].equalsIgnoreCase("CREATIF")){
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Créatif");
						}
						if(args[0].equalsIgnoreCase("ADVENTURE")){
							p.setGameMode(GameMode.ADVENTURE);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Aventure");
						}
						if(args[0].equalsIgnoreCase("SPECTATOR")){
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage("§6[§4GM§6] §fTu es maintenant en Mode Spectateur");
						}
						
					}
					
				} else {
					p.sendMessage("§4Vous devez être OP!");
					return false;
				}
				
				
			}else {
				sender.sendMessage("Vous devez etre un joueur!");
				return false;
			}
			
		}
		
		return false;
	}

}























