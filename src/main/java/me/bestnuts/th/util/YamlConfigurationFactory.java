package me.bestnuts.th.util;

import me.bestnuts.th.TacticalHudPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class YamlConfigurationFactory {

    public static @NotNull FileConfiguration getConfiguration(@NotNull String path) {
        File file = new File(TacticalHudPlugin.getInstance().getDataFolder() + "/" + path + ".yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static @NotNull FileConfiguration getConfiguration() {
        return TacticalHudPlugin.getInstance().getConfig();
    }
}
