package org.bird.gui.common;


import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class PropertyValurFactoryExtended<S,T> implements Callback<TableColumn.CellDataFeatures<S,T>, ObservableValue<T>> {

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<S,T> cellDataFeatures) {
        return null;
    }
}
