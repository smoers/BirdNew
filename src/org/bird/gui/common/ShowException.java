package org.bird.gui.common;

import javafx.application.Platform;
import org.bird.gui.common.dialog.DialogAlert;
import org.bird.gui.common.dialog.DialogExtended;
import org.bird.i18n.InternationalizationBuilder;
import org.bird.i18n.InternationalizationBundle;

/**
 * Cette classe est destinée à l'affichage "Graphique" des erreurs lorsque l'on n'est pas
 * dans le thread graphique
 */
public class ShowException {

    private Exception e;

    /**
     * Constructeur
     * @param e
     */
    public ShowException(Exception e){
        this.e = e;
    }

    /**
     * Affiche le message en tenant compte de la langue
     * @param clazz
     */
    public void show(Class clazz){
        //System.out.println(e.getMessage());
        InternationalizationBuilder builder = InternationalizationBuilder.getInstance();
        InternationalizationBundle bundle = builder.getInternationalizationBundle(clazz);
        show(bundle);
    }

    /**
     * Affiche le message en tenant compte de la langue
     * @param key
     */
    public void show(String key){
        //System.out.println(e.getMessage());
        InternationalizationBuilder builder = InternationalizationBuilder.getInstance();
        InternationalizationBundle bundle = builder.getInternationalizationBundle(key);
        show(bundle);
    }

    /**
     * Affiche le message en tenant compte de la langue
     * @param bundle
     */
    public void show(InternationalizationBundle bundle){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                DialogAlert alert = new DialogAlert(DialogExtended.TYPE.BUTTON_CANCEL,"Error",getClass().getCanonicalName(),e.getMessage(),e,bundle);
                alert.showAndWait();
            }
        });
    }

}
