package me.bestnuts.th.hud;

import me.bestnuts.th.util.ComponentFactory;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public record HudComponent(
        @NotNull TextDisplay.TextAlignment alignment,
        @NotNull Color backgroundColor,
        @NotNull String text,
        @NotNull String font,
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
