package org.bird.gui.resources.controls;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class LabelObservableList<T> extends Label {

    protected ObservableList<T> observableList = null;



    public LabelObservableList(ObservableList<T> observableList) {
        super("...");
        this.observableList = observableList;
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }
}
