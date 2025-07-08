package rune.biomeRTP.spigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rune.biomeRTP.spigot.Dialog.Screen;

public class RTPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Screen.sendBiomeRTPMapDialog(player);
            return true;
        }
        return true;
    }
}
