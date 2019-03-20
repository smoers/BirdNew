package org.bird.gui.controllers.display;

import org.bird.db.query.Paginator;
import org.bird.gui.common.progress.IOnWaitingBarListener;

import java.io.IOException;

/**
 * Permet de rendre un objet utilisable par un paginator
 * @param <T>
 */
public interface IOnDisplayItemDashboardChange<T> extends IOnWaitingBarListener {

    public void display(Paginator<T> paginator) throws IOException;

}
