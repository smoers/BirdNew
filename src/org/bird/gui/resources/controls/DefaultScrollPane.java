package org.bird.gui.resources.controls;

import javafx.scene.control.ScrollPane;

public class DefaultScrollPane extends ScrollPane {

    public DefaultScrollPane() {
        setFitToHeight(true);
        setFitToWidth(true);
        setPannable(true);
    }
}
