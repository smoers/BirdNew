package org.bird.gui.controllers.display;

import org.bird.db.query.Paginator;
import org.bird.gui.common.progress.IProgessListener;

import java.io.IOException;

public interface IDisplayItemDashboard<T> extends IProgessListener {

    public void display(Paginator<T> paginator) throws IOException;

}
