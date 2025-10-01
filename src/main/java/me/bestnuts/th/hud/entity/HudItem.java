package me.bestnuts.th.hud.entity;

import me.bestnuts.th.hud.HudEntity;
import me.bestnuts.th.player.TacticalPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class HudItem extends HudEntity<ItemDisplay> {

    private final HudComponent hudComponent;

    public HudItem(TacticalPlayer tacticalPlayer, HudComponent hudComponent) {
        super(tacticalPlayer);
        this.hudComponent = hudComponent;
    }

    @Override
    public ItemDisplay getBukkitEntity() {
        return (ItemDisplay) getEntity().getBukkitEntity();
    }

    @Override
    public void createEntity() {
        setEntity(EntityType.ITEM_DISPLAY.create(getPlayer().level(), EntitySpawnReason.COMMAND));
    }

    @Override
    public void modifyEntity() {
        hudComponent.modify(getBukkitEntity());
    }

    public record HudComponent(
            ItemStack itemStack,
            ItemDisplay.ItemDisplayTransform displayTransform
    ) {
        public void modify(ItemDisplay display) {
            display.setItemStack(itemStack);
            display.setItemDisplayTransform(displayTransform);
        }
    }
}
