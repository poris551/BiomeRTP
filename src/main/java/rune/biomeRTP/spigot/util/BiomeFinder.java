package rune.biomeRTP.spigot.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import rune.biomeRTP.spigot.RTPPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BiomeFinder {

    public static Set<Biome> allowedBiomes = new HashSet<>(Arrays.asList(
                Biome.PLAINS,
                Biome.DESERT,
                Biome.FOREST,
                Biome.TAIGA,
                Biome.JUNGLE,
                Biome.SAVANNA,
                Biome.SWAMP,
                Biome.CHERRY_GROVE,
                Biome.BEACH
        ));


    public static void teleportToBiome(World w, Player player, Biome targetBiome, int maxAttempts) {
        if (!allowedBiomes.contains(targetBiome)) {
            player.sendMessage(ChatColor.RED + "알 수 없는 바이옴.");
            return;
        }

        new BukkitRunnable() {
            int attempts = 0;
            boolean found = false;

            @Override
            public void run() {
                Location center = player.getLocation();
                while (attempts < maxAttempts && !found) {
                    int radius = 500 + Math.min(attempts * 100, 9500);
                    double randomX = center.getX() + (Math.random() * 2 * radius - radius);
                    double randomZ = center.getZ() + (Math.random() * 2 * radius - radius);
                    int highestY = w.getHighestBlockYAt((int) randomX, (int) randomZ);
                    Location loc = new Location(w, randomX, highestY, randomZ);
                    if (w.getBiome(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()) == targetBiome) {
                        found = true;
                        safeTeleport(player, loc);
                        break;
                    }
                    attempts++;
                }

                if (!found) {
                    player.sendMessage(ChatColor.RED + "바이옴 찾기 실패");
                }
                cancel();
            }
        }.runTaskAsynchronously(RTPPlugin.getInstance());
    }

    public static void safeTeleport(Player player, Location loc) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location safeLoc = loc.clone();
                safeLoc.setY(loc.getWorld().getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()) + 1);
                player.teleport(safeLoc);
                player.sendMessage(ChatColor.GREEN + safeLoc.getBlock().getBiome().translationKey() + " 바이옴 이동");
            }
        }.runTask(RTPPlugin.getInstance());
    }
}