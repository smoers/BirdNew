package org.bird.gui.common.tableview.extended;

import javafx.scene.control.TableColumn;
import org.bird.gui.common.tableview.ConverterTableViewColumn;

/**
 *
 * @param <T>
 */
public abstract class TableColumnExtended<T> implements ITableColumnExtended<T> {

    protected String title;
    protected String propertyName;
    protected Class<?> transposer;
    protected Object defaultValue = null;

    /**
     * Constructeur
     * @param title
     * @param propertyName
     * @param transposer
     */
    public TableColumnExtended(String title, String propertyName, Class<?> transposer) {
        this.title = title;
        this.propertyName = propertyName;
        this.transposer = transposer;
    }

    /**
     * Constructeur
     * @param title
     * @param propertyName
     * @param transposer
     * @param defaultValue
     */
    public TableColumnExtended(String title, String propertyName, Class<?> transposer, Object defaultValue){
        this.defaultValue = defaultValue;
        this.title = title;
        this.propertyName = propertyName;
        this.transposer = transposer;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
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
