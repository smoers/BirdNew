package org.bird.gui.resources.controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.FlowPane;

/**
 * FlowPane avec les bon paramêtres
 */
public class DefaultFlowPane extends FlowPane {

    public DefaultFlowPane() {
        setPadding(new Insets(10.0,10.0,10.0,10.0));
        setBlendMode(BlendMode.MULTIPLY);
        setHgap(10.0);
        setVgap(10.0);
        setRowValignment(VPos.TOP);
        setAlignment(Pos.TOP_LEFT);
    }
}
