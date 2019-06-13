package org.bird.gui.common.i18n;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

/**
 * Retourne le node contenu dans l'objet TitledPane
 */
public class TranslatorControlScrollPane extends TranslatorControl<Node> {

    /**
     *
     * @param node
     */
    public TranslatorControlScrollPane(Object node) {
        super(node);
    }

    /**
     * Retourne le node dans un objet ObservableList<Node>
     * @return ObservableList<Node>
     */
    @Override
    public ObservableList<Node> getNodes() {
        ObservableList<Node> nodes = FXCollections.observableArrayList();
        nodes.add(((ScrollPane) node).getContent());
        return nodes;
    }
}
