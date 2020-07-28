package fr.draftman.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.draftman.Practice;
import fr.draftman.Utils.ChatUtils;

public class CommandsKit implements CommandExecutor {

	public CommandsKit(Practice pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length == 0){
				sendHelp(p);
			}else if(args.length >= 1){
				if(args[0].equalsIgnoreCase("add")){
					if(args.length == 2){
						try {
							addKit(p, args[1]);
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§aLe Kit §e" + args[1] + " §a a bien été crée !");
						} catch (IOException e) {
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cErreur lors de la création du kit "+ args[1]);
							e.printStackTrace();
						}
					}else{
						p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /kit add [NOM]");
					}
				}else if(args[0].equalsIgnoreCase("remove")){
					if(args.length == 2){
						try {
							removeKit(args[1]);
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§aLe Kit §e" + args[1] + " §a a bien été supprimé !");
						} catch (IOException e) {
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cErreur lors de la suppréssion du kit "+ args[1]);
							e.printStackTrace();
						}
					}else{
						p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /kit remove [NOM]");
					}
				}else if(args[0].equalsIgnoreCase("get")){
					if(args.length == 2){
						try {
							getKit(p, args[1]);
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§aLe Kit §e" + args[1] + " §a a bien été chargé !");
						} catch (IOException e) {
							p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cErreur lors du chargement du kit"+ args[1]);
							e.printStackTrace();
						}
					}else{
						p.sendMessage("\n"+ChatUtils.getGamePrefix() +"§cUsage Incorect : /kit get [NOM]");
					}
				}else{
					sendHelp(p);
				}
			}
		}else{
			sender.sendMessage("Tu dois Ãªtre un joueur !");
		}
		return false;
	}
	
	public void sendHelp(Player p){
		p.sendMessage(""
				+ "\n§7----------------------§2[§9HELP§2]§7---------------------------\n "
				+ "§7/§6kit §ladd\n "
				+ "§7/§6kit §lremove\n "
				+ "§7/§6kit §lget\n"
				+ "§7-----------------------------------------------------------");
	}
	
	public void addKit(Player p, String kit) throws IOException {
        YamlConfiguration c = new YamlConfiguration();
        c.set("inventory.armor", p.getInventory().getArmorContents());
        c.set("inventory.content", p.getInventory().getContents());
        c.set("name", kit);
        c.save(new File(Practice.getInstance().getDataFolder(), kit+".yml"));
    }
    
    @SuppressWarnings("unchecked")
    public static void getKit(Player p, String kit) throws IOException {
        YamlConfiguration c = YamlConfiguration.loadConfiguration(new File(Practice.getInstance().getDataFolder(), kit+".yml"));
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
    }
    public void removeKit(String kit) throws IOException {
    	File file = new File(Practice.getInstance().getDataFolder(), kit+".yml");
    	file.delete();
    }
}
