package me.bestnuts.th.hud;

public class HudObject {

    private final HudComponent hudComponent;
    private final HudTransform hudTransform;
    private final HudEntity hudEntity;

    public HudObject(HudComponent hudComponent, HudTransform hudTransform, HudEntity hudEntity) {
        this.hudComponent = hudComponent;
        this.hudTransform = hudTransform;
        this.hudEntity = hudEntity;
    }
}
