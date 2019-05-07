package org.bird.gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.bird.db.models.Author;
import org.bird.gui.common.ConverterTableViewColumn;
import org.bird.gui.events.OnSelectedEvent;
import org.bird.gui.listeners.OnSelectedListener;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ListDashboardController<T> extends ProtectedController implements Initializable {

    @FXML
    private AnchorPane apListContainer;
    private TableView<ConverterTableViewColumn<ImageView, Void, Void>> tableView;
    /**
     * Event
     */
    private ArrayList<OnSelectedListener<T>> onSelectedListeners = new ArrayList<>();

    public ListDashboardController() {
        setInternationalizationBundle(internationalizationBuilder.getInternationalizationBundle(getClass()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView = new TableView<>();
        AnchorPane.setBottomAnchor(tableView,0.0);
        AnchorPane.setLeftAnchor(tableView,0.0);
        AnchorPane.setRightAnchor(tableView,0.0);
        AnchorPane.setTopAnchor(tableView,0.0);
        apListContainer.getChildren().add(tableView);
        // Event
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConverterTableViewColumn<ImageView, Void, Void>>() {
            @Override
            public void changed(ObservableValue<? extends ConverterTableViewColumn<ImageView, Void, Void>> observableValue, ConverterTableViewColumn<ImageView, Void, Void> oldVal, ConverterTableViewColumn<ImageView, Void, Void> newVal) {

                Author author = (Author) newVal.getSource();
                System.out.println(author.getFullName());

            }
        });

    }

    public TableView<ConverterTableViewColumn<ImageView, Void, Void>> getTableView() {
        return tableView;
    }

    @Override
    public void setLanguage() {
        tableView.getColumns().forEach(new Consumer<TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, ?>>() {
            @Override
            public void accept(TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, ?> tableColumn) {
                tableColumn.setText(getInternationalizationBundle().getString(tableColumn.getText()));
            }
        });
    }

    public void addOnSelectedListener(OnSelectedListener<T> listener){
        onSelectedListeners.add(listener);
    }

    private void notifyOnSelectedListener(OnSelectedEvent<T> evt){
        onSelectedListeners.forEach(new Consumer<OnSelectedListener<T>>() {
            @Override
            public void accept(OnSelectedListener<T> onSelectedListener) {
                onSelectedListener.OnSelected(evt);
            }
        });
    }

}
