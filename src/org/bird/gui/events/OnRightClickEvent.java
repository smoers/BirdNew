package org.bird.gui.events;

import java.util.EventObject;

public class OnRightClickEvent extends EventObject {

    private int clickCount;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public OnRightClickEvent(Object source,int clickCount) {
        super(source);
        this.clickCount = clickCount;
    }
}
