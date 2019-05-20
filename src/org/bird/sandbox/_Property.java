package org.bird.sandbox;

import javafx.beans.property.SimpleObjectProperty;

public class _Property {

    public static void main(String[] args) {
        SimpleObjectProperty<String> property = new SimpleObjectProperty<>(null,"Name2","Name3");
        System.out.println(property.get());
    }
}
