package rune.biomeRTP.spigot.util;

import alepando.dev.dialogapi.body.types.PlainMessageDialogBody;
import alepando.dev.dialogapi.factory.button.Button;
import alepando.dev.dialogapi.factory.button.data.ButtonData;
import alepando.dev.dialogapi.factory.button.data.ButtonDataBuilder;
import alepando.dev.dialogapi.factory.button.data.DataContainer;
import alepando.dev.dialogapi.factory.button.data.KeyedAction;
import alepando.dev.dialogapi.factory.data.DialogData;
import alepando.dev.dialogapi.factory.data.DialogDataBuilder;
import alepando.dev.dialogapi.executor.CustomKeyRegistry;
import alepando.dev.dialogapi.factory.data.ResourceLocation;
import alepando.dev.dialogapi.factory.input.types.BooleanInput;
import alepando.dev.dialogapi.factory.input.types.builders.BooleanInputBuilder;
import alepando.dev.dialogapi.types.MultiActionDialog;
import alepando.dev.dialogapi.types.builders.MultiActionDialogBuilder;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import rune.biomeRTP.spigot.Dialog.RTPAction;

import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

public class EasyDialogUtil {
    public static void RegisterAction(String Namespace, String Path) {
        ResourceLocation key = new ResourceLocation(Namespace, Path);
        try {
            CustomKeyRegistry.INSTANCE.register(
                    key,
                    new RTPAction(Path),
                    Optional.empty()
            );
            getLogger().info("Action 등록 성공: " + key);
        } catch (Exception e) {
            getLogger().warn("Action 등록 실패: " + e.getMessage());
        }
    }
    // 키 액션
    public static KeyedAction buildKeyedAction(String Namespace, String Path) {
        DataContainer container = new DataContainer();
        container.add(new NamespacedKey(Namespace, "biome"), PersistentDataType.STRING, Path);
        return new KeyedAction(new ResourceLocation(Namespace, Path), Optional.of(container));
    }

    // 텍스트
    public static Component buildText(String text) {
        return Component.text(text).color(TextColor.color(0xFFFFFF)).font(Key.key("default"));
    }

    public static Component buildText(String text, TextColor color) {
        return Component.text(text).color(color).font(Key.key("default"));
    }

    public static Component buildText(String text, Key font) {
        return Component.text(text).color(TextColor.color(0xFFFFFF)).font(font);
    }

    public static Component buildText(String text, TextColor color, Key font) {
        return Component.text(text).color(color).font(font);
    }

    public static Component buildCombinedText(Component... components) {
        return Component.text().append(components).build();
    }

    // 버튼
    public static Button buildButton(String text, String tooltip, int width, KeyedAction action) {
        ButtonData buttonData = new ButtonDataBuilder()
                .label(buildText(text))
                .width(width)
                .tooltip(buildText(tooltip))
                .build();
        return new Button(buttonData, Optional.ofNullable(action));
    }
    
    // 체크 Input 
    public static BooleanInput buildToggleInput(String label, String key, boolean initial) {
        return new BooleanInputBuilder()
                .label(buildText(label))
                .key(key)
                .initial(initial)
                .build();
    }
    
    // 생성
    public static DialogDataBuilder createDialogBuilder(Component title) {
        return new DialogDataBuilder()
                .title(title)
                .canCloseWithEscape(true);
    }

    // 빈줄 추가 (높낮이 조정)
    public static void addEmptyLines(DialogDataBuilder builder, int count) {
        for (int i = 0; i < count; i++) {
            builder.addBody(new PlainMessageDialogBody(1024, Component.empty()));
        }
    }
    
    // 다이얼로그 빌드 (최종시)
    public static MultiActionDialog buildEnhanceDialog(
            Component title,
            Component[] bodyComponents,
            Button[] buttons,
            BooleanInput[] inputs
    ) {
        DialogDataBuilder dialogBuilder = createDialogBuilder(title);

        for (Component component : bodyComponents) {
            dialogBuilder.addBody(new PlainMessageDialogBody(1024, component));
        }

        for (BooleanInput input : inputs) {
            dialogBuilder.addInput(input);
        }

        DialogData dialogData = dialogBuilder.build();

        MultiActionDialogBuilder dialogBuilderFinal = new MultiActionDialogBuilder()
                .data(dialogData)
                .columns(buttons.length);

        for (Button button : buttons) {
            dialogBuilderFinal.addButton(button);
        }

        return dialogBuilderFinal.build();
    }
}
