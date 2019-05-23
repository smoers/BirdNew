package org.bird.gui.controllers;

import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class DataSheetController<T> extends ProtectedController implements IDataSheetController<T> {

    protected ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    @Override
    public abstract void update(T item);

    /**
     * Ajoute un écouteur sur les boutons
     * @param listener
     */
    @Override
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Notifie les écouteurs qu'un bouton a été pressé
     * @param evt
     */
    protected void notifyOnLeftClickListener(OnLeftClickEvent evt){
        onLeftClickListeners.forEach(new Consumer<OnLeftClickListener>() {
            @Override
            public void accept(OnLeftClickListener listener) {
                listener.onLeftClick(evt);
            }
        });
    }

    @Override
    public abstract void setLanguage();
}
