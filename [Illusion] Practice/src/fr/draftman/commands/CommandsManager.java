package fr.draftman.commands;

import fr.draftman.Practice;

public class CommandsManager {

	public static void registerCommands(Practice pl) {
		
		pl.getCommand("unranked").setExecutor(new CommandsMsg(pl));
		pl.getCommand("kit").setExecutor(new CommandsKit(pl));
		pl.getCommand("duel").setExecutor(new CommandsDuel(pl));
	}

}
