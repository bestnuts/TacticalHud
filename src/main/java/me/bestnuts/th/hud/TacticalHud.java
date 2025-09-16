package me.bestnuts.th.hud;

import me.bestnuts.th.player.TacticalPlayer;

public class TacticalHud {

    private final TacticalPlayer tacticalPlayer;

    private final int interval;
    private final String condition;

    private final HudEntity hudEntity;
    private final HudComponent component;
    private final HudTransform transform;

    public TacticalHud(TacticalPlayer tacticalPlayer, int interval, String condition, HudComponent component, HudTransform transform) {
        this.tacticalPlayer = tacticalPlayer;
        this.interval = interval;
        this.condition = condition;

        this.hudEntity = new HudEntity(tacticalPlayer);
        this.component = component;
        this.transform = transform;
    }

    public TacticalPlayer getTacticalPlayer() {
        return tacticalPlayer;
    }

    public int getInterval() {
        return interval;
    }

    public String getCondition() {
        return condition;
    }

    public HudEntity getHudEntity() {
        return hudEntity;
    }

    public HudComponent getComponent() {
        return component;
    }

    public HudTransform getTransform() {
        return transform;
    }
}
