package fr.draftman.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.Illusion;
import fr.neutronstars.message.BukkitMessageBuilder;

public class TpaCommand implements CommandExecutor {
	
	public HashMap<Player, Player> tp = new HashMap<>();

	public TpaCommand(Illusion pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		
		if(command.getName().equalsIgnoreCase("tpa")){
			if(!(sender instanceof Player)){
	            sender.sendMessage("Tu dois être un Joueur !");
	            return true;
	        }
	        
	        if(args.length < 1){
	            new BukkitMessageBuilder("§7Spécifie à qui tu veux te téléporter !").send((Player) sender);
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
	        
	        if(args.length == 1){
	            new BukkitMessageBuilder(" \n"+"§7-----------------------------------------------------------\n"
	            		+ "Vous allez demandé à§6 "+target.getDisplayName()+ " §rde se téléporter à lui !"+"\n ")
	            .nextln("             §2[§aValider§2]§r").click(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpa "+target.getName()+" yes").setHover("Valider l'envoie ?")
	            .next("             §4[§cAnnuler§4]§r").click(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpa "+target.getName()+" no").setHover("Annuler l'envoie ?")
	            .nextln("§7-----------------------------------------------------------"+"\n ").send((Player) sender);
			} else if(args.length == 2) {
	            if(args[1].equalsIgnoreCase("yes")){
	            	Player p = (Player) sender;
	                new BukkitMessageBuilder("§7Vous avez demandé à §6" + target.getDisplayName() + " §7de se téléporter a lui !").send((Player) sender);
	                new BukkitMessageBuilder( "\n"+"§7-----------------------------------------------------------\n"
	                	+"§6"+sender.getName()+"§7 demande a se téléporter vers vous !"+"\n ")
	                .nextln("             §2[§aAutoriser§2]§r").click(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpyes").setHover("Autoriser la téléportation ?")
	                .next("             §4[§cRefuser§4]§r").click(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpno").setHover("Refuser la téléportation ?")
	                .nextln("§7-----------------------------------------------------------"+"\n ")
	                .send((Player) target);
	                tp.put(target, p);
	            }else if(args[1].equalsIgnoreCase("no"))
	                new BukkitMessageBuilder("§7Vous avez annulé votre demande de téléportation vers §6" + target.getDisplayName()).send((Player) sender);
	        }
	    }
		if(command.getName().equalsIgnoreCase("tpyes")){
			Player p = (Player) sender;
			if(tp.containsKey(p)){
				Player t = tp.get(p);
				t.teleport(p.getLocation());
				p.sendMessage("§6"+t.getName()+" §7s'est téléporté à vous avec succès !");
				t.sendMessage("§7Vous avez été tétéporté à §6"+t.getName()+" §7avec succès !");
			}
	    	
	    }
		if(command.getName().equalsIgnoreCase("tpno")){
			Player p = (Player) sender;
			if(tp.containsKey(p)){
				Player t = tp.get(p);
				t.sendMessage("§6"+p.getName()+" §7a refusé votre demande de téléportaion ");
				p.sendMessage("§7La demande de Téléportaion de §6"+t.getName()+" §7a été refusé avec succès !");
			}
	    	
	    }
		return false;
	}

}
