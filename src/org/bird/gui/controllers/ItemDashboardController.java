package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemDashboardController implements Initializable {

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
        author.getStyleClass().add("itemdashboard_author");
        book.getStyleClass().add("itemdashboard_book");
    }
}
