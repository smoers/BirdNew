package org.bird.gui.listeners;

import org.bird.gui.events.OnSelectedEvent;
import java.util.EventListener;

/**
 * Ce listener est utilisé lorsqu'un item est sélectionné
 * @param <T>
 */
public interface OnSelectedListener<T> extends EventListener {

    public void OnSelected(OnSelectedEvent<T> evt);
}
