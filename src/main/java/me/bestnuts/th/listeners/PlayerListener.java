package me.bestnuts.th.listeners;

import me.bestnuts.th.TacticalHudPlugin;
import me.bestnuts.th.hud.TacticalHud;
import me.bestnuts.th.player.TacticalPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TacticalHudPlugin.getTacticalPlayer(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        TacticalHudPlugin.removeTacticalPlayer(player);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        TacticalPlayer tacticalPlayer = TacticalHudPlugin.getTacticalPlayer(player);
        tacticalPlayer.hudUpdate(TacticalHud::forceUpdate);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        TacticalPlayer tacticalPlayer = TacticalHudPlugin.getTacticalPlayer(player);
        tacticalPlayer.hudUpdate(TacticalHud::forceUpdate);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        TacticalPlayer tacticalPlayer = TacticalHudPlugin.getTacticalPlayer(player);
        tacticalPlayer.hudUpdate(TacticalHud::forceUpdate);
    }
}
