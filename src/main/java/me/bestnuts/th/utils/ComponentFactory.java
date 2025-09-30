package me.bestnuts.th.utils;

import me.bestnuts.th.TacticalHudPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ComponentFactory {

    public static Component getComponent(@NotNull Player player, @Nullable String text) {
        if (text == null)
            return Component.text("");
        if (TacticalHudPlugin.getInstance().isEnablePlaceHolderAPI())
            text = PlaceholderAPI.setPlaceholders(player, text);
        GsonComponentSerializer serializer = GsonComponentSerializer.gson();
        return serializer.deserialize(text);
    }
}
