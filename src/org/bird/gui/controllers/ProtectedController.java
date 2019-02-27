package org.bird.gui.controllers;

import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.common.dialog.DialogAlert;
import org.bird.gui.common.dialog.DialogExtended;
import org.bird.i18n.InternationalizationController;

import java.util.ListIterator;
import java.util.function.Predicate;

/**
 * cette classe doit être le parent de tous les 'controller' graphique
 */
public abstract class ProtectedController extends InternationalizationController {

    /**
     * Instance
     */
    protected ConfigurationBuilder configurationBuilder = ConfigurationBuilder.getInstance();

    /**
     * Retourne la configuration liée au layout de l'application
     * Voir le fichier de configuration config/layout.json
     * @return
     * @throws ConfigurationException
     */
    protected Configuration getConfigurationLayout() throws ConfigurationException {
        return configurationBuilder.get("layout");
    }

    /**
     * Permet d'afficher ou de ne pas afficher les textes des boutons dont le parent est un objet Pane
     * @param pane
     * @throws ConfigurationException
     */
    protected void setText(Pane pane) throws ConfigurationException {
        if (!getConfigurationLayout().get("layout.iftext.button").getAsBoolean()) {
            FilteredList<Node> list = pane.getChildren().filtered(new Predicate<Node>() {
                @Override
                public boolean test(Node node) {
                    return node instanceof Button;
                }
            });
            applyButtonText(list);
        }
    }

    /**
     * Permet d'afficher ou de ne pas afficher les textes des boutons dont le parent est un objet Toolbar
     * @param toolBar
     * @throws ConfigurationException
     */
    protected void setText(ToolBar toolBar) throws ConfigurationException {
        if (!getConfigurationLayout().get("layout.iftext.button").getAsBoolean()) {
            FilteredList<Node> list = toolBar.getItems().filtered(new Predicate<Node>() {
                @Override
                public boolean test(Node node) {
                    return node instanceof Button;
                }
            });
            applyButtonText(list);
        }
    }

    /**
     * Facilite la gestion des exceptions en centralisant l'affichage
     * @param e
     */
    protected void shoxException(Exception e){
        System.out.println(e.getMessage());
        DialogAlert alert = new DialogAlert(DialogExtended.TYPE.BUTTON_CANCEL,"Error",getClass().getCanonicalName(),e.getMessage(),e,getInternationalizationBundle());
        alert.showAndWait();
    }

    /**
     * Si pas de texte dans les boutons, on charge une chaine vide.
     * @param list
     */
    private void applyButtonText(FilteredList<Node> list){
        ListIterator<Node> it = list.listIterator();
        while (it.hasNext()) {
            Button button = (Button) it.next();
            button.setText("");
        }
    }

}
