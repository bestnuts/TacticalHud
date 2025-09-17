package me.bestnuts.th;

import me.bestnuts.th.handlers.TacticalHandler;
import me.bestnuts.th.listeners.PlayerListener;
import me.bestnuts.th.player.TacticalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class TacticalHudPlugin extends JavaPlugin {

    private static TacticalHudPlugin plugin;
    TacticalHandler tacticalHandler;
    Map<Player, TacticalPlayer> tacticalPlayerMap;
    boolean enablePlaceHolderAPI;

    public @NotNull Logger getLogger() {
        return super.getLogger();
    }

    @Override
    public void onEnable() {
        plugin = this;
        tacticalHandler = new TacticalHandler();
        tacticalPlayerMap = new HashMap<>();
        checkEnableAPI();
        registerEvents();

        for (Player player : Bukkit.getOnlinePlayers()) {
            getTacticalPlayer(player);
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);

        for (TacticalPlayer tacticalPlayer : tacticalPlayerMap.values()) {
            tacticalPlayer.hudUpdate(t -> t.getHudObject().remove());
        }

        tacticalPlayerMap.clear();
        tacticalPlayerMap = null;
        tacticalHandler = null;
        plugin = null;
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private void checkEnableAPI() {
        enablePlaceHolderAPI = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    public boolean isEnablePlaceHolderAPI() {
        return enablePlaceHolderAPI;
    }

    public TacticalHandler getTacticalHandler() {
        return tacticalHandler;
    }

    public static TacticalHudPlugin getInstance() {
        return plugin;
    }

    public static TacticalPlayer getTacticalPlayer(Player player) {
        TacticalPlayer tacticalPlayer = plugin.tacticalPlayerMap.get(player);
        if (tacticalPlayer == null) {
            tacticalPlayer = new TacticalPlayer(player);
            plugin.tacticalPlayerMap.put(player, tacticalPlayer);
        }
        return tacticalPlayer;
    }

    public static TacticalPlayer removeTacticalPlayer(Player player) {
        return plugin.tacticalPlayerMap.remove(player);
    }
}
