package me.bestnuts.th.util;

import me.bestnuts.th.TacticalHudPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.mvel2.MVEL;

public class ConditionParser {

    public static boolean parsing(Player player, String condition) {
        if (TacticalHudPlugin.getInstance().isEnablePlaceHolderAPI())
            condition = PlaceholderAPI.setPlaceholders(player, condition);
        return (Boolean) MVEL.eval(condition);
    }
}
