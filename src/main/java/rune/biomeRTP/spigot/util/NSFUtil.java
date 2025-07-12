package rune.biomeRTP.spigot.util;

import net.kyori.adventure.text.Component;

import java.util.Map;
import java.util.TreeMap;

public class NSFUtil {
    private static final Map<Integer, String> spaceMap = new TreeMap<>();

    static {
        spaceMap.put(-1, "\uF101");
        spaceMap.put(-2, "\uF102");
        spaceMap.put(-3, "\uF103");
        spaceMap.put(-4, "\uF104");
        spaceMap.put(-5, "\uF105");
        spaceMap.put(-6, "\uF106");
        spaceMap.put(-7, "\uF107");
        spaceMap.put(-8, "\uF108");
        spaceMap.put(-9, "\uF109");
        spaceMap.put(-15, "\uF200");
        spaceMap.put(10, "\uF201");
        spaceMap.put(1, "\uF202");
        spaceMap.put(2, "\uF203");
        spaceMap.put(4, "\uF204");
        spaceMap.put(6, "\uF205");
    }


    public static Component space(int pixel) {
        Component result = Component.empty();

        if (pixel == 0) return result;

        var sorted = spaceMap.keySet().stream()
                .sorted((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)))
                .toList();

        int remaining = pixel;

        for (int key : sorted) {
            while ((remaining > 0 && key > 0 && key <= remaining) ||
                    (remaining < 0 && key < 0 && key >= remaining)) {
                result = result.append(Component.text(spaceMap.get(key)));
                remaining -= key;
            }
        }

        if (remaining != 0) {
            throw new IllegalArgumentException("nsf 에러 : " + pixel);
        }

        return result;
    }
}
