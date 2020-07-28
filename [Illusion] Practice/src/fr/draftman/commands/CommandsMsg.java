package fr.draftman.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.Practice;
import fr.draftman.Utils.ChatUtils;
import fr.draftman.events.GiveItems;
import fr.draftman.events.QueueManager;
import fr.neutronstars.message.BukkitMessageBuilder;

public class CommandsMsg implements CommandExecutor {

	public CommandsMsg(Practice pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		if(command.getName().equalsIgnoreCase("unranked")){
			if(!(sender instanceof Player)){
	            sender.sendMessage("Tu dois Ãªtre un Joueur !");
	            return true;
	        }
			Player p = (Player) sender;
			if(args.length == 0){
				p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked join <Type>");
				p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked leave <Type>");
				return true;
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("join")){
					p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked join <Type>");
				}else if(args[0].equalsIgnoreCase("leave")){
					p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked leave <Type>");
				}else{
					p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked join <Type>");
					p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked leave <Type>");
				}
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("join")){
					if(args[1].equalsIgnoreCase("builduhc")){
						if(!QueueManager.unrankedBuildUHC.contains(p)){
							QueueManager.addUnrankedBuildUHC(p);
							p.sendMessage("\n"+ ChatUtils.getGamePrefix() +"§7Tu as bien Ã©tÃ© ajoutÃ© a la queue de §6Unranked BuildUHC");
							
							GiveItems.ItemsQueue(p);
							
							if(QueueManager.getUnrankedBuildUHCSize() >= 2){
								
								Player t = QueueManager.unrankedBuildUHC.get(0);
								
								QueueManager.removeUnrankedBuildUHC(p);
								QueueManager.removeUnrankedBuildUHC(t);
								
								QueueManager.addFightingPlayer(p, t);
								
								try {
									CommandsKit.getKit(p, "BuildUHC");
									CommandsKit.getKit(t, "BuildUHC");
								} catch (IOException e1) {
									e1.printStackTrace();
								}

								p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§7Tu est maintenant en unranked avec §a"+t.getName()+" §7en §6BuildUHC §7!");
								t.sendMessage("\n"+ChatUtils.getGamePrefix() +"§7Tu est maintenant en unranked avec §a"+p.getName()+" §7en §6BuildUHC §7!");
								
								Practice.combat.put(p, t);
								Practice.combat.put(t, p);
								
							}else{
								new BukkitMessageBuilder("\n"+ChatUtils.getGamePrefix() +"§7Tu es tout seul dans la queue du §6Unranked §6BuildUHC\n")
				                	.nextln("                      §4[§cQuitter§4]§r").click(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/queue leave builduhc").setHover("Quitter le Unranked BuildUHC ?")
				                	.send(p);
							}
						}else{
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§7Tu es déja dans la queue du §6Unranked BuildUHC");
						}
					}else{
						p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked join <type>");
					}
				}else if(args[0].equalsIgnoreCase("leave")){
					if(args[1].equalsIgnoreCase("builduhc")){
						if(QueueManager.unrankedBuildUHC.contains(p)){
							QueueManager.removeUnrankedBuildUHC(p);
							p.getInventory().clear();
							GiveItems.ItemsSpawn(p);
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§7Tu as quittÃ© la queue du §6Unranked BuildUHC");
						}else{
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§7Tu n'est pas dans la queue §6Unranked BuildUHC §7!");
						}
					}
				}else{
					p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked join <Type>");
					p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /unranked leave <Type>");
				}
				
			}
		}
		return false;
	}

}
//leave diamond