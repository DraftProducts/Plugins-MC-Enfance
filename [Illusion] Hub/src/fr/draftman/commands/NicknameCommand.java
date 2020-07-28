package fr.draftman.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.draftman.Illusion;

public class NicknameCommand implements CommandExecutor {
	
	public NicknameCommand(Illusion pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("nick")){
			
			if(sender instanceof Player){
				Player p = (Player)sender;
				
				if (p.hasPermission("perm.nick") || p.isOp()) {
				
					if(args.length == 0){
						p.sendMessage(ChatColor.RED + "Tu n'as pas choisi de pseudo!");
						return true;
					}
					
					String nick = "";
                    for (String arg : args) {
                        nick += arg + " ";
                    }
                    nick = nick.substring(0, nick.length() - 1);
	
                    nick = ChatColor.translateAlternateColorCodes('&' , nick);
	
					p.sendMessage(ChatColor.GREEN + "Tu as changé ton Pseudo en " + nick);
					Illusion.getInstance().getConfig().set(p.getName(), nick);
					Illusion.getInstance().saveConfig();
					
				} else {
					p.sendMessage("§4Vous devez être OP!");
					return false;
				}
			
			}else {
				sender.sendMessage("Que des joueurs peuvent changer leur pseudo !");
				return false;
			}
		
		}
		return false;
			
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
	
			if (Illusion.getInstance().getConfig().getString(e.getPlayer().getName()) != null) {
	        	e.getPlayer().setDisplayName(Illusion.getInstance().getConfig().getString(e.getPlayer().getName()) + ChatColor.RESET);
	        }
			
		}
	
	}
