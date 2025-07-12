package rune.biomeRTP.spigot;

import alepando.dev.dialogapi.executor.PlayerOpener;
import alepando.dev.dialogapi.factory.button.Button;
import alepando.dev.dialogapi.factory.input.types.BooleanInput;
import alepando.dev.dialogapi.types.MultiActionDialog;
import net.kyori.adventure.text.Component;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import rune.biomeRTP.spigot.Dialog.DialogButton;
import rune.biomeRTP.spigot.Dialog.DialogElement;
import rune.biomeRTP.spigot.Dialog.DialogText;
import rune.biomeRTP.spigot.util.EasyDialogUtil;
import rune.biomeRTP.spigot.util.NSFUtil;

import java.util.ArrayList;
import java.util.List;

public class Screen extends EasyDialogUtil{

    public static void DoubleCheckDialog(Player p, String Biome) {
        p.playSound(p.getLocation(), "minecraft:flip", SoundCategory.MASTER, 1.0f, 1.0f);
        p.sendTitle("", "§0\uF300", 20, 200, 20);

        List<DialogElement> content = new ArrayList<>();
        Button yes = buildButton("이동하기", "클릭시 이동합니다.", 64, buildKeyedAction("biomertp", Biome));
        Button no = buildButton("돌아가기", "클릭시 돌아갑니다.", 64, buildKeyedAction("biomertp", "reopen"));

        String BiomeName = "";
        String BiomeDesc = "";
        String BiomeUnicode = "";
        if (Biome.equals("taiga")) {
            BiomeUnicode = "\uF911";
            BiomeName = "타이가";
            BiomeDesc = "가문비 나무, 고사리와 같은 양치류 식물이 자라는 차가운 바이옴";
        }
        if (Biome.equals("snowy_plains")) {
            BiomeUnicode = "\uF912";
            BiomeName = "눈덮인 평원";
            BiomeDesc = "대부분 눈으로 덮여진 평원이다.";
        }
        if (Biome.equals("beach")) {
            BiomeUnicode = "\uF913";
            BiomeName = "해변";
            BiomeDesc = "해변은 육지 바이옴과 바다와 만나는 곳에 생성된다.";
        }
        if (Biome.equals("forest")) {
            BiomeUnicode = "\uF914";
            BiomeName = "숲";
            BiomeDesc = "전 지역에 걸쳐 참나무와 자작나무가 많다. 또한 여러 꽃이 자란다.";
        }
        if (Biome.equals("savanna")) {
            BiomeUnicode = "\uF915";
            BiomeName = "사바나";
            BiomeDesc = "키 큰 잔디가 퍼져 있어 평원과 비슷해 보이지만, 이곳에서는 평원에 비해 나무가 더 많다.";
        }
        if (Biome.equals("plains")) {
            BiomeUnicode = "\uF916";
            BiomeName = "평원";
            BiomeDesc = "대부분 평평하고 경사는 드물며, 완만한 변화를 보인다. 지표면은 키 큰 잔디로 덮혀 있다.";
        }
        if (Biome.equals("jungle")) {
            BiomeUnicode = "\uF917";
            BiomeName = "정글";
            BiomeDesc = "나무 밀도가 어두운 숲과 쌍벽을 이룰 정도로 매우 빽빽하다.";
        }
        if (Biome.equals("swamp")) {
            BiomeUnicode = "\uF918";
            BiomeName = "늪지대";
            BiomeDesc = "늪의 많은 부분이 물로 덮혀 있다. 늪의 물 표면은 회색을 띈다.";
        }
        if (Biome.equals("desert")) {
            BiomeUnicode = "\uF919";
            BiomeName = "사막";
            BiomeDesc = "척박하며, 생물이 많지 않고, 식물은 선인장과 마른 덤불만 존재한다.";
        }



        content.add(new DialogText(buildCombinedText(
                buildText(BiomeName+" ("+Biome+")"))));
        content.add(new DialogText(buildCombinedText(
                buildText(BiomeDesc))));

        content.add(new DialogText(buildCombinedText(
                buildText(BiomeUnicode))));



        addShiftButtons(content, 64);
        addShiftButtons(content, 10);
        content.add(new DialogButton(yes));
        addShiftButtons(content, 42);
        content.add(new DialogButton(no));
        addShiftButtons(content, 10);
        addShiftButtons(content, 64);
        addShiftButtons(content, 64);
        addShiftButtons(content, 64);
        addShiftButtons(content, 64);


        MultiActionDialog dialog = buildEnhanceDialog(
                Component.empty(),
                content,
                new BooleanInput[]{}
        );



        PlayerOpener.INSTANCE.openDialog(p, dialog);
    }

    public static void sendBiomeRTPMapDialog(Player p) {
        p.sendTitle("", "§0\uF301", 10, 100, 10);
        Button Taiga = buildButton("타이가", "클릭시 타이가 바이옴으로 이동합니다. (Taiga)", 32, buildKeyedAction("biomertp", "suggection_taiga"));
        Button Forest = buildButton("숲", "클릭시 숲 바이옴으로 이동합니다. (Forest)", 20, buildKeyedAction("biomertp", "suggection_forest"));
        Button Savana = buildButton("사바나", "클릭시 사바나 바이옴으로 이동합니다. (Savanna)", 32, buildKeyedAction("biomertp", "suggection_savanna"));
        Button Plains = buildButton("평원", "클릭시 평원 바이옴으로 이동합니다. (Plains)", 24, buildKeyedAction("biomertp", "suggection_plains"));
        Button Jungle = buildButton("정글", "클릭시 정글 바이옴으로 이동합니다. (Jungle)", 24, buildKeyedAction("biomertp", "suggection_jungle"));
        Button Desert = buildButton("사막", "클릭시 사막 바이옴으로 이동합니다. (Desert)", 24, buildKeyedAction("biomertp", "suggection_desert"));
        Button Swamp = buildButton("늪지대", "클릭시 늪지대 바이옴으로 이동합니다. (Swamp)", 32, buildKeyedAction("biomertp", "suggection_swamp"));
        Button Snowy_Plains = buildButton("눈덮인 평원", "클릭시 눈덮인 평원 평지로 이동합니다. (Snowy Plains)", 50, buildKeyedAction("biomertp", "suggection_snowy_plains"));
        Button Beach = buildButton("해변", "클릭시 해변 평지로 이동합니다. (Beach)", 24, buildKeyedAction("biomertp", "suggection_beach"));


        Component BlankText = buildText("");

        List<DialogElement> content = new ArrayList<>();
        content.add(new DialogText(buildCombinedText(
                NSFUtil.space(0),
                buildText("\uF901\uF101\uF902"),
                NSFUtil.space(-228),
                buildText("\uF903\uF101\uF904"))));
        addShiftButtons(content, 28);
        content.add(new DialogButton(Desert));
        addShiftButtons(content, 12);
        content.add(new DialogButton(Jungle));
        addShiftButtons(content, 22);

        addShiftButtons(content, 24);
        content.add(new DialogButton(Plains));
        addShiftButtons(content, 14);
        content.add(new DialogButton(Forest));
        addShiftButtons(content, 24);

        addShiftButtons(content, 64);

        addShiftButtons(content, 64);

        addShiftButtons(content, 24);
        content.add(new DialogButton(Savana));
        addShiftButtons(content, 14);
        content.add(new DialogButton(Swamp));
        addShiftButtons(content, 24);

        addShiftButtons(content, 64);

        addShiftButtons(content, 28);
        content.add(new DialogButton(Snowy_Plains));
        addShiftButtons(content, 35);

        addShiftButtons(content, 24);
        content.add(new DialogButton(Taiga));
        addShiftButtons(content, 16);
        content.add(new DialogButton(Beach));
        addShiftButtons(content, 22);

        addShiftButtons(content, 64);
        MultiActionDialog dialog = buildEnhanceDialog(
                Component.empty(),
                content,
                new BooleanInput[]{}
        );



        PlayerOpener.INSTANCE.openDialog(p, dialog);
    }

    public static void addShiftButtons(List<DialogElement> content, int count) {
        Button shiftButton = buildButton("", "", 1, buildKeyedAction("biomertp", "reopen"));
        for (int i = 0; i < count; i++) {
            content.add(new DialogButton(shiftButton));
        }
    }
}
