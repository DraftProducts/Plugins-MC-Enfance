/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 */
package fr.draftman.UHC.Utils;

import org.bukkit.event.Listener;

import fr.draftman.UHC.Illusion;

public class GameUtils
implements Listener {
    public static boolean isWinner() {
        if (Illusion.players.size() == 1) {
            return true;
        }
        return false;
    }
}

