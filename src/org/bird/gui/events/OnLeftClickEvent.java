package org.bird.gui.events;

import java.util.EventObject;

public class OnLeftClickEvent extends EventObject {

    private int clickCount;
    private String id;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public OnLeftClickEvent(Object source, int clickCount) {
        super(source);
        this.clickCount = clickCount;
    }

    public OnLeftClickEvent(Object source, int clickCount, String id){
        super(source);
        this.clickCount = clickCount;
        this.id = id;
    }

    public int getClickCount() {
        return clickCount;
    }
}
