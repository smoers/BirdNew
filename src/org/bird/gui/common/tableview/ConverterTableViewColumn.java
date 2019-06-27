package org.bird.gui.common.tableview;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;

/**
 * Cette classe est chargée avec la liste des objects SimpleObjectProperty
 * Cet objet contient la données à afficher dans la cellule
 */
public class ConverterTableViewColumn {

    protected HashMap<String, SimpleObjectProperty> properties = new HashMap<>();
    protected Object source;

    /**
     * Constructeur
     * @param source
     */
    public ConverterTableViewColumn(Object source) {
        this.source = source;
    }

    /**
     * Le nom de la colonne et l'object à afficher
     * @param columnName
     * @param initialValue
     * @param <T>
     */
    public <T> void set(String columnName, T initialValue){
        set(new SimpleObjectProperty<T>(null, columnName, initialValue));
    }

    /**
     * La propriété contenant l'objet à afficher
     * @param property
     * @param <T>
     */
    public <T> void set(SimpleObjectProperty<T> property){
        if (!properties.containsKey(property.getName())) {
            properties.put(property.getName(),property);
        }
    }

    /**
     * Retourne l'ObservableValue de la colonne dont le nom est passé en paramêtre
     * @param columnName
     * @param <T>
     * @return
     */
    public <T> ObservableValue<T> get(String columnName){
        if (properties.containsKey(columnName)){
            return properties.get(columnName);
        }
        return null;
    }

    /**
     * Retourne l'objet source
     * @return
     */
    public Object getSource() {
        return source;
    }
}
