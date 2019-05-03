package org.bird.gui.controllers.display;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.common.ConverterTableViewColumn;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.ShowException;
import org.bird.gui.controllers.ListDashboardController;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;

/**
 * Cette classe affiche les items de type auteur dans le TableView
 */
public class DisplayDashboardListAuthor extends DisplayDashboardList<Author> {

    private TableView<ConverterTableViewColumn<ImageView, Void, Void>> tableView;
    private Pane itemsContainer;
    private Configuration configuration;

    /**
     * Constructeur
     * @param itemsContainer
     */
    public DisplayDashboardListAuthor(Pane itemsContainer) throws ConfigurationException {
        this.itemsContainer = itemsContainer;
        ConfigurationBuilder builder = ConfigurationBuilder.getInstance();
        configuration = builder.get("layout");
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
            //instance le controller pour la vue
            ListDashboardController controller = new ListDashboardController();
            //Crée la vue
            FXMLLoaderImpl fxmlLoader = new FXMLLoaderImpl();
            FXMLLoader _loader = fxmlLoader.getFXMLLoader(this.getClass());
            _loader.setController(controller);
            Node node = _loader.load();
            //Ajoute la vue dans le container du dashboard
            AnchorPane anchorPane = (AnchorPane) itemsContainer;
            AnchorPane.setTopAnchor(node,0.0);
            AnchorPane.setRightAnchor(node,0.0);
            AnchorPane.setLeftAnchor(node,0.0);
            AnchorPane.setBottomAnchor(node,0.0);
            anchorPane.getChildren().add(node);
            //Récupère le tableau
            tableView = controller.getTableView();
            //Ajoute les colonnes pour la liste des auteurs
            TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, ImageView> imageCol = new TableColumn<>("Images");
            imageCol.setCellValueFactory(new PropertyValueFactory<>("objectColumn01"));
            TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, String> lastNameCol = new TableColumn<>("LastName");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn01"));
            TableColumn<ConverterTableViewColumn<ImageView, Void, Void>, String> firstNameCol = new TableColumn<>("FirstName");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn02"));
            //Récupère le tableau
            tableView = controller.getTableView();
            //On cahrge les colonnes dans le tableau
            tableView.getColumns().setAll(imageCol, lastNameCol, firstNameCol);
            //On demande au controller de charger la traduction des titres des colonnes
            controller.setLanguage();
            //Instance & start le service qui va se charger d'afficher la liste des auteurs
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
    public ConverterTableViewColumn<ImageView, Void, Void> getConverterTableViewColumn(Author item) throws ConfigurationException {
        ConverterTableViewColumn<ImageView,Void,Void> column = new ConverterTableViewColumn<>();
        ImageProvider provider = new ImageProvider(item.getPicture());
        ImageView imageView = provider.getImageView();
        imageView.setPreserveRatio(true);
        Double fitHeight = configuration.get("layout.list_dashboard.image_view_height").getAsDouble();
        imageView.setFitHeight(fitHeight);
        column.setObjectColumn01(imageView);
        column.setStringColumn01(item.getLastName());
        column.setStringColumn02(item.getFirstName());
        return column;
    }
}
