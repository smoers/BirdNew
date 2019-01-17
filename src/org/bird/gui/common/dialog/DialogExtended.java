package org.bird.gui.common.dialog;

import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class DialogExtended<R> extends Dialog<R> {

    public static enum TYPE {

        BUTTON_YES,
        BUTTON_NO,
        BUTTON_CANCEL,
        BUTTON_SAVE,
        BUTTON_YES_NO,
        BUTTON_OK_CANCEL,
        BUTTON_SAVE_CANCEL

    }

    protected GridPane getExpandableContext(String text){

        TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);

        return expContent;
    }


}
