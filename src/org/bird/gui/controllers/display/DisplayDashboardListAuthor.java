package org.bird.gui.controllers.display;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.common.ConverterTableViewColumn;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.ShowException;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;

/**
 * Cette classe affiche les items de type auteur dans le TableView
 */
public class DisplayDashboardListAuthor extends DisplayDashboardList<Author> {

    private TableView<ConverterTableViewColumn<ImageView, Void, Void>> tableView;
    private Pane itemsContainer;

    /**
     *
     * @param itemsContainer
     */
    public DisplayDashboardListAuthor(Pane itemsContainer) {
        this.itemsContainer = itemsContainer;
    }

    /**
     * Charge les items au travers du paginator
     * @param paginator
     * @throws IOException
     */
    @Override
    public void display(Paginator<Author> paginator) throws IOException {
        try {
            //Vide le container
            itemsContainer.getChildren().clear();
            //Crée la vue
            FXMLLoaderImpl fxmlLoader = new FXMLLoaderImpl();
            FXMLLoader _loader = fxmlLoader.getFXMLLoader(this.getClass());
            _loader.setController();
            TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, ImageView> imageCol = new TableColumn<>("Images");
            imageCol.setCellValueFactory(new PropertyValueFactory<>("objectColumn01"));
            TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, String> lastNameCol = new TableColumn<>("LastName");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn01"));
            TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, String> firstNameCol = new TableColumn<>("FirstName");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn02"));
            tableView.getColumns().setAll(imageCol, lastNameCol, firstNameCol);
            DisplayService service = new DisplayService(tableView, paginator);
            service.start();
        } catch (ConfigurationException e) {
            ShowException showException = new ShowException(e);
            showException.show("ConfigurationException");
        }
    }

    /**
     * Cette méthode va convertir un item de type auteur vers un item de type ConverterTableViewColumn
     * Qui est le type d'objet attendu par le TableView
     * @param item
     * @return
     */
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
