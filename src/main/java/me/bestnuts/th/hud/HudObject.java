package me.bestnuts.th.hud;

import me.bestnuts.th.TacticalHudPlugin;
import me.bestnuts.th.handlers.EntityHandler;

public record HudObject(HudComponent hudComponent, HudTransform hudTransform, HudEntity hudEntity) {

    public HudObject(HudComponent hudComponent, HudTransform hudTransform, HudEntity hudEntity) {
        this.hudComponent = hudComponent;
        this.hudTransform = hudTransform;
        this.hudEntity = hudEntity;
        create();
    }

    private void create() {
        EntityHandler handler = TacticalHudPlugin.getInstance().getTacticalHandler().getEntityHandler();
        if (hudEntity.isEntityInValid()) {
            hudEntity.createEntity();
            handler.sendAddEntity(hudEntity.getPlayer(), hudEntity.getEntity());
            mount();
        }
        modifyAll();
    }

    public void modifyAll() {
        EntityHandler handler = TacticalHudPlugin.getInstance().getTacticalHandler().getEntityHandler();

        modifyComponent();
        modifyTransform();

        handler.updateEntityData(hudEntity.getPlayer(), hudEntity.getEntity());
    }

    public void mount() {
        EntityHandler handler = TacticalHudPlugin.getInstance().getTacticalHandler().getEntityHandler();
        handler.mountEntityOnPlayer(hudEntity.getPlayer(), hudEntity.getEntity());
    }

    public void modifyComponent() {
        hudComponent.modify(hudEntity.getBukkitEntity());
    }

    public void modifyTransform() {
        hudTransform.modify(hudEntity.getBukkitEntity());
    }
}
