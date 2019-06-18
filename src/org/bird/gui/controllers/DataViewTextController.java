package org.bird.gui.controllers;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import org.bird.gui.resources.controls.DefaultAnchorPaneZero;

/**
 * Etend la classe DataViewController afin d'afficher
 * dans un TextArea le text passé en content
 */
public class DataViewTextController extends DataViewController<String> {

    public DataViewTextController(Window owner) {
        super(owner);
    }

    @Override
    protected AnchorPane displayContent() {
        TextArea txText = new TextArea();
        txText.setWrapText(true);
        txText.setEditable(isShowSave());
        txText.setText(getContent());
        AnchorPane node = new DefaultAnchorPaneZero(txText);
        node.setPadding(new Insets(5.0));
        return node;    }
}
