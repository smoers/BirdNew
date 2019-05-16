package org.bird.gui.common;

import javafx.beans.property.SimpleStringProperty;

public class StringColumn {

    protected SimpleStringProperty stringProperty;
    protected String columnName;

    public StringColumn(String value,String columnName) {
        this.columnName = columnName;
        stringProperty().set(value);
    }

    public SimpleStringProperty stringProperty(){
        if (stringProperty == null) stringProperty = new SimpleStringProperty(this,columnName);
        return stringProperty;
    }

    public String get(){ return stringProperty().get();}

    public String getColumnName() {
        return columnName;
    }
}
