package fr.draftman.commands;

import fr.draftman.Illusion;

public class CommandsManager {
	public static void registerCommands(Illusion pl) {
		
		pl.getCommand("gm").setExecutor(new GamemodeCommand(pl));
		pl.getCommand("nick").setExecutor(new NicknameCommand(pl));
		pl.getCommand("maintenance").setExecutor(new MaintenanceCommand(pl));
		pl.getCommand("build").setExecutor(new BuildCommand(pl));
		pl.getCommand("prank").setExecutor(new PrankCommand(pl));
		pl.getCommand("hub").setExecutor(new HubCommand(pl));
	}

}
