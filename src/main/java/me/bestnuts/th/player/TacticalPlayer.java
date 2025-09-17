package me.bestnuts.th.player;

import me.bestnuts.th.TacticalHudPlugin;
import me.bestnuts.th.hud.TacticalHud;
import me.bestnuts.th.util.YamlConfigurationFactory;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class TacticalPlayer extends BukkitRunnable {

    private final Player player;

    private final Map<Integer, TacticalHud> tacticalHudMap;

    public TacticalPlayer(Player player) {
        this.player = player;
        tacticalHudMap = new HashMap<>();

        if (YamlConfigurationFactory.getConfiguration().getBoolean("enable", false)) {
            TacticalHudPlugin.getInstance().getTacticalHandler().getPlayerHandler().createHud(this);
        }

        this.runTaskTimer(
                TacticalHudPlugin.getInstance(),
                1L,
                1L
        );
    }

    @Override
    public void run() {
        if (tacticalHudMap.isEmpty())
            return;
        for (TacticalHud tacticalHud : tacticalHudMap.values()) {
            tacticalHud.update();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void putTacticalHud(int index, TacticalHud tacticalHud) {
        tacticalHudMap.put(index, tacticalHud);
    }
}
