package org.bird.gui.controllers;

import org.bird.gui.listeners.OnLeftClickListener;

public interface DataSheetController<T> {

    public void update(T item);

    public void addOnLeftClickListener(OnLeftClickListener listener);

}
