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
    protected Boolean editable = false;

    /**
     * Constructeur
     * @param owner
     */
    public DataViewTableController(Window owner) {
        super(owner);
    }

    /**
     * Charge les données à afficher
     * @param data
     */
    public void setData(ObservableList<ConverterTableViewColumn> data){
        this.data = data;
    }

    public ObservableList<ConverterTableViewColumn> getData() {
        return data;
    }

    /**
     * Détermine si le TableView est editable
     * Par defaut : False
     * @param editable
     */
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    @Override
    protected AnchorPane displayContent() {
        TableView<ConverterTableViewColumn> tableView = new TableView<>();
        tableView.setEditable(editable);
        tableView.getColumns().setAll(getContent());
        tableView.setItems(data);
        AnchorPane node = new DefaultAnchorPaneZero(tableView);
        node.setPadding(new Insets(5.0));
        return node;
    }
}
