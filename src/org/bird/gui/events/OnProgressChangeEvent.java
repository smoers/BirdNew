package org.bird.gui.events;

import java.util.EventObject;

public class OnProgressChangeEvent extends EventObject {

    private double value;
    private double size;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public OnProgressChangeEvent(Object source, double value, double size) {
        super(source);
        this.value = value;
        this.size = size;
    }

    public double getValue() {
        return value;
    }

    public double getSize() {
        return size;
    }
}
