package me.bestnuts.th.hud;

import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public record HudTransform (
        @NotNull Vector3f transform,
        @NotNull Vector3f scale,
        @NotNull AxisAngle4f leftRotation,
        @NotNull AxisAngle4f rightRotation,
        @NotNull Vector2f rawRotation
) {
    public void modify(Display display) {
        display.setTransformation(
                new Transformation(
                        transform,
                        leftRotation,
                        scale,
                        rightRotation
                )
        );
        display.setRotation(rawRotation.x, rawRotation.y);
    }
}
