package org.bird.gui.events;

import java.util.EventObject;

public class OnPageChange extends EventObject {

    public enum TYPE{
        FIRST,
        PREVIOUS,
        NEXT,
        LAST
    }

    private TYPE type;
    private int page;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public OnPageChange(Object source,TYPE type, int page) {
        super(source);
        this.type = type;
        this.page = page;
    }

    public TYPE getType() {
        return type;
    }

    public int getPage() {
        return page;
    }
}
