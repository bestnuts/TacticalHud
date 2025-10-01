package me.bestnuts.th.handlers;

import me.bestnuts.th.TacticalHudPlugin;
import me.bestnuts.th.exceptions.NotFoundSubSectionElement;
import me.bestnuts.th.hud.*;
import me.bestnuts.th.player.TacticalPlayer;
import me.bestnuts.th.utils.YamlConfigurationFactory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class PlayerHandler {

    private final HudFactory hudFactory;

    public PlayerHandler(HudFactory hudFactory) {
        this.hudFactory = hudFactory;
    }

    public void createHud(@NotNull TacticalPlayer tacticalPlayer) {
        createHud(tacticalPlayer, YamlConfigurationFactory.getConfiguration("hud/default"));
    }

    public void createHud(@NotNull TacticalPlayer tacticalPlayer, @NotNull FileConfiguration configuration) {
        ConfigurationSection section = configuration.getConfigurationSection("hud");
        if (section != null)
            createHud(tacticalPlayer, section);
    }

    private void createHud(@NotNull TacticalPlayer tacticalPlayer, @NotNull ConfigurationSection section) {
        for (String key : section.getKeys(false)) {
            try {
                int index = Integer.parseInt(key);

                ConfigurationSection subSection = section.getConfigurationSection(key);
                if (subSection == null) throw new NotFoundSubSectionElement("subSection");

                String displayPath = subSection.getString("path");
                if (displayPath == null) throw new NotFoundSubSectionElement("path");

                FileConfiguration displayConfiguration = YamlConfigurationFactory.getConfiguration(displayPath);

                int interval = subSection.getInt("interval", 1);
                String condition = subSection.getString("condition", "true");

                TacticalHud tacticalHud = new TacticalHud(
                        tacticalPlayer,
                        interval,
                        condition,
                        hudFactory.createHudEntity(tacticalPlayer, displayConfiguration)
                );

                tacticalPlayer.putTacticalHud(index, tacticalHud);
            } catch (NumberFormatException exception) {
                TacticalHudPlugin.getInstance().getLogger().warning("Hud 섹션 키가 숫자가 아닙니다. " + key);
            } catch (NotFoundSubSectionElement exception) {
                exception.printStackTrace();
            }
        }
    }
}
