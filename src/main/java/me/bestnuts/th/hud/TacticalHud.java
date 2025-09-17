package me.bestnuts.th.hud;

import me.bestnuts.th.util.ConditionParser;

public class TacticalHud {

    private final int interval;
    private final String condition;

    private final HudObject hudObject;

    private int tick;

    public TacticalHud(int interval, String condition, HudObject hudObject) {
        this.interval = interval;
        this.condition = condition;

        this.hudObject = hudObject;
    }

    public void update() {
        tick -= 1;
        if (tick <= 0) {
            tick = interval;
            if (ConditionParser.parsing(condition))
                hudObject.modifyComponent();
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
