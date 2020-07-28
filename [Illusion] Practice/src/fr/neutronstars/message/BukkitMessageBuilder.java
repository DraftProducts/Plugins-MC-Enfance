package fr.neutronstars.message;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;

public final class BukkitMessageBuilder extends AbstractMessageBuilder<BukkitMessageBuilder>{

	public BukkitMessageBuilder(AbstractMessageBuilder<?> messageBuilder) {
		super(messageBuilder);
	}
	
	public BukkitMessageBuilder(String text) {
		super(text);
	}

	/**
	 * Send to player
	 * @param player
	 */
	public void send(Player player){
		player.spigot().sendMessage(build());
	}
	
	/**
	 * Send to a player List.
	 * @param players
	 */
	public void send(Collection<? extends Player> players){
		BaseComponent[] bc = build();
		for(Player player : players) player.spigot().sendMessage(bc);
	}
	
	/**
	 * Send to a player List.
	 * @param players
	 */
	public void send(Player... players){
		BaseComponent[] bc = build();
		for(Player player : players) player.spigot().sendMessage(bc);
	}
	
	/**
	 * Send to all players online.
	 * @param players
	 */
	public void sendAll(){
		send(Bukkit.getOnlinePlayers());
	}
	
	/**
	 * Create a new instance of the class.
	 * @return this
	 */
	public BukkitMessageBuilder clone(){
		return new BukkitMessageBuilder(this);
	}
}
