/*
 * Decompiled with CFR 0_110.
 */
package fr.draftman.Utils;

public class ChatUtils {
    public static String prefix = "\u00a76[\u00a7eBOMBER\u00a76]";
    public static String infogameprefix = "\u00a77[\u00a7cINFOS\u00a77] \u00a7e>> \u00a7f";
    public static String playerprefix = "\u00a77[\u00a78Joueur\u00a77]";
    public static String spectateurprefix = "\u00a77[\u00a78Spectateur\u00a77]";

    public static String getGamePrefix() {
        return prefix;
    }

    public static String getInfoGamePrefix() {
        return infogameprefix;
    }

    public static String getPlayerPrefix() {
        return playerprefix;
    }

    public static String getSepectateurPrefix() {
        return spectateurprefix;
    }
}

