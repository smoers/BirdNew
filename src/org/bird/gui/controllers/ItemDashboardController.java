package org.bird.gui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.bird.db.models.Author;
import org.bird.db.models.Book;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnRightClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnRightClickListener;
import org.bird.gui.resources.images.ImageProvider;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringJoiner;

/**
 * Controller de gestion de l'affichage des Item du dashboard
 */
public class ItemDashboardController implements Initializable {

    @FXML
    private VBox container;
    @FXML
    private ImageView picture;
    @FXML
    private Label label01;
    @FXML
    private Label label02;
    /**
     * Event
     */
    private ArrayList<OnRightClickListener> onRightClickListeners = new ArrayList<>();
    private ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    /**
     * Constructeur
     */
    public ItemDashboardController() {}

    /**
     * Si on aujoute un auteur
     * @param author
     */
    public void setItem(Author author){
        label01.setText(author.getFullName());
        label02.setVisible(false);
        ImageProvider imageProvider = new ImageProvider(author.getPicture());
        picture.setImage(imageProvider.getImage());
    }

    /**
     * Si on ajoute un livre
     * @param book
     */
    public void setItem(Book book){
        List<Author> authors = book.getCycle().getAuthors();
        StringJoiner joiner = new StringJoiner("\n\r");
        for (Author author : authors) {
            joiner.add(author.getFullName());
        }
        label01.setText(joiner.toString());
        label02.setText(book.getTitle());
        ImageProvider imageProvider = new ImageProvider(book.getPicture());
        picture.setImage(imageProvider.getImage());
    }

    /**
     * Permet de charger un objet en précisant le type lors de l'exécution
     * @param objet
     * @param <T>
     */
    public <T> void setItem(T objet){
        if (objet instanceof Author){
            setItem((Author) objet);
        } else if (objet instanceof Book){
            setItem((Book) objet);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        container.getStyleClass().add("item_container");
        label01.getStyleClass().add("item_author");
        label02.getStyleClass().add("item_book");
        container.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()){
                    notifyOnLeftClickListener(new OnLeftClickEvent(container, mouseEvent.getClickCount()));
                } else if (mouseEvent.isSecondaryButtonDown()){
                    notifyOnRightClickListener(new OnRightClickEvent(container, mouseEvent.getClickCount()));
                }
            }
        });
    }

    /**
     * Ajoute un listener sur le click droit de la souris
     * @param listener
     */
    public void addOnRightClickListener(OnRightClickListener listener){
        onRightClickListeners.add(listener);
    }

    /**
     * Ajoute un listener sur le click gauche de la souris
     * @param listener
     */
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Notifie le listener du click droit
     * @param evt
     */
    private void notifyOnRightClickListener(OnRightClickEvent evt){
        for (OnRightClickListener listener : onRightClickListeners){
            listener.onRightClick(evt);
        }
    }

    /**
     * Notifie le listener du click gauche
     * @param evt
     */
    private void notifyOnLeftClickListener(OnLeftClickEvent evt){
        for (OnLeftClickListener listener : onLeftClickListeners){
            listener.onLeftClick(evt);
        }
    }

}
