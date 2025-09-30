package me.bestnuts.th.utils;

import org.joml.AxisAngle4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

public class JomlFactory {

    public static Vector2f getVector2f(List<Float> list) {
        if (list == null || list.isEmpty() || list.size() < 2)
            return new Vector2f();
        return new Vector2f(list.get(0), list.get(1));
    }

    public static Vector3f getVector3f(List<Float> list) {
        if (list == null || list.isEmpty() || list.size() < 3)
            return new Vector3f();
        return new Vector3f(list.get(0), list.get(1), list.get(2));
    }

    public static AxisAngle4f getAxisAngle4f(List<Float> list) {
        if (list == null || list.isEmpty() || list.size() < 3)
            return new AxisAngle4f();
        return new AxisAngle4f(list.get(0), list.get(1), list.get(2), list.get(3));
    }
}
