package rune.biomeRTP.spigot.util;

import org.bukkit.block.Biome;

import java.util.HashMap;
import java.util.Map;

public class BiomeMapping {
    private static final Map<String, Biome> BIOME_MAP = new HashMap<>();

    static {
        BIOME_MAP.put("PLAINS", Biome.PLAINS);
        BIOME_MAP.put("DESERT", Biome.DESERT);
        BIOME_MAP.put("FOREST", Biome.FOREST);
        BIOME_MAP.put("TAIGA", Biome.TAIGA);
        BIOME_MAP.put("JUNGLE", Biome.JUNGLE);
        BIOME_MAP.put("SAVANNA", Biome.SAVANNA);
        BIOME_MAP.put("SWAMP", Biome.SWAMP);
        BIOME_MAP.put("CHERRY_GROVE", Biome.CHERRY_GROVE);
        BIOME_MAP.put("BEACH", Biome.BEACH);
    }
    public static Biome getBiome(String name) {
        return BIOME_MAP.get(name.toUpperCase());
    }

}
