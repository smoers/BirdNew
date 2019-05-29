package org.bird.gui.common.i18n;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;

/**
 * Retourne le node contenu dans l'objet TitledPane
 */
public class TranslatorControlTitledPane extends TranslatorControl {

    /**
     * Constructeur
     * @param node
     */
    public TranslatorControlTitledPane(Node node) {
        super(node);
    }

    /**
     * Retourne le node dans un objet ObservableList<Node>
     * @return ObservableList<Node>
     */
    @Override
    public ObservableList<Node> getNodes() {
        ObservableList<Node> nodes = FXCollections.observableArrayList();
        nodes.add(((TitledPane) node).getContent());
        return nodes;
    }
}
