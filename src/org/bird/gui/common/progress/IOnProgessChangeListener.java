package org.bird.gui.common.progress;

import org.bird.gui.listeners.OnProgressChangeListener;

/**
 * Cette interface permet de rendre un objet
 * disponible pour l'implémentation de la vue WaitingBar
 */
public interface IOnProgessChangeListener {

    public void addOnProgressChangeListener(OnProgressChangeListener listener);

}
