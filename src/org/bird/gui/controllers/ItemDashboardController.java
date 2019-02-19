package org.bird.gui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnRightClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnRightClickListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemDashboardController implements Initializable {

    @FXML
    private VBox container;
    @FXML
    private ImageView image;
    @FXML
    private Label author;
    @FXML
    private Label book;
    /**
     * Event
     */
    private ArrayList<OnRightClickListener> onRightClickListeners = new ArrayList<>();
    private ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    public ItemDashboardController() {}

    public void setImage(Image image){
        this.image.setImage(image);
    }

    public void setAuthor(String author){
        this.author.setText(author);
    }

    public void setBook(String book){
        this.book.setText(book);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        container.getStyleClass().add("item_container");
        author.getStyleClass().add("item_author");
        book.getStyleClass().add("item_book");
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

    public void addOnRightClickListener(OnRightClickListener listener){
        onRightClickListeners.add(listener);
    }

    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    private void notifyOnRightClickListener(OnRightClickEvent evt){
        for (OnRightClickListener listener : onRightClickListeners){
            listener.onRightClick(evt);
        }
    }

    private void notifyOnLeftClickListener(OnLeftClickEvent evt){
        for (OnLeftClickListener listener : onLeftClickListeners){
            listener.onLeftClick(evt);
        }
    }
}
