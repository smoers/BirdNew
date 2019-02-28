package org.bird.gui.listeners;

import org.bird.gui.events.OnPaginatorChangePageEvent;

import java.util.EventListener;

public interface OnPaginatorChangePageListener<T> extends EventListener {

    public abstract void onPaginatorChangePage(OnPaginatorChangePageEvent<T> evt);
}
