package org.bird.gui.events;

import org.bird.db.query.Paginator;

import java.util.EventObject;

public class OnPaginatorChangePageEvent<T> extends EventObject {

    private Paginator<T> paginator;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public OnPaginatorChangePageEvent(Object source, Paginator<T> paginator) {
        super(source);
        this.paginator = paginator;
    }

    public Paginator<T> getPaginator() {
        return paginator;
    }
}
