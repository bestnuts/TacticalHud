package me.bestnuts.th.hud.entity;

import me.bestnuts.th.hud.HudEntity;
import me.bestnuts.th.player.TacticalPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;

public class HudBlock extends HudEntity<BlockDisplay> {

    private final HudComponent hudComponent;

    public HudBlock(TacticalPlayer tacticalPlayer, HudComponent hudComponent) {
        super(tacticalPlayer);
        this.hudComponent = hudComponent;
    }

    @Override
    public BlockDisplay getBukkitEntity() {
        return (BlockDisplay) getEntity().getBukkitEntity();
    }

    @Override
    public void createEntity() {
        setEntity(EntityType.BLOCK_DISPLAY.create(getPlayer().level(), EntitySpawnReason.COMMAND));
    }

    @Override
    public void modifyEntity() {
        hudComponent.modify(getBukkitEntity());
    }

    public record HudComponent(
            Material material
    ) {
        public void modify(BlockDisplay display) {
            display.setBlock(material.createBlockData());
        }
    }
}
