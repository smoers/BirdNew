package org.bird.gui.common.tableviewextended;

import javafx.scene.control.TableColumn;
import org.bird.gui.common.ConverterTableViewColumn;

public abstract class TableColumnExtended<T> implements ITableColumnExtended<T> {

    protected String title;
    protected String propertyName;
    protected Class<?> transposer;

    public TableColumnExtended(String title, String propertyName, Class<?> transposer) {
        this.title = title;
        this.propertyName = propertyName;
        this.transposer = transposer;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public abstract TableColumn<ConverterTableViewColumn, T> get();

    @Override
    public Class<?> getTransposer() {
        return transposer;
    }
}