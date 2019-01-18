package org.bird.gui.common.dialog;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.bird.i18n.InternationalizationBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class DialogAlert extends DialogExtended<ButtonType> {

    // Button Type
    /**
     * Constructeur
     * @param buttonTypes
     * @param title
     * @param messageText
     */
    public DialogAlert(List<ButtonType> buttonTypes, String title, String messageText) {
        super();
        initialize(buttonTypes, title, messageText, null, null, null);
    }

    /**
     * Constructeur
     * @param buttonTypes
     * @param title
     * @param messageText
     * @param contextText
     */
    public DialogAlert(List<ButtonType> buttonTypes, String title, String messageText, String contextText){
        super();
        initialize(buttonTypes,title, messageText, contextText, null, null);
    }

    /**
     * Constructeur
     * @param buttonTypes
     * @param title
     * @param messageText
     * @param contextText
     * @param expandableContextText
     */
    public DialogAlert(List<ButtonType> buttonTypes, String title, String messageText, String contextText, String expandableContextText){
        super();
        initialize(buttonTypes,title, messageText, contextText, expandableContextText, null);
    }

    /**
     * Constructeur
     * @param buttonTypes
     * @param title
     * @param messageText
     * @param contextText
     * @param exception
     */
    public DialogAlert(List<ButtonType> buttonTypes, String title, String messageText, String contextText, Exception exception){
        super();
        initialize(buttonTypes,title, messageText, contextText, null, exception);
    }

    //List TYPE
    /**
     * Constructeur
     * @param types
     * @param title
     * @param messageText
     * @param internationalizationBundle
     */
    public DialogAlert(List<DialogExtended.TYPE> types, String title, String messageText, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(types, internationalizationBundle),title, messageText, null, null, null);
    }

    /**
     * Constructeur
     * @param types
     * @param title
     * @param messageText
     * @param contextText
     * @param internationalizationBundle
     */
    public DialogAlert(List<DialogExtended.TYPE> types, String title, String messageText, String contextText, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(types, internationalizationBundle),title, messageText, contextText, null ,null);
    }

    /**
     * Constructeur
     * @param types
     * @param title
     * @param messageText
     * @param contextText
     * @param expandableContextText
     * @param internationalizationBundle
     */
    public DialogAlert(List<DialogExtended.TYPE> types, String title, String messageText, String contextText, String expandableContextText, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(types, internationalizationBundle),title, messageText, contextText, expandableContextText, null);
    }

    /**
     * Constructeur
     * @param types
     * @param title
     * @param messageText
     * @param contextText
     * @param exception
     * @param internationalizationBundle
     */
    public DialogAlert(List<DialogExtended.TYPE> types, String title, String messageText, String contextText, Exception exception, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(types, internationalizationBundle),title, messageText, contextText, null, exception);
    }



    //TYPE Only
    /**
     * Constructeur
     * @param type
     * @param title
     * @param messageText
     * @param internationalizationBundle
     */
    public DialogAlert(DialogExtended.TYPE type, String title, String messageText, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(type, internationalizationBundle),title, messageText, null, null, null);
    }

    /**
     * Constructeur
     * @param type
     * @param title
     * @param messageText
     * @param contextText
     * @param internationalizationBundle
     */
    public DialogAlert(DialogExtended.TYPE type, String title, String messageText, String contextText, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(type, internationalizationBundle),title, messageText, contextText, null, null);
    }

    /**
     * Constructeur
     * @param type
     * @param title
     * @param messageText
     * @param contextText
     * @param expandableContextText
     * @param internationalizationBundle
     */
    public DialogAlert(DialogExtended.TYPE type, String title, String messageText, String contextText, String expandableContextText, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(type, internationalizationBundle),title, messageText, contextText, expandableContextText, null);
    }

    /**
     * Constructeur
     * @param type
     * @param title
     * @param messageText
     * @param contextText
     * @param exception
     * @param internationalizationBundle
     */
    public DialogAlert(DialogExtended.TYPE type, String title, String messageText, String contextText, Exception exception, InternationalizationBundle internationalizationBundle){
        super();
        initialize(DialogExtended.getListButtons(type, internationalizationBundle),title, messageText, contextText, null, exception);
    }

    /**
     *
     * @param buttonTypes
     * @param title
     * @param messageText
     * @param contextText
     * @param expandableContextText
     * @param exception
     */
    private void initialize(List<ButtonType> buttonTypes, String title, String messageText, String contextText, String expandableContextText, Exception exception){

        //Ajoute les boutons
        getDialogPane().getButtonTypes().addAll(buttonTypes);
        //Titre
        setTitle(title);
        //message
        setContentText(messageText);
        //entete du message
        headerTextProperty().setValue(contextText);
        // exception & texte zone expandable
        if (exception != null) {
            getDialogPane().setExpandableContent(getExpandableContext(getPrintStackTrace(exception)));
        } else if (expandableContextText != null){
            getDialogPane().setExpandableContent(getExpandableContext(expandableContextText));
        }


    }

}
