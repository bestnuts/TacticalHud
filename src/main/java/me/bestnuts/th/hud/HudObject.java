package me.bestnuts.th.hud;

import me.bestnuts.th.TacticalHudPlugin;
import me.bestnuts.th.handlers.EntityHandler;

public record HudObject(HudComponent hudComponent, HudTransform hudTransform, HudEntity hudEntity) {

    private void update() {
        EntityHandler handler = TacticalHudPlugin.getInstance().getTacticalHandler().getEntityHandler();
        handler.updateEntityData(hudEntity.getPlayer(), hudEntity.getEntity());
    }

    public void create() {
        EntityHandler handler = TacticalHudPlugin.getInstance().getTacticalHandler().getEntityHandler();
        if (hudEntity.isEntityInValid()) {
            hudEntity.createEntity();
            handler.sendAddEntity(hudEntity.getPlayer(), hudEntity.getEntity());
            mount();
        }
        modifyAll();
    }

    public void remove() {
        EntityHandler handler = TacticalHudPlugin.getInstance().getTacticalHandler().getEntityHandler();
        if (hudEntity.isEntityInValid())
            return;
        handler.removeEntity(hudEntity.getPlayer(), hudEntity.getEntity());
    }

    public void modifyAll() {
        modifyComponent();
        modifyTransform();
    }

    public void mount() {
        EntityHandler handler = TacticalHudPlugin.getInstance().getTacticalHandler().getEntityHandler();
        handler.mountEntityOnPlayer(hudEntity.getPlayer(), hudEntity.getEntity());
    }

    public void modifyComponent() {
        hudComponent.modify(hudEntity.getBukkitEntity(), hudEntity.getBukkitPlayer());
        update();
    }

    public void modifyTransform() {
        hudTransform.modify(hudEntity.getBukkitEntity());
        update();
    }
}
