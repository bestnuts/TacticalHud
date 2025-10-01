package me.bestnuts.th.hud.entity;

import me.bestnuts.th.hud.HudEntity;
import me.bestnuts.th.player.TacticalPlayer;
import me.bestnuts.th.utils.ComponentFactory;
import net.kyori.adventure.text.Component;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

import java.util.Arrays;
import java.util.List;

public class HudText extends HudEntity<TextDisplay> {

    private final HudComponent hudComponent;

    public HudText(TacticalPlayer tacticalPlayer, HudComponent hudComponent) {
        super(tacticalPlayer);
        this.hudComponent = hudComponent;
    }

    @Override
    public TextDisplay getBukkitEntity() {
        return (TextDisplay) getEntity().getBukkitEntity();
    }

    @Override
    public void createEntity() {
        setEntity(EntityType.TEXT_DISPLAY.create(getPlayer().level(), EntitySpawnReason.COMMAND));
    }

    @Override
    public void modifyEntity() {
        hudComponent.modify(getBukkitEntity(), getBukkitPlayer());
    }

    public record HudComponent(
            TextDisplay.TextAlignment alignment,
            Color backgroundColor,
            String text,
            String font,
            int lineWidth,
            boolean textShadow
    ) {
        public void modify(TextDisplay display, Player player) {
            display.setAlignment(alignment);
            display.setBackgroundColor(backgroundColor);
            Component component = ComponentFactory.getComponent(player, text);
            List<String> split = Arrays.stream(font.split(":")).toList();
            if (split.size() == 2) {
                NamespacedKey fontKey = new NamespacedKey(split.get(0), split.get(1));
                component = component.font(fontKey);
            }
            display.text(component);
            display.setLineWidth(lineWidth);
            display.setShadowed(textShadow);
        }
    }
}
