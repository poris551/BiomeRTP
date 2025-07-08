package rune.biomeRTP.spigot.Dialog;

import alepando.dev.dialogapi.executor.PlayerOpener;
import alepando.dev.dialogapi.factory.button.Button;
import alepando.dev.dialogapi.factory.input.types.BooleanInput;
import alepando.dev.dialogapi.types.MultiActionDialog;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import rune.biomeRTP.spigot.util.EasyDialogUtil;

public class Screen extends EasyDialogUtil{
    public static void sendBiomeRTPMapDialog(Player p) {
        Button Taiga = buildButton("TAIGA", "클릭시 타이가 바이옴으로 이동합니다.", 32, buildKeyedAction("biomertp", "taiga"));

        Component text = buildCombinedText(
                buildText("test")
        );
        Component background = buildText("");

        MultiActionDialog dialog = buildEnhanceDialog(
                Component.empty(),
                new Component[]{text, background},
                new Button[]{Taiga},
                new BooleanInput[]{}
        );


        PlayerOpener.INSTANCE.openDialog(p, dialog);
    }
}
