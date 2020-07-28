package fr.draftman.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.Practice;
import fr.draftman.Utils.ChatUtils;
import fr.draftman.events.GUI;

public class CommandsDuel implements CommandExecutor {

	public CommandsDuel(Practice pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		if(sender instanceof Player){
			sender.sendMessage("Tu dois être un Joueur !");
            return true;
        }
		Player p = (Player) sender;
		if(args.length == 0){
			p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /duel {PLAYER}");
			return true;
		}else{
			String t = args[0];
			if(Bukkit.getPlayer(t) != null){
				Player ta = Bukkit.getPlayer(t);
				if(ta == p){
					p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /duel {PLAYER}");
					return true;
				}
				Practice.duelRequest.put(p, ta);
				GUI.DuelGUI(p);
			}else{
				p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§c"+t+" n'est pas connecté !");
			}
		}
		return false;
	}

}
