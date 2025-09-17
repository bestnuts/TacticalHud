package me.bestnuts.th.hud;

import me.bestnuts.th.util.ComponentFactory;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record HudComponent(
        @NotNull TextDisplay.TextAlignment alignment,
        @NotNull Color backgroundColor,
        @Nullable String text,
        int lineWidth,
        boolean textShadow
) {
    public void modify(TextDisplay display) {
        display.setAlignment(alignment);
        display.setBackgroundColor(backgroundColor);
        display.text(ComponentFactory.getComponent(text));
        display.setLineWidth(lineWidth);
        display.setShadowed(textShadow);
    }
}
