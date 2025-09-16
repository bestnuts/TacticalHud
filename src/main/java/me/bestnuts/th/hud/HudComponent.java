package me.bestnuts.th.hud;

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
        if (text != null)
            display.text(Component.text(text));
        display.setLineWidth(lineWidth);
        display.setShadowed(textShadow);
    }
}
