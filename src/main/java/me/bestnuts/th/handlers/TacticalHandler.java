package me.bestnuts.th.handlers;

public class TacticalHandler {

    private final EntityHandler entityHandler;
    private final PlayerHandler playerHandler;

    public TacticalHandler() {
        entityHandler = new EntityHandler();
        playerHandler = new PlayerHandler();
    }

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }
}
