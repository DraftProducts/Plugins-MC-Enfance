/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.GameMode
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.PlayerDeathEvent
 *  org.bukkit.inventory.EntityEquipment
 *  org.bukkit.inventory.PlayerInventory
 */
package fr.draftman.UHC.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.draftman.UHC.Illusion;
import fr.draftman.UHC.Utils.GameUtils;

public class PlayerDeath
implements Listener {
    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Illusion.players.remove((Object)p);
        p.getInventory().clear();
        p.getEquipment().clear();
        p.setGameMode(GameMode.SPECTATOR);
        if (GameUtils.isWinner()) {
            Illusion.end();
        }
    }
}

