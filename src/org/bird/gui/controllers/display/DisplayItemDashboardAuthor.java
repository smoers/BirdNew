package org.bird.gui.controllers.display;

import javafx.scene.layout.Pane;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;

import java.io.IOException;

/**
 * Permet l'affichage des objets Author sur le panneau
 */
public class DisplayItemDashboardAuthor extends DisplayItemDashboard<Author> {

    private Pane itemsContainer;

    /**
     * Contructeur
     * @param itemsContainer
     */
    public DisplayItemDashboardAuthor(Pane itemsContainer){
        this.itemsContainer = itemsContainer;
    }

    /**
     * Affiche les items au depart d'un objet Paginator
     * @param paginator
     * @throws IOException
     */
    @Override
    public void display(Paginator<Author> paginator) throws IOException {
        //vide le conteneur
        itemsContainer.getChildren().clear();
        //Instance du service qui se charge de l'affichage
        DisplayService<Author> service = new DisplayService<Author>(itemsContainer, paginator);
        service.start();
    }

}
