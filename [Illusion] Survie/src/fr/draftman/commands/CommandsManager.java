package fr.draftman.commands;

import fr.draftman.Illusion;

public class CommandsManager {
	public static void registerCommands(Illusion pl) {
		
		TpaCommand tpa = new TpaCommand(pl);
		
		pl.getCommand("nick").setExecutor(new NicknameCommand(pl));
		pl.getCommand("tpa").setExecutor(tpa);
		pl.getCommand("tpyes").setExecutor(tpa);
		pl.getCommand("tpno").setExecutor(tpa);
		pl.getCommand("m").setExecutor(new MsgCommand(pl));
		pl.getCommand("start").setExecutor(new StartCommand(pl));
	}
}