package org.bird.gui.listeners;

import org.bird.gui.events.OnLeftClickEvent;

import java.util.EventListener;

public interface OnLeftClickListener extends EventListener {

    public abstract void onLeftClick(OnLeftClickEvent evt);

}
