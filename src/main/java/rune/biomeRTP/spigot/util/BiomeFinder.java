package rune.biomeRTP.spigot.util;

import org.bukkit.*;
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

            @Override
            public void run() {
                Location center = player.getLocation();

                for (; attempts < maxAttempts; attempts++) {
                    int radius = 500 + Math.min(attempts * 100, 9500);
                    double randomX = center.getX() + (Math.random() * 2 * radius - radius);
                    double randomZ = center.getZ() + (Math.random() * 2 * radius - radius);

                    Biome biome = w.getBiome((int) randomX, 64, (int) randomZ);

                    if (biome == targetBiome) {
                        int finalAttempts = attempts;
                        Bukkit.getScheduler().runTask(RTPPlugin.getInstance(), () -> {
                            Location finalLoc = new Location(w, randomX, w.getHighestBlockYAt((int) randomX, (int) randomZ), randomZ);
                            safeTeleport(player, finalLoc);
                        });
                        cancel();
                        return;
                    }
                }

                Bukkit.getScheduler().runTask(RTPPlugin.getInstance(), () ->
                        player.sendMessage(ChatColor.RED + "바이옴 찾기 실패")
                );
                Bukkit.getScheduler().runTask(RTPPlugin.getInstance(), () ->
                        player.sendTitle("바이옴 찾기 실패", "", 0, 0, 0)
                );
                int x = player.getLocation().getBlockX()+1;
                int y = player.getLocation().getBlockY()+1;
                int z = player.getLocation().getBlockZ()+1;
                int x2 = player.getLocation().getBlockX()-1;
                int y2 = player.getLocation().getBlockY()-1;
                int z2 = player.getLocation().getBlockZ()-1;
                player.sendTitle("이동중 . . .", "§0\uF300", 0, 0, 30);
                player.stopSound("minecraft:teleporting");
                player.playSound(player.getLocation(), "minecraft:teleported", SoundCategory.MASTER, 1.0f, 1.0f);

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:entity_effect{color:[0.996078431372549, 0.9921568627450981, 0.0, 0.984313725490196],scale:1f} "+x+" "+y+" "+z+" "+x2+" "+y2+" "+z2+" 0 1 force "+player.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:entity_effect{color:[0.996078431372549, 0.9882352941176471, 0.0, 0.984313725490196],scale:1f} "+x+" "+y+" "+z+" "+x2+" "+y2+" "+z2+" 0 1 force "+player.getName());

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
                int x = player.getLocation().getBlockX()+1;
                int y = player.getLocation().getBlockY()+1;
                int z = player.getLocation().getBlockZ()+1;
                int x2 = player.getLocation().getBlockX()-1;
                int y2 = player.getLocation().getBlockY()-1;
                int z2 = player.getLocation().getBlockZ()-1;
                player.sendTitle("이동중 . . .", "§0\uF300", 0, 0, 30);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:entity_effect{color:[0.996078431372549, 0.9921568627450981, 0.0, 0.984313725490196],scale:1f} "+x+" "+y+" "+z+" "+x2+" "+y2+" "+z2+" 0 1 force "+player.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:entity_effect{color:[0.996078431372549, 0.9882352941176471, 0.0, 0.984313725490196],scale:1f} "+x+" "+y+" "+z+" "+x2+" "+y2+" "+z2+" 0 1 force "+player.getName());
                player.sendMessage(ChatColor.GREEN + safeLoc.getBlock().getBiome().translationKey() + " 바이옴 이동");
            }
        }.runTask(RTPPlugin.getInstance());
    }
}