package me.bestnuts.th.utils;

import org.bukkit.Color;
import org.joml.AxisAngle4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

public class DimensionFactory {

    private static final int TWO_DIMENSION = 2;
    private static final int THREE_DIMENSION = 3;
    private static final int FOUR_DIMENSION = 4;

    public static Color getARGB(List<Integer> list) {
        if (list == null || list.isEmpty() || list.size() < FOUR_DIMENSION)
            list = List.of(0, 0, 0, 0);
        return Color.fromARGB(list.get(0), list.get(1), list.get(2), list.get(3));
    }

    public static Vector2f getVector2f(List<Float> list) {
        if (list == null || list.isEmpty() || list.size() < TWO_DIMENSION)
            return new Vector2f();
        return new Vector2f(list.get(0), list.get(1));
    }

    public static Vector3f getVector3f(List<Float> list) {
        if (list == null || list.isEmpty() || list.size() < THREE_DIMENSION)
            return new Vector3f();
        return new Vector3f(list.get(0), list.get(1), list.get(2));
    }

    public static AxisAngle4f getAxisAngle4f(List<Float> list) {
        if (list == null || list.isEmpty() || list.size() < FOUR_DIMENSION)
            return new AxisAngle4f();
        return new AxisAngle4f(list.get(0), list.get(1), list.get(2), list.get(3));
    }
}
