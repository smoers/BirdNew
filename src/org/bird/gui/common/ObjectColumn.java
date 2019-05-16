package org.bird.gui.common;

import javafx.beans.property.SimpleObjectProperty;

public class ObjectColumn<T> {

    protected SimpleObjectProperty<T> objectProperty;
    protected String columnName;

    public ObjectColumn(T object, String columnName) {
        this.columnName = columnName;
        objectProperty().set(object);
    }

    public SimpleObjectProperty<T> objectProperty(){
        if (objectProperty == null) objectProperty = new SimpleObjectProperty<T>(this,null);
        return objectProperty;
    }

    public T get(){ return objectProperty().get();}

    public String getColumnName() {
        return columnName;
    }
}
