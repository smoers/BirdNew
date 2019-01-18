package org.bird.gui.common.dialog;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.bird.i18n.InternationalizationBundle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    protected String getPrintStackTrace(Exception e){

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    //Static methods

    /**
     * Créer le bouton et les retournes dans une liste
     * @param type
     * @param internationalizationBundle
     * @return
     */
    public static List<ButtonType> getListButtons(DialogExtended.TYPE type, InternationalizationBundle internationalizationBundle){

        ArrayList<DialogExtended.TYPE> types = new ArrayList<>();
        types.add(type);
        return DialogExtended.getListButtons(types, internationalizationBundle);
    }

    /**
     * Créer les boutons et les retournes dans une liste
     * @param types
     * @param internationalizationBundle
     * @return
     */
    public static List<ButtonType> getListButtons(List<DialogExtended.TYPE> types, InternationalizationBundle internationalizationBundle) {
        ArrayList<ButtonType> buttons = new ArrayList<>();
        Iterator<DialogExtended.TYPE> iterator = types.iterator();
        while (iterator.hasNext()) {
            DialogExtended.TYPE type = iterator.next();
            switch (type) {
                case BUTTON_YES:
                    buttons.add(new ButtonType(getInternationalization("Yes", internationalizationBundle), ButtonBar.ButtonData.YES));
                    break;
                case BUTTON_NO:
                    buttons.add(new ButtonType(getInternationalization("No", internationalizationBundle), ButtonBar.ButtonData.NO));
                    break;
                case BUTTON_SAVE:
                    buttons.add(new ButtonType(getInternationalization("Save", internationalizationBundle), ButtonBar.ButtonData.OK_DONE));
                    break;
                case BUTTON_CANCEL:
                    buttons.add(new ButtonType(getInternationalization("Cancel", internationalizationBundle), ButtonBar.ButtonData.CANCEL_CLOSE));
                    break;
                case BUTTON_YES_NO:
                    buttons.add(new ButtonType(getInternationalization("Yes", internationalizationBundle), ButtonBar.ButtonData.YES));
                    buttons.add(new ButtonType(getInternationalization("No", internationalizationBundle), ButtonBar.ButtonData.NO));
                    break;
                case BUTTON_OK_CANCEL:
                    buttons.add(new ButtonType(getInternationalization("Ok", internationalizationBundle), ButtonBar.ButtonData.OK_DONE));
                    buttons.add(new ButtonType(getInternationalization("Cancel", internationalizationBundle), ButtonBar.ButtonData.CANCEL_CLOSE));
                    break;
                case BUTTON_SAVE_CANCEL:
                    buttons.add(new ButtonType(getInternationalization("Save", internationalizationBundle), ButtonBar.ButtonData.OK_DONE));
                    buttons.add(new ButtonType(getInternationalization("No", internationalizationBundle), ButtonBar.ButtonData.NO));
                    break;
            }
        }
        System.out.println(buttons.size());
        return buttons;
    }

    protected static String getInternationalization(String key, InternationalizationBundle internationalizationBundle){

        String result  = key;
        if (internationalizationBundle != null){
            return internationalizationBundle.getString(key);
        }
        return result;
    }

}
