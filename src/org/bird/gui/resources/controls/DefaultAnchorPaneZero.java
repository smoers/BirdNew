package org.bird.gui.resources.controls;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * AnchorPane avec une valeur de 0 pour marges
 */
public class DefaultAnchorPaneZero extends AnchorPane {

    public DefaultAnchorPaneZero(Node node) {
        AnchorPane.setTopAnchor(node,0.0);
        AnchorPane.setRightAnchor(node,0.0);
        AnchorPane.setLeftAnchor(node,0.0);
        AnchorPane.setBottomAnchor(node,0.0);
        getChildren().add(node);
    }
}
