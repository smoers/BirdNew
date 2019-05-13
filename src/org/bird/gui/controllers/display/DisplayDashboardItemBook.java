package org.bird.gui.controllers.display;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.bird.db.models.Book;
import org.bird.db.query.Paginator;

import java.io.IOException;

/**
 * Permet l'afficahge des livres
 */
public class DisplayDashboardItemBook extends DisplayDashboardItem<Book> {
    /**
     * Contructeur
     *
     * @param itemsContainer
     */
    public DisplayDashboardItemBook(ObservableList<Node> itemsContainer) {
        super(itemsContainer);
    }

    @Override
    public void display(Paginator<Book> paginator) throws IOException {
        //Vide le container
        itemsContainer.getChildren().clear();
        //Instance du service qui se charge de l'afficahge
        DisplayService<Book> service = new DisplayService<Book>(itemsContainer, paginator);
        service.start();
    }
}
