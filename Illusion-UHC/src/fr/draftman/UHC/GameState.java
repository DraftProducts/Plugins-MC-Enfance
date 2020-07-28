/*
 * Decompiled with CFR 0_110.
 */
package fr.draftman.UHC;

public enum GameState {
    LOBBY(true),
    PREPVP(false),
    GAME(false),
    FINISH(false);
    
    private boolean canJoin;
    private static GameState correntState;

    private GameState(boolean canJoin) {
        this.canJoin = canJoin;
    }

    public boolean canJoin() {
        return this.canJoin;
    }

    public static void setState(GameState states) {
        correntState = states;
    }

    public static boolean inStates(GameState states) {
        if (correntState == states) {
            return true;
        }
        return false;
    }

    public static GameState getState() {
        return correntState;
    }
}

