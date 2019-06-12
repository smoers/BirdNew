package org.bird.gui.common.i18n;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

public class TranslatorControlMenuButton extends TranslatorControl {

    public TranslatorControlMenuButton(Node node) {
        super(node);
    }

    @Override
    public ObservableList<Node> getNodes() {
        ObservableList<MenuItem> nodes = FXCollections.observableArrayList();

        return null;
    }
}
