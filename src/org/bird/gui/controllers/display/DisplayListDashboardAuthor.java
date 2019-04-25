package org.bird.gui.controllers.display;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.common.ConverterTableViewColumn;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;

public class DisplayListDashboardAuthor extends DisplayListDashboard<Author> {

    private TableView<ConverterTableViewColumn<ImageView, Void, Void>> tableView;

    public DisplayListDashboardAuthor(TableView<ConverterTableViewColumn<ImageView, Void, Void>> tableView) {
        this.tableView = tableView;
    }

    @Override
    public void display(Paginator<Author> paginator) throws IOException {
        TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, ImageView> imageCol = new TableColumn<>("Images");
        imageCol.setCellValueFactory(new PropertyValueFactory<>("objectColumn01"));
        TableColumn<ConverterTableViewColumn<ImageView, Void, Void>,String> lastNameCol = new TableColumn<>("LastName");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn01"));
        TableColumn<ConverterTableViewColumn<ImageView, Void, Void>,String> firstNameCol = new TableColumn<>("FirstName");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn02"));
        tableView.getColumns().setAll(imageCol, lastNameCol, firstNameCol);
    }

    @Override
    public ConverterTableViewColumn<ImageView, Void, Void> getConverterTableViewColumn(Author item) {
        ConverterTableViewColumn<ImageView,Void,Void> column = new ConverterTableViewColumn<>();
        ImageProvider provider = new ImageProvider(item.getPicture());
        column.setObjectColumn01(provider.getImageView());
        column.setStringColumn01(item.getLastName());
        column.setStringColumn02(item.getFirstName());
        return column;
    }
}
