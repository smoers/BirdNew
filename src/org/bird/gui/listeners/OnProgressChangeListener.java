package org.bird.gui.listeners;

import org.bird.gui.events.OnProgressChangeEvent;

import java.util.EventListener;

public abstract interface OnProgressChangeListener extends EventListener {

    public abstract void onProcessChange(OnProgressChangeEvent evt);
}
