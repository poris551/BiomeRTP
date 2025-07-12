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
        EasyDialogUtil.RegisterAction("biomertp", "desert");
        EasyDialogUtil.RegisterAction("biomertp", "beach");
        EasyDialogUtil.RegisterAction("biomertp", "forest");
        EasyDialogUtil.RegisterAction("biomertp", "plains");
        EasyDialogUtil.RegisterAction("biomertp", "jungle");
        EasyDialogUtil.RegisterAction("biomertp", "swamp");
        EasyDialogUtil.RegisterAction("biomertp", "savanna");
        EasyDialogUtil.RegisterAction("biomertp", "snowy_plains");
        EasyDialogUtil.RegisterAction("biomertp", "reopen");

        EasyDialogUtil.RegisterAction("biomertp", "suggection_taiga");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_desert");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_beach");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_forest");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_plains");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_jungle");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_swamp");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_savanna");
        EasyDialogUtil.RegisterAction("biomertp", "suggection_snowy_plains");
    }

}
