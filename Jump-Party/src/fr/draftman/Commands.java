package fr.draftman;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("jump")){
			
			if(sender instanceof Player){
				Player p = (Player)sender;
				
				String FelicitationMsg = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("FelicitationMsg").replace("<player>", p.getName()));
				
				String Title = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("Title.Title"));
				
				String SubTitle = ChatColor.translateAlternateColorCodes('&',JumpParty.getInstance().getConfig().getString("Title.SubTitle"));
				
				if (p.hasPermission("jump.admin") || p.isOp()) {
					
					if(args.length == 0){
						p.sendMessage("§4----------------- §6[§4JUMP§6] §4----------------");
						p.sendMessage("§e/jump §3info | 1> §f- Donne des informations sur le JumpParty.getInstance() jump");
						p.sendMessage("§e/jump §3join §f- Rejoindre le jump");
						p.sendMessage("§e/jump §3start §f- Lance le jump");
						p.sendMessage("§e/jump §3stop §f- Stop le jump");
						p.sendMessage("§e/jump §3setwinmessage §f- Change le message de victoire");
						p.sendMessage("§e/jump §3setwintitle §f- Change le title de victoire");
						p.sendMessage("§e/jump §3addholographique §f- Positionne un tableau des 10 meilleurs scores");
						p.sendMessage("§e/jump §3delholographique §f- Supprime un tableau des 10 meilleurs scores");
						p.sendMessage("§e/jump §3setspawn §f- Met le sapwn des joueurs qui ont finit/quitté le jump ");
						p.sendMessage("§e/jump §3setspawnjoin §f- Met le sapwn des joueurs qui rejoigne le jump");
						p.sendMessage("§e/jump §3setquitmessage §f- Met le message des joueurs qui quittent le jump");
						p.sendMessage("§e/jump §3setreturnmessage §f- Met le message des joueurs qui retournent au checkpoint");
						p.sendMessage("§e/jump §3setnocheckpointmessage §f- Met le message qu'il n'y a pas de checkpoint");
						p.sendMessage("§e/jump §3setcheckpointmessage §f- Met le message de checkpoint");
						p.sendMessage("§4-----------------------------------------");
					}
				
					if(args.length == 1){
	
						if(args[0].equalsIgnoreCase("join")){
							double x = JumpParty.getInstance().getConfig().getDouble("SpawnJump.x");
				            double y = JumpParty.getInstance().getConfig().getDouble("SpawnJump.y");
				            double z = JumpParty.getInstance().getConfig().getDouble("SpawnJump.z");
				            String monde = JumpParty.getInstance().getConfig().getString("SpawnJump.worldName");
				            World world = Bukkit.getWorld(monde);
				            p.teleport(new Location(world,x,y,z));
				            p.sendMessage("§6[§4JUMP§6] §2Vous venez de rejoindre le jump !");
						}
						if(args[0].equalsIgnoreCase("stop")){
							double x = JumpParty.getInstance().getConfig().getDouble("Spawn.x");
				            double y = JumpParty.getInstance().getConfig().getDouble("Spawn.y");
				            double z = JumpParty.getInstance().getConfig().getDouble("Spawn.z");
				            String monde = JumpParty.getInstance().getConfig().getString("Spawn.worldName");
				            World world = Bukkit.getWorld(monde);
				            p.teleport(new Location(world,x,y,z));
				            PlayerUtils.sendTitle(p, Title, SubTitle);
							Bukkit.broadcastMessage(FelicitationMsg);
							
						}
						if(args[0].equalsIgnoreCase("info")){
							p.sendMessage("§eName: §3Jump-Party");
							p.sendMessage("§eAuthor: §3DraftMan");
							p.sendMessage("§eVersion: §31.0");
							p.sendMessage("§eOther Plugins : §3");
						}
						if(args[0].equalsIgnoreCase("setwintitle")){ 
							if(args.length < 2) return true;
							StringBuilder sb = new StringBuilder();
							for(int i = 1; i < args.length; i++){
							    if(sb.length() != 0) sb.append(" ");
							    sb.append(args[i]);
							}
							String[] args2 = sb.toString().split("-n");
							String title = args2[0];
							String subTitle = args2.length > 1 ? args2[1] : null;

							JumpParty.getInstance().getConfig().set("Title.Title", title);
							if(subTitle != null) JumpParty.getInstance().getConfig().set("Title.SubTitle", subTitle);
							p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le title de fin du jump !");
						}
						if(args[0].equalsIgnoreCase("setwinmessage")){
							String winmsg = "";
				            for (String arg : args) {
				            	winmsg += arg + " ";
				            }
							JumpParty.getInstance().getConfig().set("FelicitationMsg", winmsg); 
							JumpParty.getInstance().saveConfig();
							p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le message de fin du jump !");
						}
						if(args[0].equalsIgnoreCase("start")){
							p.sendMessage("§6[§4JUMP§6] §2Vous venez de lancer le jump !");
						}
						if(args[0].equalsIgnoreCase("setspawn")){
			               
			               JumpParty.getInstance().getConfig().set("Spawn.x", p.getLocation().getX());
			               JumpParty.getInstance().getConfig().set("Spawn.y", p.getLocation().getY());
			               JumpParty.getInstance().getConfig().set("Spawn.z", p.getLocation().getZ());
			               JumpParty.getInstance().getConfig().set("Spawn.worldName", p.getWorld().getName());
			               JumpParty.getInstance().saveConfig();
			               p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le spawn de votre serveur pour le jump !");
			               
			            }
						if(args[0].equalsIgnoreCase("setspawnjoin")){
				               
			               JumpParty.getInstance().getConfig().set("SpawnJoin.x", p.getLocation().getX());
			               JumpParty.getInstance().getConfig().set("SpawnJoin.y", p.getLocation().getY());
			               JumpParty.getInstance().getConfig().set("SpawnJoin.z", p.getLocation().getZ());
			               JumpParty.getInstance().getConfig().set("SpawnJoin.worldName", p.getWorld().getName());
			               JumpParty.getInstance().saveConfig();
			               p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le spawn du début du jump !");
			               
			            }
						if(args[0].equalsIgnoreCase("setquitmessage")){
				           
							String quitmsg = "";
				            for (String arg : args) {
				            	quitmsg += arg + " ";
				            }
							JumpParty.getInstance().getConfig().set("QuitJump", quitmsg); 
							JumpParty.getInstance().saveConfig();
							p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le message de quit du jump !");
			               
			            }
						if(args[0].equalsIgnoreCase("setreturnmessage")){
					           
							String ReturnCheckPoint = "";
				            for (String arg : args) {
				            	ReturnCheckPoint += arg + " ";
				            }
							JumpParty.getInstance().getConfig().set("ReturnCheckPoint", ReturnCheckPoint);
							JumpParty.getInstance().saveConfig();
							p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le message de retour au CheckPoint !");
			               
			            }
						if(args[0].equalsIgnoreCase("setnocheckpointmessage")){
					           
							String NoCheckPoint = "";
				            for (String arg : args) {
				            	NoCheckPoint += arg + " ";
				            }
							JumpParty.getInstance().getConfig().set("NoCheckPoint", NoCheckPoint);
							JumpParty.getInstance().saveConfig();
							p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le message de non CheckPoint !");
			               
			            }
						if(args[0].equalsIgnoreCase("setcheckpointmessage")){
					           
							String CheckPointSet = "";
				            for (String arg : args) {
				            	CheckPointSet += arg + " ";
				            }
							JumpParty.getInstance().getConfig().set("CheckPointSet", CheckPointSet);
							JumpParty.getInstance().saveConfig();
							p.sendMessage("§6[§4JUMP§6] §2Vous venez de changer le message de Positionnement de CheckPoint !");
			               
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