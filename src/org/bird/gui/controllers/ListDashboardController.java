package org.bird.gui.controllers;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import org.bird.db.models.Author;
import org.bird.db.models.Book;
import org.bird.gui.common.ConverterTableViewColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class ListDashboardController implements Initializable {

    @FXML
    private TableView tbItems;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setItem(Author author){
        ConverterTableViewColumn<ImageView,Void,Void> converterTableViewColumn = new ConverterTableViewColumn<>();

    }

    public void setItem(Book book){

    }

    /**
     *
     * @param object
     * @param <T>
     */
    public <T> void setItem(T object){
        if (object instanceof Author){
            setItem((Author) object);
        } else if (object instanceof Book){
            setItem((Book) object);
        }
    }

    /**
     * Cette classe standardise l'objet passé au TableView
     */
    protected class Item<T,U,V>{


    }
}
