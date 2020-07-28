package fr.draftman.commands;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.Illusion;
import fr.neutronstars.message.BukkitMessageBuilder;

public class MsgCommand implements CommandExecutor {

	public MsgCommand(Illusion pl) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		
		if(!(sender instanceof Player)){
            sender.sendMessage("Tu dois être un Joueur !");
            return true;
        }
        
        if(args.length < 2){
        	sender.sendMessage("\n"+Illusion.getInstance().getGamePrefix() +"§cUsage Incorect : /m {Pseudo} [MESSAGE]");
            return true;
        }
        
        
        
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sender.sendMessage("§6"+args[0]+" §cn'est pas connecté !");
            return true;
        }
        if(target == sender){
        	sender.sendMessage("\n"+Illusion.getInstance().getGamePrefix() + "§cError : Tu ne peux pas t'envoyer un message !");
        	return true;
        }
        
        if(args.length > 1){
        	String msg = StringUtils.join(ArrayUtils.remove(args, 0), " ");
        	new BukkitMessageBuilder("§5[§d"+sender.getName()+" §3-> §6"+target.getName()+"§5] ")
        		.next("§7[§cR§7]§r").click(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/m "+sender.getName()+" ").setHover("Répondre ?")
        		.next(" "+ msg)
                .send((Player) target);
        	
        	sender.sendMessage("§5[§d"+sender.getName()+" §3-> §6"+target.getName()+"§5]§r "+ msg);
        	
            return true;
        }
		
		return false;

	}
}
