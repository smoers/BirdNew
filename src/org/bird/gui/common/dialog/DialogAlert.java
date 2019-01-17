package org.bird.gui.common.dialog;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.bird.i18n.InternationalizationBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class DialogAlert extends DialogExtended<ButtonType> {

    private InternationalizationBundle internationalizationBundle = null;

    public DialogAlert(List<ButtonType> buttonTypes, String title, String message, String context){
        super();
        initialize(buttonTypes,title, message, context);
    }

    public DialogAlert(List<ButtonType> buttonTypes, String title, String message) {
        super();
        initialize(buttonTypes, title, message, null);
    }

    public DialogAlert(DialogExtended.TYPE type, String title, String message, InternationalizationBundle internationalizationBundle){
        super();
        this.internationalizationBundle = internationalizationBundle;

    }

    public DialogAlert(DialogExtended.TYPE type, String title, String message, String context, InternationalizationBundle internationalizationBundle){
        this.internationalizationBundle = internationalizationBundle;
    }

    private void initialize(List<ButtonType> buttonTypes, String title, String message, String context){

        //Ajoute les boutons
        getDialogPane().getButtonTypes().addAll(buttonTypes);

    }

    protected List<ButtonType> getButtons(DialogExtended.TYPE type){
        ArrayList<ButtonType> buttons = new ArrayList<>();
        switch (type){
            case BUTTON_YES_NO:
                buttons.add(new ButtonType("Yes", ButtonBar.ButtonData.YES));
                buttons.add(new ButtonType("No", ButtonBar.ButtonData.NO));
            case BUTTON_OK_CANCEL:
                buttons.add(new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE));
                buttons.add(new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));
            case BUTTON_SAVE_CANCEL:
                buttons.add(new ButtonType("Save", ButtonBar.ButtonData.OK_DONE));
                buttons.add(new ButtonType("No", ButtonBar.ButtonData.NO));
        }

        return buttons;
    }

    protected String setLanguage(String key){

        String result  = key;
        if (internationalizationBundle != null){
            return internationalizationBundle.getString(key);
        }
        return result;
    }
}
