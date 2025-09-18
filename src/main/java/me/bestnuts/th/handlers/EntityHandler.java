package me.bestnuts.th.handlers;

import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.List;

public class EntityHandler {

    public void sendAddEntity(ServerPlayer player, Entity entity) {
        player.connection.send(new ClientboundAddEntityPacket(
                entity.getId(),
                entity.getUUID(),
                player.getX(),
                player.getY(),
                player.getZ(),
                entity.getXRot(),
                entity.getYRot(),
                entity.getType(),
                0,
                entity.getDeltaMovement(),
                entity.getYHeadRot()
        ));
    }

    public void updateEntityData(ServerPlayer player, Entity entity) {
        List<SynchedEntityData.DataValue<?>> dataValues = entity.getEntityData().packDirty();
        if (dataValues != null && !dataValues.isEmpty()) {
            player.connection.send(new ClientboundSetEntityDataPacket(entity.getId(), dataValues));
        }
    }

    public void removeEntity(ServerPlayer player, Entity entity) {
        player.connection.send(new ClientboundRemoveEntitiesPacket(entity.getId()));
    }

    public void mountEntityOnPlayer(ServerPlayer player, Entity entity) {
        entity.startRiding(player, true);
        player.connection.send(new ClientboundSetPassengersPacket(player));
    }
}
