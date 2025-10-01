package me.bestnuts.th.handlers;

import me.bestnuts.th.hud.HudFactory;

public class TacticalHandler {

    private final HudFactory hudFactory;

    private final EntityHandler entityHandler;
    private final PlayerHandler playerHandler;

    public TacticalHandler() {
        hudFactory = new HudFactory();

        entityHandler = new EntityHandler();
        playerHandler = new PlayerHandler(hudFactory);
    }

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }
}
