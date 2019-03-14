package org.bird.gui.controllers.display;

import org.bird.db.query.Paginator;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.listeners.OnProgressChangeListener;

import java.io.IOException;
import java.util.ArrayList;

public abstract class DisplayItemDashboard<T> implements IDisplayItemDashboard<T> {

    private ArrayList<OnProgressChangeListener> onProgressChangeListeners = new ArrayList<>();

    @Override
    public abstract void display(Paginator<T> paginator) throws IOException;

    @Override
    public void addOnProgressChangeListener(OnProgressChangeListener listener) {
        onProgressChangeListeners.add(listener);
    }

    protected void notifyOnProgressChangeListener(OnProgressChangeEvent evt){
        for (OnProgressChangeListener listener : onProgressChangeListeners){
            listener.onProcessChange(evt);
        }
    }
}
