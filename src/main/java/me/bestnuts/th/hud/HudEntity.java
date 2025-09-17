package me.bestnuts.th.hud;

import me.bestnuts.th.player.TacticalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

public class HudEntity {

    private final ServerPlayer player;
    private Entity entity;

    public HudEntity(TacticalPlayer tacticalPlayer) {
        this.player = ((CraftPlayer) tacticalPlayer.getPlayer()).getHandle();
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    public Entity getEntity() {
        return entity;
    }

    public Player getBukkitPlayer() {
        return player.getBukkitEntity().getPlayer();
    }

    public TextDisplay getBukkitEntity() {
        return (TextDisplay) entity.getBukkitEntity();
    }

    public boolean isEntityInValid() {
        return entity == null || entity.isRemoved();
    }

    public void createEntity() {
        entity = EntityType.TEXT_DISPLAY.create(player.level(), EntitySpawnReason.COMMAND);
    }
}
