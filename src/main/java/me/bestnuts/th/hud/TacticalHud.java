package me.bestnuts.th.hud;

import me.bestnuts.th.player.TacticalPlayer;
import me.bestnuts.th.util.ConditionParser;

public class TacticalHud {

    private final TacticalPlayer tacticalPlayer;

    private final int interval;
    private final String condition;

    private final HudObject hudObject;

    private int tick;

    public TacticalHud(TacticalPlayer tacticalPlayer, int interval, String condition, HudObject hudObject) {
        this.tacticalPlayer = tacticalPlayer;

        this.interval = interval;
        this.condition = condition;

        this.hudObject = hudObject;
    }

    public void update() {
        tick -= 1;
        if (tick <= 0) {
            tick = interval;
            if (ConditionParser.parsing(tacticalPlayer.getPlayer(), condition))
                hudObject.create();
            else
                hudObject.remove();
        }
    }

    public void forceUpdate() {
        hudObject.modifyComponent();
        hudObject.mount();
    }

    public int getInterval() {
        return interval;
    }

    public String getCondition() {
        return condition;
    }

    public HudObject getHudObject() {
        return hudObject;
    }
}
