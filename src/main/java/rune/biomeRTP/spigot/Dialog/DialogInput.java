package rune.biomeRTP.spigot.Dialog;

import alepando.dev.dialogapi.factory.input.types.BooleanInput;

public class DialogInput implements DialogElement {
    private final BooleanInput input;

    public DialogInput(BooleanInput button) {
        this.input = button;
    }

    public BooleanInput getButton() {
        return input;
    }
}