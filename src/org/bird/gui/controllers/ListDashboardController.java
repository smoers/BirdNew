package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.bird.gui.common.tableview.ConverterTableViewColumn;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ListDashboardController<T> extends ProtectedController implements Initializable {

    @FXML
    private AnchorPane apListContainer;
    private TableView<ConverterTableViewColumn> tableView;

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
    }

    public TableView<ConverterTableViewColumn> getTableView() {
        return tableView;
    }

    @Override
    public void setLanguage() {
        tableView.getColumns().forEach(new Consumer<TableColumn<ConverterTableViewColumn, ?>>() {
            @Override
            public void accept(TableColumn<ConverterTableViewColumn, ?> tableColumn) {
                tableColumn.setText(getInternationalizationBundle().getString(tableColumn.getText()));
            }
        });
    }

}
