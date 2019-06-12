package org.bird.gui.common.dialog;

import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import org.bird.gui.resources.images.ImageProvider;

public class DialogPrompt extends TextInputDialog {

    public DialogPrompt() {
        super();
        setIcon();
    }

    public DialogPrompt(String s) {
        super(s);
        setIcon();
    }

    protected void setIcon(){
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(ImageProvider.getLogoImage());
    }
}
