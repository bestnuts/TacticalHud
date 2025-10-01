package me.bestnuts.th.hud;

import me.bestnuts.th.exceptions.NotFoundSubSectionElement;
import me.bestnuts.th.hud.entity.HudBlock;
import me.bestnuts.th.hud.entity.HudItem;
import me.bestnuts.th.hud.entity.HudText;
import me.bestnuts.th.player.TacticalPlayer;
import me.bestnuts.th.utils.JomlFactory;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

public class HudFactory {

    public @NotNull HudObject createHudEntity(
            @NotNull TacticalPlayer tacticalPlayer,
            @NotNull FileConfiguration configuration
    ) throws NotFoundSubSectionElement {

        HudEntityType hudEntityType = HudEntityType.valueOf(configuration.getString("type", "BLOCK_DISPLAY").toUpperCase());

        ConfigurationSection componentSection = configuration.getConfigurationSection("component");
        ConfigurationSection transformSection = configuration.getConfigurationSection("transform");
        if (componentSection == null) throw new NotFoundSubSectionElement("component");

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

        return new HudObject(transform, hudEntityType.create(tacticalPlayer, componentSection));
    }

    public enum HudEntityType {
        BLOCK_DISPLAY {
            @Override
            public HudEntity<BlockDisplay> create(TacticalPlayer tacticalPlayer, ConfigurationSection component) {
                Material material = Material.getMaterial(component.getString("material", "AIR").toUpperCase());
                HudBlock.HudComponent hudComponent = new HudBlock.HudComponent(material);
                return new HudBlock(
                        tacticalPlayer,
                        hudComponent
                );
            }
        },
        ITEM_DISPLAY {
            @Override
            public HudEntity<ItemDisplay> create(TacticalPlayer tacticalPlayer, ConfigurationSection component) {
                Material material = Material.getMaterial(component.getString("material", "AIR").toUpperCase());
                ItemDisplay.ItemDisplayTransform display = ItemDisplay.ItemDisplayTransform.valueOf(component.getString("display", "NONE").toUpperCase());
                if (material == null) material = Material.AIR;
                ItemStack itemStack = new ItemStack(material);
                HudItem.HudComponent hudComponent = new HudItem.HudComponent(itemStack, display);
                return new HudItem(
                        tacticalPlayer,
                        hudComponent
                );
            }
        },
        TEXT_DISPLAY {
            @Override
            public HudEntity<TextDisplay> create(TacticalPlayer tacticalPlayer, ConfigurationSection component) {
                List<Integer> colorList = component.getIntegerList("backgroundColor");
                if (colorList.size() != 4) colorList = List.of(0, 0, 0, 0);
                HudText.HudComponent hudComponent = new HudText.HudComponent(
                        TextDisplay.TextAlignment.valueOf(component.getString("alignment", "CENTER").toUpperCase()),
                        Color.fromARGB(colorList.get(0), colorList.get(1), colorList.get(2), colorList.get(3)),
                        component.getString("text", ""),
                        component.getString("font", "minecraft:default"),
                        component.getInt("lineWidth", 0),
                        component.getBoolean("textShadow", false)
                );
                return new HudText(
                        tacticalPlayer,
                        hudComponent
                );
            }
        };

        public abstract HudEntity<? extends Display> create(TacticalPlayer tacticalPlayer, ConfigurationSection component);
    }
}
