package org.bird.gui.controllers;

import org.bird.gui.listeners.OnLeftClickListener;

public interface IDataSheetController<T> {

    public void update(T item);

    public void addOnLeftClickListener(OnLeftClickListener listener);

}
