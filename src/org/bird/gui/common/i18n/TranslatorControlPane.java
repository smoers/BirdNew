package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Retourne le node contenu dans l'objet TitledPane
 */
public class TranslatorControlPane extends TranslatorControl {

    /**
     *
     * @param node
     */
    public TranslatorControlPane(Node node) {
        super(node);
    }

    /**
     * Retourne le node dans un objet ObservableList<Node>
     * @return ObservableList<Node>
     */
    @Override
    public ObservableList<Node> getNodes() {
        return ((Pane) node).getChildren();
    }
}
