package rune.biomeRTP.spigot.Dialog;

import alepando.dev.dialogapi.factory.actions.CustomAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import rune.biomeRTP.spigot.util.BiomeFinder;
import rune.biomeRTP.spigot.util.BiomeMapping;

public class RTPAction extends CustomAction {
    private final String biome;

    public RTPAction(String biome) {
        this.biome = biome;
    }

    @Override
    public void task(@NotNull Player player, @NotNull Plugin plugin) {
        Bukkit.broadcastMessage("test");
        BiomeFinder.teleportToBiome(Bukkit.getWorld("world"), player, BiomeMapping.getBiome(biome), 50);
    }
}