package rune.biomeRTP.spigot.Dialog;
import net.kyori.adventure.text.Component;

public class DialogText implements DialogElement {
    private final Component component;

    public DialogText(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }
}