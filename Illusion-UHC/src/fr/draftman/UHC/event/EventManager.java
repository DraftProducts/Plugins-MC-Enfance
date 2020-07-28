/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 */
package fr.draftman.UHC.event;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {
    public static void registerEvents(Plugin pl) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new PlayerJoin(), pl);
        pm.registerEvents((Listener)new PlayerQuit(), pl);
        pm.registerEvents((Listener)new PlayerDeath(), pl);
    }
}

