package org.bird.gui.controllers.display;

import org.bird.db.query.Paginator;

import java.io.IOException;

public interface IDisplayItemDashboard<T> {

    public void display(Paginator<T> paginator) throws IOException;

}
