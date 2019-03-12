package org.bird.gui.events;

import java.util.EventObject;

/**
 * Cet event est levé pour signifier si un processus est
 * démarré ou terminé
 */
public class OnProcessEvent extends EventObject {

    private boolean started;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public OnProcessEvent(Object source,boolean started) {
        super(source);
        this.started = started;
    }

    /**
     * Retourne si l'event a été levé au début du processus ou à la fin de celui-ci
     * @return
     */
    public boolean isStarted() {
        return started;
    }
}
