package org.bird.gui.controllers.display;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.common.ConverterTableViewColumnThree;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.ShowException;
import org.bird.gui.controllers.ListDashboardController;
import org.bird.gui.events.OnSelectedEvent;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;

/**
 * Cette classe affiche les items de type auteur dans le TableView
 */
public class DisplayDashboardListAuthor extends DisplayDashboardList<Author> {

    private TableView<ConverterTableViewColumnThree<ImageView, Void, Void>> tableView;

    /**
     * Constructeur
     * @param itemsContainer
     */
    public DisplayDashboardListAuthor(ObservableList itemsContainer) throws ConfigurationException {
        super(itemsContainer);

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
            itemsContainer.clear();
            //instance le controller pour la vue
            ListDashboardController controller = new ListDashboardController();
            //Crée la vue
            FXMLLoaderImpl fxmlLoader = new FXMLLoaderImpl();
            FXMLLoader _loader = fxmlLoader.getFXMLLoader(this.getClass());
            _loader.setController(controller);
            Node node = _loader.load();
            //Ajoute la vue dans le container du dashboard
            itemsContainer.add(node);
            //Ajoute les colonnes pour la liste des auteurs
            TableColumn<ConverterTableViewColumnThree<ImageView, Void, Void>, ImageView> imageCol = new TableColumn<>("Images");
            imageCol.setCellValueFactory(new PropertyValueFactory<>("objectColumn01"));
            TableColumn<ConverterTableViewColumnThree<ImageView, Void, Void>, String> lastNameCol = new TableColumn<>("LastName");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn01"));
            TableColumn<ConverterTableViewColumnThree<ImageView, Void, Void>, String> firstNameCol = new TableColumn<>("FirstName");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn02"));
            //Récupère le tableau
            tableView = controller.getTableView();
            //On charge les colonnes dans le tableau
            tableView.getColumns().setAll(imageCol, lastNameCol, firstNameCol);
            //On defini l'event
            tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConverterTableViewColumnThree<ImageView, Void, Void>>() {
                @Override
                public void changed(ObservableValue<? extends ConverterTableViewColumnThree<ImageView, Void, Void>> observableValue, ConverterTableViewColumnThree<ImageView, Void, Void> oldVal, ConverterTableViewColumnThree<ImageView, Void, Void> newVal) {
                    Author author = (Author) newVal.getSource();
                    notifyOnSelectedListener(new OnSelectedEvent<Author>(this, author));
                }
            });
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
     * Cette méthode va convertir un item de type auteur vers un item de type ConverterTableViewColumnThree
     * Qui est le type d'objet attendu par le TableView
     * @param item
     * @return
     */
    @Override
    public ConverterTableViewColumnThree<ImageView, Void, Void> getConverterTableViewColumn(Author item) throws ConfigurationException {
        ConverterTableViewColumnThree<ImageView,Void,Void> column = new ConverterTableViewColumnThree<>();
        column.setSource(item);
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
