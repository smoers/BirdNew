package org.bird.gui.events;

import java.util.EventObject;

/**
 * Event passé lorsqu'un item est sélectionné
 * @param <T>
 */
public class OnSelectedEvent<T> extends EventObject {

    private T item;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public OnSelectedEvent(Object source, T item) {
        super(source);
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
