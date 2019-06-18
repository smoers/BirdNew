package org.bird.gui.controllers;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import org.bird.gui.resources.controls.DefaultAnchorPaneZero;

/**
 * Etend la classe DataViewController afin d'afficher
 * dans un ListView la liste passé en content
 */
public class DataViewObservableListController extends DataViewController<ObservableList<String>> {

    public DataViewObservableListController(Window owner) {
        super(owner);
    }

    @Override
    protected AnchorPane displayContent() {
        ListView<String> lvList = new ListView<>();
        lvList.setEditable(isShowSave());
        lvList.setItems(getContent());
        AnchorPane node = new DefaultAnchorPaneZero(lvList);
        node.setPadding(new Insets(5.0));
        return node;
    }
}
