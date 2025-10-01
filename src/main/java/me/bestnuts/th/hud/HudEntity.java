package me.bestnuts.th.hud;

import me.bestnuts.th.player.TacticalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;

public abstract class HudEntity<T extends Display> {

    private final ServerPlayer player;
    private Entity entity;

    public HudEntity(TacticalPlayer tacticalPlayer) {
        this.player = ((CraftPlayer) tacticalPlayer.getPlayer()).getHandle();
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public Player getBukkitPlayer() {
        return player.getBukkitEntity().getPlayer();
    }

    public boolean isEntityInValid() {
        return entity == null || entity.isRemoved();
    }

    public abstract T getBukkitEntity();

    public abstract void createEntity();

    public abstract void modifyEntity();
}
