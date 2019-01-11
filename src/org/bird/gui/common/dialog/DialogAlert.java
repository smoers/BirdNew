package org.bird.gui.common.dialog;

import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class DialogAlert extends DialogExtended<ButtonType> {

    private HashMap<String,String> languages;

    public DialogAlert(List<ButtonType> buttonTypes) {
        super();
        initialize(buttonTypes);
    }

    private void initialize(List<ButtonType> buttonTypes){

        //Ajoute les boutons
        getDialogPane().getButtonTypes().addAll(buttonTypes);

    }

    public void setLanguage(ResourceBundle resourceBundle){



    }
}
