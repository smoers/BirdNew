package org.bird.gui.listeners;

import org.bird.gui.events.OnPageChangeEvent;

import java.util.EventListener;

public abstract interface OnPageChangeListener extends EventListener {

    public abstract void onChangePage(OnPageChangeEvent evt);

}
