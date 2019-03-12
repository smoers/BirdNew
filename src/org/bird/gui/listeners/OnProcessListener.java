package org.bird.gui.listeners;

import org.bird.gui.events.OnProcessEvent;

import java.util.EventListener;

/**
 * Cette interface doit être implémentée
 * pour répondre à un event levé au début ou à la fin
 * d'un processus
 */
public abstract interface OnProcessListener extends EventListener {

    public abstract void onProcess(OnProcessEvent evt);

}
