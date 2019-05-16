package org.bird.gui.common;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;

public class ConverterTableViewColumn {

    protected HashMap<String, SimpleObjectProperty> properties = new HashMap<>();
    protected Object source;

    public ConverterTableViewColumn(Object source) {
        this.source = source;
    }

    public <T> void set(SimpleObjectProperty<T> property){
        if (!properties.containsKey(property.getName())) {
            properties.put(property.getName(),property);
        }
    }

    public <T> ObservableValue<T> get(String columnName){
        if (properties.containsKey(columnName)){
            return properties.get(columnName);
        }
        return null;
    }

    public Object getSource() {
        return source;
    }
}
