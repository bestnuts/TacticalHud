package me.bestnuts.th.handlers;

import me.bestnuts.th.TacticalHudPlugin;
import me.bestnuts.th.exceptions.NotFoundSubSectionElement;
import me.bestnuts.th.hud.*;
import me.bestnuts.th.player.TacticalPlayer;
import me.bestnuts.th.utils.JomlFactory;
import me.bestnuts.th.utils.YamlConfigurationFactory;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

public class PlayerHandler {

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

                TacticalHud tacticalHud = createHud(tacticalPlayer, displayConfiguration, interval, condition);

                tacticalPlayer.putTacticalHud(index, tacticalHud);
            } catch (NumberFormatException exception) {
                TacticalHudPlugin.getInstance().getLogger().warning("Hud 섹션 키가 숫자가 아닙니다. " + key);
            } catch (NotFoundSubSectionElement exception) {
                exception.printStackTrace();
            }
        }
    }

    private @NotNull TacticalHud createHud(@NotNull TacticalPlayer tacticalPlayer, @NotNull FileConfiguration configuration, int interval, String condition) throws NotFoundSubSectionElement {
        ConfigurationSection componentSection = configuration.getConfigurationSection("component");
        ConfigurationSection transformSection = configuration.getConfigurationSection("transform");
        if (componentSection == null) throw new NotFoundSubSectionElement("component");

        List<Integer> colorList = componentSection.getIntegerList("backgroundColor");
        if (colorList.size() != 4) colorList = List.of(0, 0, 0, 0);

        HudComponent component = new HudComponent(
                TextDisplay.TextAlignment.valueOf(componentSection.getString("alignment", "CENTER").toUpperCase()),
                Color.fromARGB(colorList.get(0), colorList.get(1), colorList.get(2), colorList.get(3)),
                componentSection.getString("text", ""),
                componentSection.getString("font", "minecraft:default"),
                componentSection.getInt("lineWidth", 0),
                componentSection.getBoolean("textShadow", false)
        );

        HudTransform transform;

        if (transformSection == null) {
            transform = new HudTransform(
                    new Vector3f(),
                    new Vector3f(),
                    new AxisAngle4f(),
                    new AxisAngle4f(),
                    new Vector2f()
            );
        } else {
            List<Float> transformList = transformSection.getFloatList("transform");
            List<Float> scaleList = transformSection.getFloatList("scale");
            List<Float> leftRotationList = transformSection.getFloatList("leftRotation");
            List<Float> rightRotationList = transformSection.getFloatList("rightRotation");
            List<Float> rawRotationList = transformSection.getFloatList("rawRotation");
            transform = new HudTransform(
                    JomlFactory.getVector3f(transformList),
                    JomlFactory.getVector3f(scaleList),
                    JomlFactory.getAxisAngle4f(leftRotationList),
                    JomlFactory.getAxisAngle4f(rightRotationList),
                    JomlFactory.getVector2f(rawRotationList)
            );
        }

        return new TacticalHud(tacticalPlayer, interval, condition,
                new HudObject(component, transform, new HudEntity(tacticalPlayer))
                );
    }
}
