package rune.biomeRTP.spigot;

import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RTPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            player.playSound(player.getLocation(), "minecraft:flip", SoundCategory.MASTER, 1.0f, 1.0f);
            Screen.sendBiomeRTPMapDialog(player);
            //Screen.DoubleCheckDialog(player, "taiga");
            return true;
        }
        return true;
    }
}
