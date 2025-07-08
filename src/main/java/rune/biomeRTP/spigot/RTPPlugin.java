package rune.biomeRTP.spigot;

import alepando.dev.dialogapi.DialogAPI;
import alepando.dev.dialogapi.executor.CustomKeyRegistry;
import org.bukkit.plugin.java.JavaPlugin;
import rune.biomeRTP.spigot.util.EasyDialogUtil;

public final class RTPPlugin extends JavaPlugin {
    public static RTPPlugin plugin;

    public static RTPPlugin getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        CustomKeyRegistry registry = CustomKeyRegistry.INSTANCE;
        registry.clearAll();

        // 추후 Action 관리필요
        DialogAPI.INSTANCE.initialize(this);

        RegisterAction();
        this.getCommand("rtp").setExecutor(new RTPCommand());
    }

    @Override
    public void onDisable() {
    }

    public void RegisterAction() {
        EasyDialogUtil.RegisterAction("biomertp", "taiga");
    }

}
