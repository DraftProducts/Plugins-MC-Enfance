package fr.draftman.game;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.draftman.UHCRun;
import fr.draftman.api.WorldSounds;
import fr.draftman.util.UHCSkullRegen;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class UHCPvP implements Listener {
	
	public static HashMap<Player, Integer> kills = new HashMap<>();
	
	@EventHandler
	public void fakeDeath(PlayerDeathEvent e){
		Player p = (Player) e.getEntity();
		fakeDeath(p);
	}

	private void fakeDeath(Player p) {
		
			//LE JOUEUR EST MORT
			respawnInstant(p);
			p.setGameMode(GameMode.SPECTATOR);
			Bukkit.broadcastMessage("§7[§aUHCRun§7] "+p.getName()+" est éliminé !");
			UHCRun.getInstance().playerInGame.remove(p.getUniqueId());
			new WorldSounds(p.getLocation()).playSound(Sound.AMBIENCE_THUNDER);
			UHCSkullRegen.dropSkull(p);
	
	}

	private void respawnInstant (final Player player) {
		  Bukkit. getScheduler (). runTaskLater (UHCRun.getInstance(), new Runnable () {@Override
		   public void run() {
		    PacketPlayInClientCommand paquet = new PacketPlayInClientCommand (EnumClientCommand. PERFORM_RESPAWN);
		    ((CraftPlayer) player). getHandle (). playerConnection. a (paquet);
		   }
		  }, 5L);
		 }

	
	@EventHandler
	public void fakeDamageDeath(EntityDamageEvent e){
		
		if(UHCState.isState(UHCState.WAIT) || UHCState.isState(UHCState.PREGAME)){
			e.setCancelled(true);
		}
		
		
	}

}