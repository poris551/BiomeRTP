package rune.biomeRTP.spigot.Dialog;

import alepando.dev.dialogapi.factory.button.Button;

public class DialogButton implements DialogElement {
    private final Button button;

    public DialogButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }
}