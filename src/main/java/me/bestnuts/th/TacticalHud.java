package me.bestnuts.th;

import me.bestnuts.th.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TacticalHud extends JavaPlugin {

    @Override
    public void onEnable() {
        checkEnableAPI();
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private void checkEnableAPI() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {

        }
    }
}
