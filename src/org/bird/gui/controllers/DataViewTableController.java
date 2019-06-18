package org.bird.gui.controllers;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import org.bird.gui.common.ConverterTableViewColumn;
import org.bird.gui.resources.controls.DefaultAnchorPaneZero;

/**
 * Etend la classe DataViewController afin d'afficher
 * dans un TableView la liste des colones passées en content
 */
public class DataViewTableController extends DataViewController<ObservableList<TableColumn<ConverterTableViewColumn,?>>>  {

    protected ObservableList<ConverterTableViewColumn> data;

    public DataViewTableController(Window owner) {
        super(owner);
    }

    public void setData(ObservableList<ConverterTableViewColumn> data){
        this.data = data;
    }

    @Override
    protected AnchorPane displayContent() {
        TableView<ConverterTableViewColumn> tableView = new TableView<>();
        tableView.getColumns().setAll(getContent());
        AnchorPane node = new DefaultAnchorPaneZero(tableView);
        node.setPadding(new Insets(5.0));
        return node;
    }
}
