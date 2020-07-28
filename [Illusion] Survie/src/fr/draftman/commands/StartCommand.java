package fr.draftman.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.draftman.GameState;
import fr.draftman.Illusion;

public class StartCommand implements CommandExecutor {

	public StartCommand(Illusion pl) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		
		Player p = (Player) sender;
		
		Player ps = (Player) Bukkit.getOnlinePlayers();
		
		Illusion.getInstance().setState(GameState.GAME);
		
		ps.teleport(new Location(p.getWorld(), 244.5, 76, 241.5, 0, 0));
		
		ps.getInventory().clear();
		
		ps.setFoodLevel(20);
		
		ps.setGameMode(GameMode.SURVIVAL);
		
		Bukkit.broadcastMessage("§6"+p.getName()+" §7viens de lancer la SEP !");
		
		return false;
	}

}
