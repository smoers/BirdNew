package org.bird.gui.controllers.display;

import org.bird.db.query.Paginator;
import org.bird.gui.common.progress.IOnWaitingBarListener;
import org.bird.gui.listeners.OnSelectedListener;

import java.io.IOException;

/**
 * Permet de rendre un objet utilisable par un paginator
 * @param <T>
 */
public interface IDisplayDashboard<T> extends IOnWaitingBarListener {

    public void display(Paginator<T> paginator) throws IOException;

    public void addOnSelectedListener(OnSelectedListener<T> onSelectedListener);
}
