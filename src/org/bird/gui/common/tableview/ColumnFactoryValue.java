package org.bird.gui.common.tableview;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ColumnFactoryValue<T> implements Callback<TableColumn.CellDataFeatures<ConverterTableViewColumn,T>, ObservableValue<T>> {

    protected String columnName;

    public ColumnFactoryValue(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<ConverterTableViewColumn,T> cellDataFeatures) {
        return cellDataFeatures.getValue().<T>get(columnName);
    }
}
