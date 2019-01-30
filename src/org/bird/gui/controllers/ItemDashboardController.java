package org.bird.gui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
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

    public ItemDashboardController() {

    }

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
                System.out.println(mouseEvent.getButton());
                System.out.println(mouseEvent.isPrimaryButtonDown());
                System.out.println(mouseEvent.getClickCount());
            }
        });
    }
}
