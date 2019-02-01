package org.bird.gui.listeners;

import org.bird.gui.events.OnRightClickEvent;

import java.util.EventListener;

public interface OnRightClickListener extends EventListener {

    public abstract void onRightClick(OnRightClickEvent evt);

}
