package me.bestnuts.th.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import javax.annotation.Nullable;

public class ComponentFactory {

    public static Component getComponent(@Nullable String text) {
        if (text == null)
            return Component.text("");
        GsonComponentSerializer serializer = GsonComponentSerializer.gson();
        return serializer.deserialize(text);
    }
}
