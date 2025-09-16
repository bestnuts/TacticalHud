package me.bestnuts.th.player;

import me.bestnuts.th.hud.TacticalHud;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TacticalPlayer {

    private final Player player;

    private final Map<Integer, TacticalHud> tacticalHudMap;

    public TacticalPlayer(Player player) {
        this.player = player;
        tacticalHudMap = new HashMap<>();
    }

    public Player getPlayer() {
        return player;
    }

    public void putTacticalHud(int index, TacticalHud tacticalHud) {
        tacticalHudMap.put(index, tacticalHud);
    }
}
