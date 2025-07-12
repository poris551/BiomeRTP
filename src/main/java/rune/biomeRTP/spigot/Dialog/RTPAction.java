package rune.biomeRTP.spigot.Dialog;

import alepando.dev.dialogapi.factory.actions.CustomAction;
import org.bukkit.Bukkit;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import rune.biomeRTP.spigot.RTPPlugin;
import rune.biomeRTP.spigot.Screen;
import rune.biomeRTP.spigot.util.BiomeFinder;
import rune.biomeRTP.spigot.util.BiomeMapping;

public class RTPAction extends CustomAction {
    private final String biome;

    public RTPAction(String biome) {
        this.biome = biome;
    }

    @Override
    public void task(Player player, Plugin plugin) {
        if (biome.contains("suggection_")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.stopSound("minecraft:ui.button.click");
                }
            }.runTaskLaterAsynchronously(RTPPlugin.getInstance(), 8L);

            Screen.DoubleCheckDialog(player, biome.replace("suggection_", ""));
            return;
        }
        if (biome.equals("reopen")) {
            Screen.sendBiomeRTPMapDialog(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.stopSound("minecraft:ui.button.click");
                }
            }.runTaskLaterAsynchronously(RTPPlugin.getInstance(), 8L);

            return;
        }
        player.playSound(player.getLocation(), "minecraft:teleporting", SoundCategory.MASTER, 1.0f, 1.0f);
        player.sendTitle("§f이동중 . . .", "§0\uF301", 10, 300, 0);
        int x = player.getLocation().getBlockX()+1;
        int y = player.getLocation().getBlockY()+1;
        int z = player.getLocation().getBlockZ()+1;
        int x2 = player.getLocation().getBlockX()-1;
        int y2 = player.getLocation().getBlockY()-1;
        int z2 = player.getLocation().getBlockZ()-1;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:entity_effect{color:[0.996078431372549, 0.9921568627450981, 1.0, 0.984313725490196],scale:1f} "+x+" "+y+" "+z+" "+x2+" "+y2+" "+z2+" 0 1 force "+player.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:entity_effect{color:[0.996078431372549, 0.9882352941176471, 0.5, 0.984313725490196],scale:1f} "+x+" "+y+" "+z+" "+x2+" "+y2+" "+z2+" 0 1 force "+player.getName());




        new BukkitRunnable() {
            @Override
            public void run() {
                BiomeFinder.teleportToBiome(Bukkit.getWorld("world"), player, BiomeMapping.getBiome(biome), 100);
            }
        }.runTaskLaterAsynchronously(RTPPlugin.getInstance(), 20L);

    }
}